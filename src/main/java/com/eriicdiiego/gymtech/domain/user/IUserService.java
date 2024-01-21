package com.eriicdiiego.gymtech.domain.user;

import com.eriicdiiego.gymtech.domain.user.payload.request.CreateUserRequest;
import com.eriicdiiego.gymtech.domain.user.payload.response.UserResponse;

import java.text.ParseException;
import java.util.List;

public interface IUserService {
  UserResponse save(CreateUserRequest createUserRequest);

  UserResponse find(Long id);

  List<UserResponse> findAll();

  void delete(Long id);
}
