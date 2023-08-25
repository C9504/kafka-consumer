package org.c9504.consumers;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.reactive.ReactiveMailer;
import io.smallrye.common.annotation.Blocking;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;
import org.c9504.repositories.ProjectRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Arrays;

@ApplicationScoped
public class ProjectConsumer {

    private final Logger logger = Logger.getLogger(ProjectConsumer.class);

    @Inject
    ProjectRepository projectRepository;

    @Inject
    Mailer mailer;

    @Inject
    ReactiveMailer reactiveMailer;

    @Inject
    Vertx vertx;

    @Incoming("projects-in")
    @Acknowledgment(Acknowledgment.Strategy.POST_PROCESSING)
    @Blocking
    public void receiveProject(ConsumerRecord<Integer, String> project) {
        System.out.println("Saving...");
        /**Project entity = new Project();
        entity.id = project.key();
        entity.value = project.value();
        entity.persist();
        entity = null;*/
        /*mailer.send(Mail.withText("cperez@idtolu.com", "Quarkus projects", "Hi Cesar"));*/
        logger.infof("Consumed project: %d - %s in partition: %d", project.key(), project.value(), project.partition());
    }

    @Incoming("instance-in")
    @Acknowledgment(Acknowledgment.Strategy.POST_PROCESSING)
    //@Blocking
    public void receiveEventCreateInstances(ConsumerRecord<String, String> alerts) {
        JsonObject instance = new JsonObject(alerts.value().toString());
        vertx.executeBlocking(future -> {
            reactiveMailer.send(
                            Mail.withText("cperez@idtolu.com", "Quarkus projects", "Greetings from alerts microservice: " + instance.getString("message") + "")
                                    .setTo(Arrays.asList("elperezAnthony@gmail.com", "evergarah2000@gmail.com")))
                    .subscribe()
                    .with(success -> {
                        System.out.println("Sent...");
                    }, error -> {
                        System.err.println(error.getMessage());
                    });
            future.complete();
        }, false, asyncResult -> {
            if (asyncResult.succeeded()) {
                System.out.println("Sent email...");
            } else {
                System.err.println("Error send email");
            }
        });
        logger.infof("Consumed instance: %d - %s in partition: %d", alerts.timestamp(), String.valueOf(alerts.value()), alerts.partition());
    }

}
