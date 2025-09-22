package org.gch.resource;

import org.gch.dto.SendMessageRequest;
import org.gch.entity.Message;
import org.gch.entity.MessageStatus;
import org.gch.messaging.SmsPublisher;
import org.gch.repository.MessageRepository;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

    @Inject
    MessageRepository repository;

    @Inject
    SmsPublisher publisher;

    @POST
    @Transactional
    public Response sendMessage(@Valid SendMessageRequest request, @Context UriInfo uriInfo) {
        Message message = new Message(
                request.getSourceNumber(),
                request.getDestinationNumber(),
                request.getContent(),
                MessageStatus.PENDING
        );
        repository.saveMessage(message);

        // synchronous simulation of delivery (updates status)
        publisher.send(message);

        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(message.id));
        return Response.created(builder.build()).entity(message).build();
    }

    @GET
    public List<Message> getAllMessages() {
        return repository.listAllMessages();
    }

    @GET
    @Path("/{id}")
    public Response getMessageById(@PathParam("id") Long id) {
        Message m = repository.findByIdMessage(id);
        if (m == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(m).build();
    }

    @GET
    @Path("/search")
    public List<Message> searchMessages(@QueryParam("sourceNumber") String sourceNumber,
                                        @QueryParam("destinationNumber") String destinationNumber) {
        return repository.searchMessages(sourceNumber, destinationNumber);
    }

    @DELETE
    @Transactional
    public Response deleteAllMessages() {
        repository.deleteAllMessages();
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteMessage(@PathParam("id") Long id) {
        boolean deleted = repository.deleteMessageById(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
