package org.gch.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        ApiError error = new ApiError("Validation failed");
        for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
            String path = cv.getPropertyPath() == null ? "" : cv.getPropertyPath().toString();
            error.addDetail(path + " " + cv.getMessage());
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON)
                .entity(error)
                .build();
    }
}
