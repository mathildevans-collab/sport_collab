package com.sport.utilisateur_api.init;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.sport.utilisateur_api.models.User;
import com.sport.utilisateur_api.models.common.references.Role;
import com.sport.utilisateur_api.services.UserService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInit implements CommandLineRunner {
	private static final Faker faker = Faker.instance();

	@NonNull
	private final UserService userServiceImpl;

	@NonNull
	private final PasswordEncoder passwordEncoder;

	@Value("${app.init.default.email}")
	private String defaultEmail = "admin@admin.fr";

	@Value("${app.init.default.username}")
	private String defaultUsername = "admin";

	@Value("${app.init.default.password}")
	private String defaultPassword = "admin";

	@Override
	public void run(String... args) throws Exception {
		initUser();
	}

	private void initUser() throws Exception {
		if (this.userServiceImpl.count() == 0) {
			User user = new User();
			user.setUsername(defaultUsername);
			user.setPassword(passwordEncoder.encode(defaultPassword));
			user.setRole(Role.ADMIN);
			user.setEmail(defaultEmail);
			user.setFailedAttempt(0);
			user.setAccountNonLocked(true);
			user.setLockTime(null);
			userServiceImpl.save(user);

			for (int i = 0; i < 200; i++) {
				user = new User();
				user.setUsername(faker.name().username());
				user.setPassword(passwordEncoder.encode("123"));
				user.setRole(Role.USER);
				user.setEmail(String.format("%s@intradef.gouv.fr", user.getUsername()));
				user.setFailedAttempt(0);
				user.setAccountNonLocked(true);
				user.setLockTime(null);
				userServiceImpl.save(user);
			}
		}
	}
}

