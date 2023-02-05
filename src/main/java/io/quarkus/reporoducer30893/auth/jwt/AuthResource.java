package io.quarkus.reporoducer30893.auth.jwt;

import io.quarkus.logging.Log;
import io.quarkus.oidc.OidcSession;
import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.NewCookie;
import org.jboss.resteasy.reactive.RestResponse;

import java.net.URI;

@Path("/auth/")
@Authenticated
public class AuthResource {

    @Inject
    OidcSession oidcSession;

    @Inject
    SecurityIdentity securityIdentity;

    @GET
    @Path("logout")
    @PermitAll
    public RestResponse<Object> logout() {
        Log.info("Logging out " + securityIdentity.getPrincipal().getName());
        oidcSession.logout().await().indefinitely();
        return RestResponse.ResponseBuilder
                .seeOther(URI.create("/logout/success"))
                .cookie(new NewCookie.Builder("X-TOKEN")
                        .value(null)
                        .path("/")
                        .maxAge(0)
                        .httpOnly(true)
                        .secure(true)
                        .sameSite(NewCookie.SameSite.STRICT)
                        .build())
                .build();
    }

}
