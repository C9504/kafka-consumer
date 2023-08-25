package org.negrdo.processors;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import io.vertx.core.json.JsonObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.negrdo.entities.Project;

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