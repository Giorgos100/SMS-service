package org.gch.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SendMessageRequest {

    @NotBlank
    @Pattern(regexp = "\\d{8,15}", message = "Source number must be 8-15 digits")
    private String sourceNumber;

    @NotBlank
    @Pattern(regexp = "\\d{8,15}", message = "Destination number must be 8-15 digits")
    private String destinationNumber;

    @NotBlank
    @Size(max = 160, message = "Content cannot exceed 160 characters")
    private String content;

    public SendMessageRequest() {}

    public String getSourceNumber() { return sourceNumber; }
    public void setSourceNumber(String sourceNumber) { this.sourceNumber = sourceNumber; }

    public String getDestinationNumber() { return destinationNumber; }
    public void setDestinationNumber(String destinationNumber) { this.destinationNumber = destinationNumber; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
