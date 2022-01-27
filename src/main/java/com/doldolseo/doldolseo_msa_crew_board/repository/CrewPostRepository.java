package com.doldolseo.doldolseo_msa_crew_board.repository;

import com.doldolseo.doldolseo_msa_crew_board.domain.CrewPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrewPostRepository extends JpaRepository<CrewPost, Long> {
    Page<CrewPost> findAllByCrewNo(Long crewNo, Pageable pageable);
    Page<CrewPost> findAllByCategory(int category, Pageable pageable);
    CrewPost findAllByCrewPostNo(Long crewPostNo);
}
