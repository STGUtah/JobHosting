package com.stgconsulting.handler;

import com.stgconsulting.model.Posting;
import com.stgconsulting.model.Recruiter;
import com.stgconsulting.repository.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Posting.class)
public class RestEventHandler {

  private final RecruiterRepository recruiterRepository;

  @Autowired
  public RestEventHandler(RecruiterRepository recruiterRepository) {
    this.recruiterRepository = recruiterRepository;
  }

  @HandleBeforeCreate
  public void applyUserInformationUsingSecurityContext(Posting posting) {

    String name = SecurityContextHolder.getContext().getAuthentication().getName();
    Recruiter recruiter = this.recruiterRepository.findByName(name);
    if (recruiter == null) {
      Recruiter newRecruiter = new Recruiter();
      newRecruiter.setName(name);
      newRecruiter.setRoles(new String[]{"ROLE_ADMIN"});
      recruiter = this.recruiterRepository.save(newRecruiter);
    }
    posting.setRecruiter(recruiter);
  }
}
