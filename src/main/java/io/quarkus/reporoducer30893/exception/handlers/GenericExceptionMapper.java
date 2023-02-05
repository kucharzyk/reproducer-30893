package io.quarkus.reporoducer30893.exception.handlers;

import io.quarkus.logging.Log;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper  implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException e) {
        Log.error(e.getMessage(), e);
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .type(MediaType.TEXT_HTML)
                .entity(Templates.serverError().render()).build();
    }

    @CheckedTemplate(basePath = "ServerError")
    public static class Templates {
        public static native TemplateInstance serverError();
    }
}