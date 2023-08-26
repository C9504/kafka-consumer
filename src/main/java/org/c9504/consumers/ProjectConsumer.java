package org.c9504.consumers;

import io.smallrye.common.annotation.Blocking;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProjectConsumer {

    private final Logger logger = Logger.getLogger(ProjectConsumer.class);

    @Incoming("projects-in")
    @Acknowledgment(Acknowledgment.Strategy.POST_PROCESSING)
    @Blocking
    public void receiveProject(ConsumerRecord<Integer, String> project) {
        logger.infof("Consumed project: %d - %s in partition: %d", project.key(), project.value(), project.partition());
    }

}
