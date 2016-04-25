package com.stgconsulting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import lombok.Data;

@Data
@Entity
public class Posting {

  @Id
  @GeneratedValue
  private Long id;
  private String firstName;
  private String lastName;
  private String description;

  @Version
  @JsonIgnore
  private Long version;

  @ManyToOne
  private Recruiter recruiter;

  private Posting() {}

  public Posting(String firstName, String lastName, String description, Recruiter recruiter) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.description = description;
    this.recruiter = recruiter;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public Recruiter getRecruiter() {
    return recruiter;
  }

  public void setRecruiter(Recruiter recruiter) {
    this.recruiter = recruiter;
  }
}
