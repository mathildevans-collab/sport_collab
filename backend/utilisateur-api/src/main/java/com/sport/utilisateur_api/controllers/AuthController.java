package com.sport.utilisateur_api.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sport.utilisateur_api.controllers.dto.AuthDto;
import com.sport.utilisateur_api.exceptions.TokenGenerationException;
import com.sport.utilisateur_api.jwt.JwtUtils;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", exposedHeaders = "AUTHORIZATION")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthController {
	@NonNull
	private final JwtUtils jwtUtils;
	@NonNull
	private final AuthenticationManager authenticationManager;

	@PostMapping("/login/")
	public ResponseEntity<Void> login(@Valid @RequestBody AuthDto authDto) throws TokenGenerationException {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authDto.getLogin(), authDto.getPassword())
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		if (authentication.getPrincipal() instanceof UserDetails user &&
				user.isAccountNonLocked()) {

		}
		String jwt = jwtUtils.generateToken(authentication, authDto.getApp());
		return ResponseEntity
				.ok()
				.header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", jwt)).build();
	}
}
