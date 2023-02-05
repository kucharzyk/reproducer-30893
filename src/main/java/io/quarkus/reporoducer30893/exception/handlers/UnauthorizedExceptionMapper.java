package io.quarkus.reporoducer30893.exception.handlers;

import io.quarkus.logging.Log;
import io.quarkus.security.UnauthorizedException;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.net.URI;

//@Provider
//@Priority(Priorities.AUTHENTICATION)
//public class UnauthorizedExceptionMapper implements ExceptionMapper<UnauthorizedException> {
//
//    @Context
//    UriInfo uriInfo;
//
//    @Override
//    public Response toResponse(UnauthorizedException e) {
//        e.printStackTrace();
//        Log.warn("Unauthorized request: " + uriInfo.getRequestUri());
//        return Response.ok("EXCEPTION HANDLED", MediaType.TEXT_HTML_TYPE).build();
//    }
//}