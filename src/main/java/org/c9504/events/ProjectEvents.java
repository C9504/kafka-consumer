package org.c9504.events;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.annotations.SseElementType;
import org.c9504.entities.Project;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/events")
public class ProjectEvents {

    @Inject
    @Channel("projects")
    Publisher<Project> projects;

    @GET
    @Path("/projects")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType("application/json")
    public Publisher<Project> streamProjects() {
        return projects;
    }
}
