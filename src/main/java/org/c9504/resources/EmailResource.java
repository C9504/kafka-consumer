package org.c9504.resources;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.reactive.ReactiveMailer;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/email")
public class EmailResource {

    @Inject
    Mailer mailer;

    @Inject
    ReactiveMailer reactiveMailer;

    @GET
    @Path("/simple")
    //@Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON)
    public void sendSimpleEmail () {
        mailer.send(Mail.withText("cperez@idtolu.com", "Quarkus projects", "Hi Cesar"));
    }

    /*@GET
    @Path("/reactive")
    //@Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON)
    public Uni<Void> sendReactiveEmail () {
        //mailer.send(Mail.withText("cperez@idtolu.com", "Quarkus projects", "Hi Cesar"));
        return reactiveMailer.send(Mail.withText("cperez@idtolu.com", "Quarkus projects", "Hi Cesar"));
    }*/
}
