package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class OpenAIChatExample {

    /*
        For other models, we may need to change the base url, api key and the model name,
        provided it supports the OpenAI-compatible Chat Completions API
     */
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public static void main(String[] args) throws IOException, InterruptedException {

        // 1. Read the API key from the environment
        String apiKey = System.getenv("OPENAI_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            System.out.println("No API key was found.");
            return;
        }
        System.out.println("API key found and looks good!");

        // 2. Construct the JSON request body
        String requestBody = """
                {
                  "model": "gpt-5-nano",
                  "messages": [
                    {
                      "role": "system",
                      "content": "You are my personal assistant"
                    },
                    {
                      "role": "user",
                      "content": "What is my name?"
                    }
                  ],
                  "reasoning_effort": "minimal"
                }
                """;

        // 3. Create the HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // 4. Build the HTTP request -- a POST request
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_URL))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(
                        requestBody, StandardCharsets.UTF_8))
                .build();

        // 5. Send the request
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // 6. Handle unsuccessful responses
        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            System.out.println("HTTP Status: " + response.statusCode());
            System.out.println("Error response: " + response.body());
            return;
        }

        // 7. Extract the assistant's message from the JSON response
        String answer = extractMessageContent(response.body());

        System.out.println("\nGPT response:");
        System.out.println(answer);

    }

    // Extract the JSON string in choices[0].message.content
    private static String extractMessageContent(String json) {
        int messageIndex = json.indexOf("\"message\"");
        if (messageIndex == -1) {
            throw new IllegalArgumentException("Message not found in API response.");
        }

        int contentIndex = json.indexOf("\"content\"", messageIndex);
        if (contentIndex == -1) {
            throw new IllegalArgumentException("Content not found in API response.");
        }

        int colonIndex = json.indexOf(':', contentIndex);
        int start = json.indexOf('"', colonIndex + 1);

        if (colonIndex == -1 || start == -1) {
            throw new IllegalArgumentException("Invalid content field in API response.");
        }

        StringBuilder result = new StringBuilder();
        boolean escaped = false;

        for (int i = start + 1; i < json.length(); i++) {
            char c = json.charAt(i);

            if (escaped) {
                switch (c) {
                    case '"' -> result.append('"');
                    case '\\' -> result.append('\\');
                    case '/' -> result.append('/');
                    case 'b' -> result.append('\b');
                    case 'f' -> result.append('\f');
                    case 'n' -> result.append('\n');
                    case 'r' -> result.append('\r');
                    case 't' -> result.append('\t');
                    case 'u' -> {
                        if (i + 4 >= json.length()) {
                            throw new IllegalArgumentException("Invalid Unicode escape.");
                        }
                        String hex = json.substring(i + 1, i + 5);
                        result.append((char) Integer.parseInt(hex, 16));
                        i += 4;
                    }
                    default -> throw new IllegalArgumentException("Invalid JSON escape: \\" + c);
                }
                escaped = false;
            } else if (c == '\\') {
                escaped = true;
            } else if (c == '"') {
                return result.toString();
            } else {
                result.append(c);
            }
        }

        throw new IllegalArgumentException("Unterminated JSON content string.");
    }
}