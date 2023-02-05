package io.quarkus.reporoducer30893.auth.oidc;

import io.quarkus.oidc.TenantResolver;
import io.vertx.ext.web.RoutingContext;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OidcTenantResolver implements TenantResolver {
    @Override
    public String resolve(RoutingContext context) {
        if (context.normalizedPath().startsWith("/auth/github")) {
            return "github";
        }
        return null;
    }
}
