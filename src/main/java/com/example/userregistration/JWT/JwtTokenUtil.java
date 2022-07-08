package com.example.userregistration.JWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {
    private static final String TOKEN_SECRET="Radhey";
    public static String createToken(Long id) {

        Algorithm algo = Algorithm.HMAC256(TOKEN_SECRET);
        String token = JWT.create().withClaim("id_key", id).sign(algo);

        return token;
    }

    public Long decodeToken(String token) throws SignatureVerificationException {

        Saml2RelyingPartyProperties.AssertingParty.Verification verification = (Saml2RelyingPartyProperties.AssertingParty.Verification) JWT.require(Algorithm.HMAC256(TOKEN_SECRET));

        JWTVerifier jwtVerifier = ((Verification) verification).build();

        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        Claim idClaim = decodedJWT.getClaim("id_key");
        Long id = idClaim.asLong();

        return id;
    }

 }

