package org.gch.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Pattern(regexp = "\\d{8,15}", message = "Source number must be 8-15 digits")
    private String sourceNumber;

    @NotBlank
    @Pattern(regexp = "\\d{8,15}", message = "Destination number must be 8-15 digits")
    private String destinationNumber;

    @NotBlank
    @Size(max = 160, message = "Content cannot exceed 160 characters")
    private String content;

    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    public Message() {}

    public Message(String sourceNumber, String destinationNumber, String content, MessageStatus status) {
        this.sourceNumber = sourceNumber;
        this.destinationNumber = destinationNumber;
        this.content = content;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSourceNumber() { return sourceNumber; }
    public void setSourceNumber(String sourceNumber) { this.sourceNumber = sourceNumber; }

    public String getDestinationNumber() { return destinationNumber; }
    public void setDestinationNumber(String destinationNumber) { this.destinationNumber = destinationNumber; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public MessageStatus getStatus() { return status; }
    public void setStatus(MessageStatus status) { this.status = status; }
}
