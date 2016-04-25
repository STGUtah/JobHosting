package com.stgconsulting.repository;

import com.stgconsulting.model.Recruiter;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface RecruiterRepository extends Repository<Recruiter, Long> {
  Recruiter save(Recruiter user);

  Recruiter findByName(String name);
}
