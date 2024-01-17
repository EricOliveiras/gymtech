package com.eriicdiiego.gymtech.domain.user;

import com.eriicdiiego.gymtech.domain.user.payload.request.CreateUserRequest;
import com.eriicdiiego.gymtech.domain.user.payload.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {
  private final IUserService service;

  public UserController(IUserService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<UserResponse> save(@RequestBody CreateUserRequest createUserRequest) {
    UserResponse userResponse = service.save(createUserRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
  }
}
