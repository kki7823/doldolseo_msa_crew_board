package com.doldolseo.doldolseo_msa_crew_board.service;

import com.doldolseo.doldolseo_msa_crew_board.dto.CrewPostAndMembersWithDTO;
import com.doldolseo.doldolseo_msa_crew_board.dto.CrewPostDTO;
import com.doldolseo.doldolseo_msa_crew_board.dto.CrewPostPageDTO;
import org.springframework.data.domain.Pageable;


public interface CrewPostService {
    CrewPostPageDTO getCrewPostPage(CrewPostDTO dtoIn, Pageable pageable);

    CrewPostAndMembersWithDTO getCrewPostAndMembersWithAndHit(Long crewPostNo);

    CrewPostAndMembersWithDTO getCrewPostAndMembersWith(Long crewPostNo);

    void updateCrewPost(CrewPostDTO dto, Long crewPostNo);

    void deleteCrewPost(Long crewPostNo);

    void saveCrewPost(CrewPostDTO dto, String membersWith);

    void saveMembers(Long crewPostNo, String memberId);

    void deleteMemberInPost(Long membersWithNo);
}
