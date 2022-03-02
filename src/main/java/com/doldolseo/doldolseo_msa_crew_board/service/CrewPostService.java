package com.doldolseo.doldolseo_msa_crew_board.service;

import com.doldolseo.doldolseo_msa_crew_board.domain.TaggedMemberId;
import com.doldolseo.doldolseo_msa_crew_board.dto.CrewPostAndMembersDTO;
import com.doldolseo.doldolseo_msa_crew_board.dto.CrewPostDTO;
import com.doldolseo.doldolseo_msa_crew_board.dto.CrewPostPageDTO;
import org.springframework.data.domain.Pageable;


public interface CrewPostService {
    CrewPostPageDTO getCrewPostPage(CrewPostDTO dtoIn, Pageable pageable);

    CrewPostAndMembersDTO getCrewPostAndMembersAndHit(Long crewPostNo);

    CrewPostAndMembersDTO getCrewPostAndMembers(Long crewPostNo);

    String getWriterId(Long crewPostNo);

    void updateCrewPost(CrewPostDTO dto, Long crewPostNo);

    void deleteCrewPost(Long crewPostNo);

    void deleteCrewPostByMemberId(String memberId);

    void deleteCrewPostByCrewNo(Long crewNo);

    void saveCrewPost(CrewPostDTO dto, String membersWith);

    void saveMembers(Long crewPostNo, String memberId);

    void deleteTaggedMember(TaggedMemberId taggedMemberId);
}
