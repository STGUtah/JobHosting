package com.stgconsulting.service;

import com.stgconsulting.model.Recruiter;
import com.stgconsulting.repository.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserDetailsService implements UserDetailsService {

  private final RecruiterRepository repository;

  @Autowired
  public CurrentUserDetailsService(RecruiterRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    Recruiter recruiter = this.repository.findByName(name);
    return new User(recruiter.getName(), recruiter.getPassword(),
      AuthorityUtils.createAuthorityList(recruiter.getRoles()));
  }

}
