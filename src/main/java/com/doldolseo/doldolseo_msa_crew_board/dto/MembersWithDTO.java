package com.doldolseo.doldolseo_msa_crew_board.dto;

import com.doldolseo.doldolseo_msa_crew_board.domain.CrewPost;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MembersWithDTO {
    private Long crewMemberWithNo;
    private CrewPost crewPost;
    private String memberId;
}
