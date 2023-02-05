package io.quarkus.reporoducer30893.pages;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@PermitAll
@Path("/logout")
public class Logout {

    @GET
    @Path("/success")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance logoutSuccess() {
        return Templates.logout();
    }

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance logout();
    }

}
