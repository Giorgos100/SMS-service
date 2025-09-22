package org.gch.exception;

import java.util.ArrayList;
import java.util.List;

public class ApiError {
    private String message;
    private List<String> details = new ArrayList<>();

    public ApiError() {}

    public ApiError(String message) { this.message = message; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public List<String> getDetails() { return details; }
    public void setDetails(List<String> details) { this.details = details; }

    public void addDetail(String d) { this.details.add(d); }
}
