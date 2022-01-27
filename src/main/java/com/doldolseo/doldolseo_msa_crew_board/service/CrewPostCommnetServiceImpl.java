package com.doldolseo.doldolseo_msa_crew_board.service;

import com.doldolseo.doldolseo_msa_crew_board.domain.CrewPost;
import com.doldolseo.doldolseo_msa_crew_board.domain.CrewPostComment;
import com.doldolseo.doldolseo_msa_crew_board.dto.CrewPostCommentDTO;
import com.doldolseo.doldolseo_msa_crew_board.repository.CrewPostCommentRepository;
import com.doldolseo.doldolseo_msa_crew_board.repository.CrewPostRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CrewPostCommnetServiceImpl implements CrewPostCommentService {
    @Autowired
    CrewPostCommentRepository commentRepository;
    @Autowired
    CrewPostRepository crewPostRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<CrewPostCommentDTO> getComments(Long crewPostNo) {
        List<CrewPostComment> commentEntity = commentRepository.findAllByCrewPost_CrewPostNo(crewPostNo);
        return entityListToDtoList(commentEntity);
    }

    @Override
    public CrewPostCommentDTO insertComment(CrewPostCommentDTO dto) {
        setWDate(dto);
        setCrewPost(dto);
        CrewPostComment comment = commentRepository.save(dtoToEntity(dto));
        return entityToDto(comment);
    }

    public void setWDate(CrewPostCommentDTO dto) {
        dto.setWDate(LocalDateTime.now());
    }

    public void setCrewPost(CrewPostCommentDTO dto) {
        CrewPost crewPost = crewPostRepository.findAllByCrewPostNo(dto.getCrewPost().getCrewPostNo());
        dto.setCrewPost(crewPost);
    }

    @Override
    @Transactional
    public void updateComment(Long commentNo, CrewPostCommentDTO dto) {
        CrewPostComment comment = commentRepository.findByCommentNo(commentNo);
        comment.setWDate(LocalDateTime.now());
        comment.setContent(dto.getContent());
    }

    @Override
    public void deleteComment(Long commentNo) {
        commentRepository.deleteById(commentNo);
    }

    public CrewPostComment dtoToEntity(CrewPostCommentDTO dto) {
        return modelMapper.map(dto, CrewPostComment.class);
    }

    public CrewPostCommentDTO entityToDto(CrewPostComment comment) {
        return modelMapper.map(comment, CrewPostCommentDTO.class);
    }

    public List<CrewPostCommentDTO> entityListToDtoList(List<CrewPostComment> commentList) {
        return modelMapper.map(commentList, new TypeToken<List<CrewPostCommentDTO>>() {
        }.getType());
    }
}
