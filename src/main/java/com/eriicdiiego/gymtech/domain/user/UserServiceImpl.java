package com.eriicdiiego.gymtech.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {
  @Autowired
  private UserRepository repository;

  public List<User> findAll() {
    return repository.findAll();
  }

  public User find(Long id) {
    Optional<User> user = repository.findById(id);

    return user.orElse(null);
  }
}
