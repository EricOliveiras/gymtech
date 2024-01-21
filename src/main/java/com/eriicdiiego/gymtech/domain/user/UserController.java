package com.eriicdiiego.gymtech.domain.user;

import com.eriicdiiego.gymtech.domain.user.payload.request.CreateUserRequest;
import com.eriicdiiego.gymtech.domain.user.payload.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

  @GetMapping
  public ResponseEntity<List<UserResponse>> getAll() {
    return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> getByID(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(service.find(id));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
  }
}
