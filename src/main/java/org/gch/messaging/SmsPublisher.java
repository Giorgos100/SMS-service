package org.gch.messaging;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.gch.entity.Message;
import org.gch.entity.MessageStatus;

import java.util.Random;

@ApplicationScoped
public class SmsPublisher {

    private final Random random = new Random();

    /**
     * Simulate sending: update the message status to DELIVERED or FAILED.
     * This method is transactional so changes to the managed entity persist.
     */
    @Transactional
    public void send(Message message) {
        boolean delivered = random.nextBoolean();
        if (delivered) {
            message.setStatus(MessageStatus.DELIVERED);
        } else {
            message.setStatus(MessageStatus.FAILED);
        }
    }
}
