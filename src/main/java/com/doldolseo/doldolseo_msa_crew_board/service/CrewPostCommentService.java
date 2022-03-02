package com.doldolseo.doldolseo_msa_crew_board.service;

import com.doldolseo.doldolseo_msa_crew_board.dto.CrewPostCommentDTO;
import com.doldolseo.doldolseo_msa_crew_board.dto.CrewPostCommentsDTO;

import java.util.List;

public interface CrewPostCommentService {
    CrewPostCommentsDTO getComments(Long crewPostNo);

    String getCommentWriter(Long commentNo);

    CrewPostCommentDTO insertComment(CrewPostCommentDTO dto);

    void updateComment(Long commentNo, CrewPostCommentDTO dto);

    void deleteComment(Long commentNo);
}
