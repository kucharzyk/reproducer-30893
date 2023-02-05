package io.quarkus.reporoducer30893.exception.handlers;

import io.quarkus.logging.Log;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.ForbiddenException;
import jakarta.annotation.Priority;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.net.URI;

@Provider
public class NotFoundExceptionMapper implements  ExceptionMapper<NotFoundException> {

    @Context
    UriInfo uriInfo;

    @Context
    HttpHeaders headers;

    @Override
    public Response toResponse(NotFoundException e) {
        if (headers.getAcceptableMediaTypes().contains(MediaType.TEXT_HTML_TYPE)) {
            Log.warn("Page not found: " + uriInfo.getRequestUri());
            return Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_HTML_TYPE).entity(Templates.notFound().render()).build();
        } else {
            Log.warn("Resource not found: " + uriInfo.getRequestUri());
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @CheckedTemplate(basePath = "NotFound")
    public static class Templates {
        public static native TemplateInstance notFound();
    }
}