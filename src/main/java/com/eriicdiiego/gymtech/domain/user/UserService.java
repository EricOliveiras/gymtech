package com.eriicdiiego.gymtech.services;

import com.eriicdiiego.gymtech.entities.User;
import com.eriicdiiego.gymtech.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
  @Autowired
  private UserRepository repository;

  public User save()

  public List<User> findAll() {
    return repository.findAll();
  }

  public User find(Long id) {
    Optional<User> user = repository.findById(id);

    return user.orElse(null);
  }
}
