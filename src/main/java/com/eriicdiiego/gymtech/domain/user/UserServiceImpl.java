package com.eriicdiiego.gymtech.domain.user;

import com.eriicdiiego.gymtech.domain.user.payload.request.CreateUserRequest;
import com.eriicdiiego.gymtech.domain.user.payload.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  @Override
  public UserResponse save(CreateUserRequest createUserRequest) {
    User user = userMapper.toEntity(createUserRequest);
    return userMapper.toDto(userRepository.save(user));
  }

  @Override
  public UserResponse find(Long id) {
    return userMapper.toDto(findUser(id));
  }

  @Override
  public List<UserResponse> findAll() {
    Page<User> users = userRepository.findAll(
      PageRequest.of(0, 20, Sort.by("fullName"))
    );
    return users
      .stream()
      .map(userMapper::toDto)
      .collect(Collectors.toList());
  }

  private User findUser(Long id) {
    return userRepository
      .findById(id)
      .orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
      );
  }
}
