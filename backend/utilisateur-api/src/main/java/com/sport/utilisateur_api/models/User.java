package com.sport.utilisateur_api.models;


import java.time.LocalDateTime;
import com.sport.utilisateur_api.models.common.AbstractPersistableWithIdSetter;
import com.sport.utilisateur_api.models.common.references.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users", uniqueConstraints ={
  @UniqueConstraint(name = "uk__users__username", columnNames = {"username"}),
  @UniqueConstraint(name = "uk_users__email", columnNames = {"email"})
}) 
@EqualsAndHashCode(callSuper = false, of = {"username"})
@ToString(callSuper = true,of = {"password","email"})
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class User extends AbstractPersistableWithIdSetter<Long> {

  @Getter
  @Setter
  @NotBlank
  @Column(name = "username",nullable = false)
  private String username;

  @Getter
  @Setter
  @NotBlank
  @Column(name = "email",nullable = false)
  private String email;

  @Getter
  @Setter
  @NotBlank
  @Column(name = "password",nullable = false)
  private String password;

  @Getter
  @Setter
  @Enumerated(EnumType.STRING)
  @Column(name = "role",nullable = false)
  private Role role;

  @Getter
  @Setter
  @Column(name = "not_locked", nullable = false)
  private boolean accountNonLocked;

  @Getter
  @Setter
  @Column(name = "failed_attempt", nullable = false)
  private Integer failedAttempt;

  @Getter
  @Setter
  @Column(name = "lock_time", nullable = false)
  private LocalDateTime lockTime;


}
