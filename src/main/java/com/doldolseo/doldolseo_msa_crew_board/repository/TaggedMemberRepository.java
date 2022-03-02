package com.doldolseo.doldolseo_msa_crew_board.repository;

import com.doldolseo.doldolseo_msa_crew_board.domain.TaggedMember;
import com.doldolseo.doldolseo_msa_crew_board.domain.TaggedMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaggedMemberRepository extends JpaRepository<TaggedMember, TaggedMemberId> {
    List<TaggedMember> findAllByCrewPost_CrewPostNo(Long crewPostNo);
}
