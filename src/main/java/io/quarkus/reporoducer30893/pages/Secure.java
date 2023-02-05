package io.quarkus.reporoducer30893.pages;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/secure")
@RolesAllowed("ADMIN")
public class Secure {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return Templates.secure();
    }

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance secure();
    }

}
