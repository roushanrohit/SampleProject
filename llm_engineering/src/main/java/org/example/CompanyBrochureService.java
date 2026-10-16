package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class CompanyBrochureService {

    public static List<String> fetchWebsiteLinks(HttpClient client, String url) throws IOException, InterruptedException {

        // 1. Build the HTTP GET request
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
                .header("User-Agent", "Mozilla/5.0")
                .GET()
                .build();

        // 2. Fetch the website HTML
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("Failed to fetch website. HTTP status: " + response.statusCode());
        }

        // 3. Parse HTML using Jsoup
        Document document = Jsoup.parse(response.body(), url);

        // 4. Find all anchor elements
        Elements anchorElements = document.select("a");
        List<String> links = new ArrayList<>();

        // 5. Extract non-empty href attributes
        for (Element element : anchorElements) {
            String href = element.attr("href");
            if (!href.isBlank()) {
                links.add(href);
            }
        }

        return links;
    }
}
