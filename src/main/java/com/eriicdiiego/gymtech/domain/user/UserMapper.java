package com.eriicdiiego.gymtech.domain.user;

import com.eriicdiiego.gymtech.domain.user.payload.request.CreateUserRequest;
import com.eriicdiiego.gymtech.domain.user.payload.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
  User toEntity(CreateUserRequest createUserRequest);

  UserResponse toDto(User user);

  List<UserResponse> toDto(List<User> users);
}
