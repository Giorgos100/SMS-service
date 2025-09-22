package org.gch.repository;

import org.gch.entity.Message;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class MessageRepository {

    @Transactional
    public void saveMessage(Message message) {
        message.persist();
    }

    public List<Message> listAllMessages() {
        return Message.listAll();
    }

    public Message findByIdMessage(Long id) {
        return Message.findById(id);
    }

    public List<Message> searchMessages(String sourceNumber, String destinationNumber) {
        if (sourceNumber == null && destinationNumber == null) {
            return listAllMessages();
        }
        if (sourceNumber != null && destinationNumber != null) {
            return Message.list("sourceNumber = ?1 and destinationNumber = ?2", sourceNumber, destinationNumber);
        }
        if (sourceNumber != null) {
            return Message.list("sourceNumber = ?1", sourceNumber);
        }
        return Message.list("destinationNumber = ?1", destinationNumber);
    }

    @Transactional
    public void deleteAllMessages() {
        Message.deleteAll();
    }

    @Transactional
    public boolean deleteMessageById(Long id) {
        return Message.deleteById(id);
    }
}
