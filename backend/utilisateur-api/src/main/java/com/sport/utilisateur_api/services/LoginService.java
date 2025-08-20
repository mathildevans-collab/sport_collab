package com.sport.utilisateur_api.services;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.sport.utilisateur_api.models.User;
import com.sport.utilisateur_api.repository.UserRepository;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginService {
  private static final int MAX_FAILED_ATTEMPTS = 3;
	private static final long LOCK_TIME_DURATION = 15;

	@NonNull
	private final UserRepository userRepository;

	public void loginFailed(String username) {
		User user = userRepository.findByUsernameOrEmail(username).orElse(null);
		if (Objects.nonNull(user)) {
			int attempts = user.getFailedAttempt() + 1;
			user.setFailedAttempt(attempts);

			if (attempts >= MAX_FAILED_ATTEMPTS) {
				user.setAccountNonLocked(false);
				user.setLockTime(LocalDateTime.now());
			}

			userRepository.save(user);
		}
    
	}

	public void loginSucceeded(String username) {
		User user = userRepository.findByUsernameOrEmail(username).orElse(null);
		if (Objects.nonNull(user)) {
			user.setFailedAttempt(0);
			user.setAccountNonLocked(true);
			user.setLockTime(null);
			userRepository.save(user);
		}
	}

	public void unlockIfTimeExpired(User user) {
		if (!user.isAccountNonLocked() && Objects.nonNull(user.getLockTime())) {
			if (user.getLockTime().plusMinutes(LOCK_TIME_DURATION).isBefore(LocalDateTime.now())) {
				user.setAccountNonLocked(true);
				user.setFailedAttempt(0);
				user.setLockTime(null);
				userRepository.save(user);
			}
		}
	}

}
