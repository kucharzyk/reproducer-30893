package io.quarkus.reporoducer30893.extensions;

import io.quarkus.arc.Arc;
import io.quarkus.qute.TemplateExtension;
import io.quarkus.reporoducer30893.auth.cons.JwtClaims;
import io.quarkus.security.identity.SecurityIdentity;
import org.eclipse.microprofile.jwt.JsonWebToken;

@TemplateExtension(namespace = "auth")
public class AuthExtensions {

    private static SecurityIdentity securityIdentity() {
        return Arc.container().select(SecurityIdentity.class).get();
    }

    public static JsonWebToken principal() {
        return (JsonWebToken) securityIdentity().getPrincipal();
    }

    public static String fullName() {
        return principal().getClaim(JwtClaims.NAME);
    }

    public static String avatarUrl() {
        return principal().getClaim(JwtClaims.AVATAR_URL);
    }

    public static Boolean isAuthenticated() {
        return !securityIdentity().getRoles().isEmpty();
    }

    public static Boolean isAdmin() {
        return !securityIdentity().getRoles().isEmpty();
    }

}
