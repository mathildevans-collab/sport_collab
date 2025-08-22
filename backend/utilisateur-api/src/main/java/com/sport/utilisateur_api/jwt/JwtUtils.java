package com.sport.utilisateur_api.jwt;

import java.security.KeyPair;
import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.sport.utilisateur_api.exceptions.TokenGenerationException;
import com.sport.utilisateur_api.models.UserDetailsImpl;

import io.jsonwebtoken.Jwts;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class JwtUtils {

  @Value("${app.security.jwt.expiration}")
	private int jwtExpiration = 86_400_000;

	@NonNull
	private KeyPair rsaKeyPair;

	public String generateToken(Authentication authentication, String app) throws TokenGenerationException {
		if (authentication.getPrincipal() instanceof UserDetailsImpl userDetails) {
			String role = userDetails
					.getAuthorities()
					.stream()
					.findFirst()
					.map(GrantedAuthority::getAuthority)
					.orElse("");

			return Jwts.builder()
					.signWith(rsaKeyPair.getPrivate(), Jwts.SIG.RS256)
					.subject(userDetails.getId().toString())
					.issuedAt(new Date())
					.expiration(Date.from(Instant.now().plusMillis(jwtExpiration)))
					.claim("role", String.join("", role.split("ROLE_")))
					.claim("username", userDetails.getUsername())
					.claim("email", userDetails.getEmail())
					.claim("app", app)
					.compact();
		}
		throw new TokenGenerationException("invalid authentication");
	}

}
