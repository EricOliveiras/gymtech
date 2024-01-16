package com.eriicdiiego.gymtech.domain.user;

import com.eriicdiiego.gymtech.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
