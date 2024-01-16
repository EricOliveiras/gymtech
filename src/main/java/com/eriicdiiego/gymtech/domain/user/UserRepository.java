package com.eriicdiiego.gymtech.repositories;

import com.eriicdiiego.gymtech.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
