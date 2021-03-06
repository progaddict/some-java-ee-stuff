package com.somejavaeestuff.cdi;

import javax.annotation.Priority;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.interceptor.Interceptor;

public class OrderedObserver {

    public static void main(String[] args) {
        try (var container = SeContainerInitializer.newInstance().initialize()) {
            container.getBeanManager().fireEvent(
                    new MyEvent("event: " + System.currentTimeMillis())
            );
        }
    }

    public void thisEventBefore(
            @Observes @Priority(Interceptor.Priority.APPLICATION - 200)
                    MyEvent event
    ) {
        System.out.println("thisEventBefore: " + event.getValue());
    }

    public void thisEventAfter(
            @Observes @Priority(Interceptor.Priority.APPLICATION + 200)
                    MyEvent event
    ) {
        System.out.println("thisEventAfter: " + event.getValue());
    }
}
