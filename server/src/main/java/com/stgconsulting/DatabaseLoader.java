package com.stgconsulting;

import com.stgconsulting.model.Posting;
import com.stgconsulting.model.Recruiter;
import com.stgconsulting.repository.PostingRepository;
import com.stgconsulting.repository.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

  private final PostingRepository postings;
  private final RecruiterRepository recruiters;

  @Autowired
  public DatabaseLoader(PostingRepository postingRepository,
                        RecruiterRepository recruiterRepository) {

    this.postings = postingRepository;
    this.recruiters = recruiterRepository;
  }

  @Override
  public void run(String... strings) throws Exception {

    Recruiter greg = this.recruiters.save(new Recruiter("greg", "turnquist",
      "ROLE_ADMIN"));
    Recruiter oliver = this.recruiters.save(new Recruiter("oliver", "gierke",
      "ROLE_ADMIN"));

    SecurityContextHolder.getContext().setAuthentication(
      new UsernamePasswordAuthenticationToken("greg", "doesn't matter",
        AuthorityUtils.createAuthorityList("ROLE_ADMIN")));

    this.postings.save(new Posting("Frodo", "Baggins", "ring bearer", greg));
    this.postings.save(new Posting("Bilbo", "Baggins", "burglar", greg));
    this.postings.save(new Posting("Gandalf", "the Grey", "wizard", greg));

    SecurityContextHolder.getContext().setAuthentication(
      new UsernamePasswordAuthenticationToken("oliver", "doesn't matter",
        AuthorityUtils.createAuthorityList("ROLE_ADMIN")));

    this.postings.save(new Posting("Samwise", "Gamgee", "gardener", oliver));
    this.postings.save(new Posting("Merry", "Brandybuck", "pony rider", oliver));
    this.postings.save(new Posting("Peregrin", "Took", "pipe smoker", oliver));

    SecurityContextHolder.clearContext();
  }
}
