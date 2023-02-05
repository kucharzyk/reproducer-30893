package io.quarkus.reporoducer30893.auth.oidc;

import io.quarkus.logging.Log;
import io.quarkus.oidc.OidcSession;
import io.quarkus.oidc.UserInfo;
import io.quarkus.oidc.runtime.OidcUtils;
import io.quarkus.reporoducer30893.auth.cons.GithubAttributes;
import io.quarkus.reporoducer30893.auth.jwt.AuthService;
import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import org.jboss.resteasy.reactive.RestResponse;

import java.net.URI;

@Path("/auth/github")
@Authenticated
public class GithubAuthResource {

    @Inject
    SecurityIdentity securityIdentity;

    @Inject
    AuthService authService;

    @Inject
    OidcSession oidcSession;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public RestResponse<Void> github() {
        return RestResponse.seeOther(URI.create("/auth/github/success"));
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("success")
    public RestResponse<Object> authSuccess() {
        Log.info("Authenticated " + securityIdentity.getPrincipal().getName());

        UserInfo userInfo = securityIdentity.getAttribute(OidcUtils.USER_INFO_ATTRIBUTE);
        if (userInfo == null) {
            Log.error("Failed to get user info");
            return RestResponse.seeOther(URI.create("/"));
        }

        var userInfoJson = userInfo.getJsonObject();
        String externalId = String.valueOf(userInfoJson.getJsonNumber(GithubAttributes.ID).longValueExact());

        // find or persist user account

        var token = authService.buildJwt();

        oidcSession.logout().await().indefinitely();

        return RestResponse.ResponseBuilder
                .seeOther(URI.create("/"))
                .cookie(new NewCookie.Builder("X-TOKEN")
                        .value(token)
                        .path("/")
                        .maxAge(5 * 60)
                        .httpOnly(true)
                        .secure(true)
                        .sameSite(NewCookie.SameSite.STRICT)
                        .build())
                .build();
    }

}
