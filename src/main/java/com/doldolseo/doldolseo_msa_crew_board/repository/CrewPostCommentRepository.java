package com.doldolseo.doldolseo_msa_crew_board.repository;

import com.doldolseo.doldolseo_msa_crew_board.domain.CrewPostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrewPostCommentRepository extends JpaRepository<CrewPostComment, Long> {
    List<CrewPostComment> findAllByCrewPost_CrewPostNo(Long crewPostNo);
    CrewPostComment findByCommentNo(Long commentNo);
}
