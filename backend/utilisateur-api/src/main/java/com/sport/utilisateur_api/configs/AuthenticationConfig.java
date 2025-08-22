package com.sport.utilisateur_api.configs;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.sport.utilisateur_api.services.LoginService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthenticationConfig {
	@NonNull
	private final LoginService loginService;

	@EventListener
	public void onFailure(AuthenticationFailureBadCredentialsEvent event) {
		if (event.getAuthentication().getPrincipal() instanceof String username) {
			loginService.loginFailed(username);
		}
	}

	@EventListener
	public void onSuccess(AuthenticationSuccessEvent event) {
		if (event.getAuthentication().getPrincipal() instanceof UserDetails user) {
			loginService.loginSucceeded(user.getUsername());
		}
	}
}

