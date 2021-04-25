package nl.themagrid.helper.exceptions;

import io.quarkus.runtime.annotations.RegisterForReflection;
import nl.themagrid.helper.exceptions.ApiException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@RegisterForReflection
public class ApiExceptionHandler implements ExceptionMapper<ApiException> {
    @Override
    public Response toResponse(ApiException e) {
        return Response.status(e.httpStatus).entity(e.getMessage()).build();
    }
}
