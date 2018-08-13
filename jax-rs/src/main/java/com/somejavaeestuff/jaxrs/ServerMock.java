package com.somejavaeestuff.jaxrs;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class ServerMock {

    public static final URI CONTEXT =
            URI.create("http://localhost:8080/");

    public static final String BASE_PATH =
            "ssevents";

    public static void main(String[] args) {
        try {
            final var resourceConfig = new ResourceConfig(SseResource.class);
            final var httpServer = GrizzlyHttpServerFactory.createHttpServer(
                    CONTEXT,
                    resourceConfig,
                    false
            );
            httpServer.start();
            System.out.println(String.format(
                    "Server started at %s%s",
                    CONTEXT,
                    BASE_PATH
            ));
            Thread.currentThread().join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
