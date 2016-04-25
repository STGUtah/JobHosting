package com.stgconsulting.repository;

import com.stgconsulting.model.Posting;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface PostingRepository extends PagingAndSortingRepository<Posting, Long> {

    @Override
    @PreAuthorize("#posting?.recruiter == null or #posting?.recruiter?.name == authentication?.name")
    Posting save(@Param("posting") Posting posting);

    @Override
    @PreAuthorize("@postingRepository.findOne(#id)?.recruiter?.name == authentication?.name")
    void delete(@Param("id") Long id);

    @Override
    @PreAuthorize("#posting?.recruiter?.name == authentication?.name")
    void delete(@Param("posting") Posting posting);

}
