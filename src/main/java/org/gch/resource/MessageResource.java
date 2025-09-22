package org.gch.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.gch.dto.SendMessageRequest;
import org.gch.entity.Message;
import org.gch.entity.MessageStatus;
import org.gch.repository.MessageRepository;
import org.gch.messaging.SmsPublisher;

import java.util.List;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

    @Inject
    MessageRepository messageRepository;

    @Inject
    SmsPublisher smsPublisher;

    @GET
    public List<Message> getAllMessages() {
        return messageRepository.listAll();
    }

    @POST
    @Transactional
    public Response sendMessage(@Valid SendMessageRequest request) {
        Message message = new Message();
        message.setSourceNumber(request.getSourceNumber());
        message.setDestinationNumber(request.getDestinationNumber());
        message.setContent(request.getContent());

        MessageStatus status = smsPublisher.sendMessage(message);
        message.setStatus(status);

        messageRepository.persist(message);
        return Response.status(Response.Status.CREATED).entity(message).build();
    }

    @GET
    @Path("/search")
    public List<Message> searchMessages(@QueryParam("q") String query) {
        return messageRepository.search(query);
    }

    @GET
    @Path("/{id}")
    public Message getMessage(@PathParam("id") Long id) {
        return messageRepository.findById(id);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deleteMessage(@PathParam("id") Long id) {
        messageRepository.deleteById(id);
    }
}
