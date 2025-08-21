package com.sport.utilisateur_api.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sport.utilisateur_api.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


	@Query("select u from User u where u.username = :value or u.email = :value")
	Optional<User> findByUsernameOrEmail(String value);


}
