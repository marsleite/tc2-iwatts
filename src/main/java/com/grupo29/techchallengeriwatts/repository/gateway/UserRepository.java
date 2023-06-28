package com.grupo29.techchallengeriwatts.repository.gateway;

import com.grupo29.techchallengeriwatts.domain.User;
import com.grupo29.techchallengeriwatts.repository.entity.UserEntity;

public interface UserRepository {

  public UserEntity createUser(User user);
}
