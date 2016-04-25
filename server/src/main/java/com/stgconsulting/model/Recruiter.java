package com.stgconsulting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@ToString(exclude = "password")
@Entity
public class Recruiter {

  public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @JsonIgnore
  private String password;

  private String[] roles;

  public void setPassword(String password) {
    this.password = PASSWORD_ENCODER.encode(password);
  }

  public Recruiter() {}

  public Recruiter(String name, String password, String... roles) {
    this.name = name;
    this.setPassword(password);
    this.roles = roles;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public String[] getRoles() {
    return roles;
  }

  public void setRoles(String[] roles) {
    this.roles = roles;
  }
}
