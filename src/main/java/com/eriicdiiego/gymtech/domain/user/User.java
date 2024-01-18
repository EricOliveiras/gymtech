package com.eriicdiiego.gymtech.domain.user;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;
  @Column(unique = true)
  public String registration;
  public String fullName;
  public String phoneNumber;
  public String password;
  public String birthday;
  public LocalDate createdAt;
  public LocalDate updatedAt;

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDate.now();
    updatedAt = LocalDate.now();
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = LocalDate.now();
  }

  public User() {
  }

  public User(
    Long id,
    String registration,
    String fullName,
    String phoneNumber,
    String password,
    String birthday,
    LocalDate createdAt,
    LocalDate updatedAt
  ) {
    this.id = id;
    this.registration = registration;
    this.fullName = fullName;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.birthday = birthday;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRegistration() {
    return registration;
  }

  public void setRegistration(String registration) {
    this.registration = registration;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public LocalDate getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDate getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDate updatedAt) {
    this.updatedAt = updatedAt;
  }
}
