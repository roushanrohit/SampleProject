package org.tcpbasics;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConnectionPoolExample {

    private static final int MAX_CONNECTIONS = 2;
    private static final PoolingHttpClientConnectionManager poolingConnectionManager = new PoolingHttpClientConnectionManager();

    static {
        poolingConnectionManager.setMaxTotal(MAX_CONNECTIONS);
        poolingConnectionManager.setDefaultMaxPerRoute(MAX_CONNECTIONS);
    }

    private static final ConnectionKeepAliveStrategy keepAliveStrategy = (HttpResponse response, HttpContext context) -> {
        // Always keep the connection alive for 10 seconds (1000 ms)
        return Long.MAX_VALUE;
    };

    private static final RequestConfig requestConfig = RequestConfig.custom()
            .setConnectionRequestTimeout(10000)
            .setConnectTimeout(10000)
            .setSocketTimeout(10000)
            .build();

    private static final HttpClient client = HttpClients.custom()
            .setConnectionManager(poolingConnectionManager)
            .setDefaultRequestConfig(requestConfig)
            .addInterceptorFirst(new ConnectionLoggingInterceptor())
            .setKeepAliveStrategy(keepAliveStrategy)
            .evictIdleConnections(3, TimeUnit.SECONDS)
            .build();

    public static void makeRequest(String url) throws IOException {
        HttpGet request = new HttpGet(url);
        try (CloseableHttpResponse response = (CloseableHttpResponse) client.execute(request)) {

            System.out.println("[Response Headers for " + url + "]:");
            for (Header header : response.getAllHeaders()) {
                System.out.println(header.getName() + ": " + header.getValue());
            }

//            System.out.println("Connection Pool Stats: Start");
//            System.out.println("  Max Total: " + poolingConnectionManager.getMaxTotal());
//            System.out.println("  Default Max Per Route: " + poolingConnectionManager.getDefaultMaxPerRoute());
//            System.out.println("  Available: " + poolingConnectionManager.getTotalStats().getAvailable());
//            System.out.println("  Leased: " + poolingConnectionManager.getTotalStats().getLeased());
//            System.out.println("  Pending: " + poolingConnectionManager.getTotalStats().getPending());
//            System.out.println("Connection Pool Stats: End");

            try {
                // Simulate processing time (keep the connection busy)
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void makeRequest2(String url) throws IOException {
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = (CloseableHttpResponse) client.execute(request);
            // Ensure the entity is fully consumed (if any) to allow connection reuse
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                EntityUtils.consume(entity);
            }

            // Simulate processing time AFTER the response is handled
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (response != null) {
                try {
                    response.close(); // Explicitly close the response to release the connection
                } catch (IOException e) {
                    System.err.println("Error closing response: " + e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String url = "https://jsonplaceholder.typicode.com/posts";

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (int i = 1; i <= 4; i++) {
            final int requestId = i;
            executorService.submit(() -> {
                System.out.println("Sending request #" + requestId);
                try {
                    makeRequest2(url);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        // shutdown after all tasks are submitted
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.MINUTES);
    }
}
