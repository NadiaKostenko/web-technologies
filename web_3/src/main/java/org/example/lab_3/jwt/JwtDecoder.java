package org.example.lab_3.jwt;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.example.lab_3.openidconnect.OpenIdConnectProperties;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.util.List;

@Service
public class JwtDecoder {

    private final OpenIdConnectProperties openIdConnectProperties;

    public JwtDecoder (OpenIdConnectProperties openIdConnectProperties) {
        this.openIdConnectProperties = openIdConnectProperties;
    }

    public Claims decodeToken(String token) {
        try {
            JWKSet jwkSet = JWKSet.load(new URL(openIdConnectProperties.getOpenIdConnectEndpoint() + "/.well-known/jwks"));
            List<JWK> keys = jwkSet.getKeys();

            if (keys.isEmpty()) {
                throw new RuntimeException("No keys found in JWK Set");
            }

            JWK jwk = keys.get(0);

            RSAPublicKey publicKey = (RSAPublicKey) jwk.toRSAKey().toPublicKey();

            return Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
                throw new RuntimeException("Failed to decode token", e);

        }
    }
}