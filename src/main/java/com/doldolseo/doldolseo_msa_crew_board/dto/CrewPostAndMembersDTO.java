package com.doldolseo.doldolseo_msa_crew_board.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CrewPostAndMembersDTO {
    CrewPostDTO crewPost;
    List<TaggedMemberDTO> taggedMemberList;
}
