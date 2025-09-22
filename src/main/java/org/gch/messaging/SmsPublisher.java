package org.gch.messaging;

import jakarta.enterprise.context.ApplicationScoped;
import org.gch.entity.Message;
import org.gch.entity.MessageStatus;

@ApplicationScoped
public class SmsPublisher {

    public MessageStatus sendMessage(Message message)
    {
        if (message.getContent() == null || message.getContent().isBlank())
        {
            return MessageStatus.FAILED;
        }
        return MessageStatus.DELIVERED;
    }
}
