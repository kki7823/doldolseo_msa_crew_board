package com.doldolseo.doldolseo_msa_crew_board.repository;

import com.doldolseo.doldolseo_msa_crew_board.domain.MembersWith;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MembersWithRepository extends JpaRepository<MembersWith, Long> {
    List<MembersWith> findAllByCrewPost_CrewPostNo(Long crewPostNo);
}
