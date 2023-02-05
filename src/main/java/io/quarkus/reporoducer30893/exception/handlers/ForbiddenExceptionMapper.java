package io.quarkus.reporoducer30893.exception.handlers;

import io.quarkus.logging.Log;
import io.quarkus.security.ForbiddenException;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.net.URI;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class ForbiddenExceptionMapper implements ExceptionMapper<ForbiddenException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(ForbiddenException e) {
        Log.warn("Forbidden request: " + uriInfo.getRequestUri());
        return Response.temporaryRedirect(URI.create("/access-denied")).build();
    }
}