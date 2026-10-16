package org.example;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatRequestParameters;

import java.util.List;
import java.util.Map;

public class OpenAIChatUsingLangChain4j {

    /*
        You no longer need to construct the HTTP request manually or write your own
        JSON response parser for this use case. LangChain4j handles those details.
     */
    public static void main(String[] args) {

        // 1. Read API key from environment
        String apiKey = System.getenv("OPENAI_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            System.out.println("No API key was found.");
            return;
        }

        System.out.println("API key found and looks good!");

        // 2. Define the prompts
        String systemPrompt = "You are my personal assistant";
        String userPrompt = "Please suggest some good malayalam movies that are also available in Hindi";

        // 3. Configure the model
        ChatModel model = OpenAiChatModel.builder().apiKey(apiKey).modelName("gpt-5-nano").build();

        /* DeepSeek using its OpenAI-compatible API
            ChatModel deepSeekModel = OpenAiChatModel.builder().baseUrl("https://api.deepseek.com")
                    .apiKey(deepSeekKey).modelName("deepseek-chat").build();
         */

        // 4. Construct the chat request
        ChatRequest request = ChatRequest.builder()
                .messages(List.of(SystemMessage.from(systemPrompt), UserMessage.from(userPrompt)))
                .parameters(OpenAiChatRequestParameters.builder().modelName("gpt-5-nano")
                                .customParameters(Map.of("reasoning_effort", "minimal")).build())
                .build();

        /*
            In LangChain4j, the ChatModel interface provides a convenient chat(String userMessage) overload
            in supported versions, as well as a method that accepts a ChatRequest.
            String answer = openAiModel.chat("What is LangChain4j?");
            System.out.println(answer);
         */
        // 5. Call the model
        String answer = model.chat(request).aiMessage().text();

        // 6. Display the response
        System.out.println("\nGPT response:");
        System.out.println(answer);
    }
}
