package com.eriicdiiego.gymtech.domain.user;

import com.eriicdiiego.gymtech.domain.user.payload.request.CreateUserRequest;
import com.eriicdiiego.gymtech.domain.user.payload.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  @Override
  public UserResponse save(CreateUserRequest createUserRequest) {
    User user = userMapper.toEntity(createUserRequest);
    user.password = hashPassword(user.getBirthday());
    user.registration = generateRegistration();
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

  @Override
  public void delete(Long id) {
    var user = userRepository.findById(id);
    if (user.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
    userRepository.deleteById(id);
  }

  private User findUser(Long id) {
    return userRepository
      .findById(id)
      .orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
      );
  }

  private String hashPassword(String password) {
    String birthdayToPassword = convertBirthdayToPassword(password);
    return passwordEncoder.encode(birthdayToPassword);
  }

  private String convertBirthdayToPassword(String birthday) {
    String[] parts = birthday.split("/");
    return parts[0] + parts[1].substring(2) + parts[2].substring(2);
  }

  public static String generateRegistration() {
    int currentYear = java.time.Year.now().getValue();

    String uniqueIdentifier = generateUniqueIdentifier();

    return currentYear + uniqueIdentifier;
  }

  public static String generateUniqueIdentifier() {
    Random random = new Random();
    int randomValue = random.nextInt(1000);
    return String.format("%03d", randomValue);
  }
}
