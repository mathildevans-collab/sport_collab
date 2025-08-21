package com.sport.utilisateur_api.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sport.utilisateur_api.models.User;
import com.sport.utilisateur_api.models.UserDetailsImpl;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDetailsServiceImpl implements UserDetailsService {

  @NonNull
  private final UserService userService;

	@NonNull
	private final LoginService loginService;

  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userService
    .findByUsernameOrEmail(username)
    .orElseThrow(() -> new UsernameNotFoundException("user not found"));

    loginService.unlockIfTimeExpired(user);

    return UserDetailsImpl.build(user);

  }

}
