package org.c9504.processors;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProjectProcessor {
    
    @Incoming("projects-in")
    @Outgoing("projects")
    @Broadcast
    public String process(ConsumerRecord<Integer, String> project) {
        System.out.println("Processing Project with ID: " + project.key() + ", Value: " + project.value());
        return project.value();
    }
}