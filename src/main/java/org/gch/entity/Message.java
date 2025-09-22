package org.gch.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
public class Message extends PanacheEntity {

    @Column(nullable = false)
    public String sourceNumber;

    @Column(nullable = false)
    public String destinationNumber;

    @Column(nullable = false, length = 160)
    public String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public MessageStatus status;

    public Message() {}

    public Message(String sourceNumber, String destinationNumber, String content, MessageStatus status) {
        this.sourceNumber = sourceNumber;
        this.destinationNumber = destinationNumber;
        this.content = content;
        this.status = status;
    }

    public String getSourceNumber() { return sourceNumber; }
    public void setSourceNumber(String sourceNumber) { this.sourceNumber = sourceNumber; }

    public String getDestinationNumber() { return destinationNumber; }
    public void setDestinationNumber(String destinationNumber) { this.destinationNumber = destinationNumber; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public MessageStatus getStatus() { return status; }
    public void setStatus(MessageStatus status) { this.status = status; }
}
