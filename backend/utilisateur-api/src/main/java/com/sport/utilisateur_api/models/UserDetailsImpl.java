package com.sport.utilisateur_api.models;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDetailsImpl implements UserDetails {

  @Getter
  @Setter
  @NonNull
  private Long id;

  @Getter
  @Setter
  @NonNull
  private String username;

  @Getter
  @Setter
  @NonNull
  private String password;

  @Getter
  @Setter
  @NonNull
  private String email;

  @Getter
  @Setter
  @NonNull
  private Boolean accountNonLocked;

  @Getter
  @Setter
  @NonNull
  private Collection<? extends GrantedAuthority> authorities;



  public boolean isAccountNonLocked(){
    return getAccountNonLocked();
  }

  public static UserDetailsImpl build(User user) {
		return new UserDetailsImpl(
				Objects.requireNonNull(user.getId()),
				user.getUsername(),
				user.getPassword(),
				user.getEmail(),
				user.isAccountNonLocked(),
				user.getRole()
						.getAllRole()
						.stream()
						.map(role -> (GrantedAuthority) () -> String.format("ROLE_%s", role.name()))
						.toList()
		);
	}

  

}
