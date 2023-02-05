package io.quarkus.reporoducer30893.auth.jwt;

import io.quarkus.reporoducer30893.auth.cons.JwtClaims;
import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.auth.principal.ParseException;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

@ApplicationScoped
public class AuthService {

    private final JWTParser parser;
    private final String issuer;

    public AuthService(JWTParser parser, @ConfigProperty(name = "mp.jwt.verify.issuer") String issuer) {
        this.parser = parser;
        this.issuer = issuer;
    }

    public JsonWebToken validateJwt(String jwt) throws ParseException {
        return parser.parse(jwt);
    }

    public String buildJwt() {
        return Jwt.issuer(issuer)
                .issuedAt(Instant.now())
                .expiresIn(Duration.ofSeconds(60 * 5))
                .groups(Set.of("USER", "ADMIN"))
                .upn("username")
                .claim(JwtClaims.PREFERRED_USERNAME, "username")
                .claim(JwtClaims.USER_ACCOUNT_ID, 666)
                .claim(JwtClaims.NAME, "name")
                .claim(JwtClaims.EMAIL, "email")
                .claim(JwtClaims.AVATAR_URL, "http://avarar/img.jpg")
                .claim(JwtClaims.EXTERNAL_ID, 666)
                .sign();
    }

}
