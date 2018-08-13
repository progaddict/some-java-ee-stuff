package com.somejavaeestuff.jaxrs;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.SseEventSource;

import static com.somejavaeestuff.jaxrs.ServerMock.BASE_PATH;
import static com.somejavaeestuff.jaxrs.ServerMock.CONTEXT;

public class ClientConsumer {

    public static final Client CLIENT =
            ClientBuilder.newClient();

    public static final WebTarget WEB_TARGET =
            CLIENT.target(CONTEXT + BASE_PATH);

    public static void main(String[] args) {
        consume();
    }

    private static void consume() {
        try (final var sseSource = SseEventSource.target(WEB_TARGET).build()) {
            sseSource.register(System.out::println);
            sseSource.open();
            for (int counter = 0; counter < 5; counter++) {
                System.out.println(" ");
                for (int innerCounter = 0; innerCounter < 5; innerCounter++) {
                    WEB_TARGET.request().post(
                            Entity.json("event " + innerCounter)
                    );
                    Thread.sleep(1000);
                }
            }
            CLIENT.close();
            System.out.println("\nall messages consumed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
