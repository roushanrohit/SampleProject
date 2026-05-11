package org.tcpbasics;

import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.protocol.HttpContext;

public class ConnectionLoggingInterceptor implements HttpResponseInterceptor {

    @Override
    public void process(HttpResponse response, HttpContext context) {
        Object conn = context.getAttribute("http.connection");
        if (conn instanceof ManagedHttpClientConnection) {
            ManagedHttpClientConnection connection = (ManagedHttpClientConnection) conn;
            System.out.println("[Interceptor " + Thread.currentThread().getName() + "]  Using connection: " +
                    connection.getRemoteAddress() + ":" +
                    connection.getRemotePort() +
                    " - ID: " + connection.hashCode());
        } else {
            System.out.println("[Interceptor] No ManagedHttpClientConnection found");
        }
    }
}
