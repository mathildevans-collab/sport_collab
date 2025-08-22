package com.sport.utilisateur_api.services;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.sport.utilisateur_api.models.User;
import com.sport.utilisateur_api.repository.UserRepository;
import com.sport.utilisateur_api.services.common.AbstractJpaService;


@Service
public class UserService extends AbstractJpaService<User, Long, UserRepository> {

  protected UserService(UserRepository repository){
    super(repository);
  }

  public Optional<User> findByUsernameOrEmail(String value) {
    return  getRepository().findByUsernameOrEmail(value);
  }

}
