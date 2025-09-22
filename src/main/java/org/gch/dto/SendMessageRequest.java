package org.gch.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SendMessageRequest {

    @NotBlank(message = "sourceNumber is required")
    @Pattern(regexp = "\\d{3,15}", message = "sourceNumber must be 3-15 digits")
    private String sourceNumber;

    @NotBlank(message = "destinationNumber is required")
    @Pattern(regexp = "\\d{3,15}", message = "destinationNumber must be 3-15 digits")
    private String destinationNumber;

    @NotBlank(message = "content is required")
    @Size(max = 160, message = "content must be at most 160 characters")
    private String content;

    public SendMessageRequest() {}

    public String getSourceNumber() { return sourceNumber; }
    public void setSourceNumber(String sourceNumber) { this.sourceNumber = sourceNumber; }

    public String getDestinationNumber() { return destinationNumber; }
    public void setDestinationNumber(String destinationNumber) { this.destinationNumber = destinationNumber; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
