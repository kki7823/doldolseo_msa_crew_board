package com.doldolseo.doldolseo_msa_crew_board.service;

import com.doldolseo.doldolseo_msa_crew_board.domain.MembersWith;
import com.doldolseo.doldolseo_msa_crew_board.domain.CrewPost;
import com.doldolseo.doldolseo_msa_crew_board.dto.CrewPostAndMembersWithDTO;
import com.doldolseo.doldolseo_msa_crew_board.dto.CrewPostDTO;
import com.doldolseo.doldolseo_msa_crew_board.dto.MembersWithDTO;
import com.doldolseo.doldolseo_msa_crew_board.dto.CrewPostPageDTO;
import com.doldolseo.doldolseo_msa_crew_board.repository.MembersWithRepository;
import com.doldolseo.doldolseo_msa_crew_board.repository.CrewPostRepository;
import com.doldolseo.doldolseo_msa_crew_board.utils.PagingParam;
import org.hibernate.mapping.Collection;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class CrewPostServiceImpl implements CrewPostService {
    @Autowired
    CrewPostRepository crewPostRepository;
    @Autowired
    MembersWithRepository membersWithRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public CrewPostPageDTO getCrewPostPage(CrewPostDTO dtoIn, Pageable pageable) {
        CrewPostPageDTO crewPostPageDTO = new CrewPostPageDTO();

        Page<CrewPostDTO> crewPostPage;

        if (dtoIn.getCrewNo() != 0)
            crewPostPage = getCrewPostPage(dtoIn.getCrewNo(), pageable);
        else if (dtoIn.getCategory() != 0)
            crewPostPage = getCrewPostPage(dtoIn.getCategory(), pageable);
        else
            crewPostPage = getCrewPostPage(pageable);

        PagingParam pagingParam = new PagingParam(5, crewPostPage);

        crewPostPageDTO.setCrewPostList(crewPostPage.getContent());
        crewPostPageDTO.setEndBlockPage(pagingParam.getStartBlockPage());
        crewPostPageDTO.setEndBlockPage(pagingParam.getEndBlockPage());
        crewPostPageDTO.setTotalPages(pagingParam.getTotalPages());

        return crewPostPageDTO;
    }

    public Page<CrewPostDTO> getCrewPostPage(Pageable pageable) {
        return entityPageToDtoPage(crewPostRepository.findAll(pageable));
    }

    public Page<CrewPostDTO> getCrewPostPage(Long crewNo, Pageable pageable) {
        return entityPageToDtoPage(crewPostRepository.findAllByCrewNo(crewNo, pageable));
    }

    public Page<CrewPostDTO> getCrewPostPage(int category, Pageable pageable) {
        return entityPageToDtoPage(crewPostRepository.findAllByCategory(category, pageable));
    }

    @Override
    public CrewPostAndMembersWithDTO getCrewPostAndMembersWithAndHit(Long crewPostNo) {
        increaseHit(crewPostRepository.findAllByCrewPostNo(crewPostNo));
        return getCrewPostAndMembersWith(crewPostNo);
    }

    @Transactional
    public void increaseHit(CrewPost crewPost) {
        crewPost.setHit(crewPost.getHit() + 1);
    }

    @Override
    public CrewPostAndMembersWithDTO getCrewPostAndMembersWith(Long crewPostNo) {
        CrewPostAndMembersWithDTO dto = new CrewPostAndMembersWithDTO();
        dto.setCrewPostDTO(getCrewPost(crewPostNo));
        dto.setMembersWithDTOList(getMembersWithList(crewPostNo));
        return dto;
    }

    public CrewPostDTO getCrewPost(Long crewPostNo) {
        return entityToDto(crewPostRepository.getById(crewPostNo));
    }

    public List<MembersWithDTO> getMembersWithList(Long crewPostNo) {
        return entityListToDtoList(membersWithRepository.findAllByCrewPost_CrewPostNo(crewPostNo));
    }

    @Override
    public void saveCrewPost(CrewPostDTO dto, String membersWith) {
        dto.setHit(0);
        dto.setWDate(LocalDateTime.now());
        CrewPost crewPost = crewPostRepository.save((CrewPost) dtoToEntity(dto));
        saveWithMembers(membersWith, crewPost);
    }

    @Override
    @Transactional
    public void updateCrewPost(CrewPostDTO dto, Long crewPostNo) {
        CrewPost crewPost = crewPostRepository.getById(crewPostNo);
        crewPost.setCategory(dto.getCategory());
        crewPost.setTitle(dto.getTitle());
        crewPost.setContent(dto.getContent());
    }

    @Override
    public void deleteCrewPost(Long crewPostNo) {
        crewPostRepository.deleteById(crewPostNo);
    }

    public void saveWithMembers(String membersWith, CrewPost crewPost) {
        membersWith = membersWith.substring(1, membersWith.length() - 1);
        if (membersWith.length() == 0) return;

        membersWith = membersWith.replace("\"", "");
        String[] membersArray = membersWith.split(",");

        for (String memberId : membersArray) {
            MembersWithDTO dto = new MembersWithDTO();
            dto.setMemberId(memberId);
            dto.setCrewPost(crewPost);

            membersWithRepository.save((MembersWith) dtoToEntity(dto));
        }
    }

    @Override
    @Transactional
    public void saveMembers(Long crewPostNo, String memberId) {
        if (crewPostNo == 0) return;

        MembersWithDTO dto = new MembersWithDTO();
        dto.setCrewPost(crewPostRepository.getById(crewPostNo));
        dto.setMemberId(memberId);
        membersWithRepository.save((MembersWith) dtoToEntity(dto));
    }

    @Override
    public void deleteMemberInPost(Long membersWithNo) {
        membersWithRepository.deleteById(membersWithNo);
    }

    public Object dtoToEntity(CrewPostDTO dto) {
        return modelMapper.map(dto, CrewPost.class);
    }

    public Object dtoToEntity(MembersWithDTO dto) {
        return modelMapper.map(dto, MembersWith.class);
    }

    public CrewPostDTO entityToDto(CrewPost crewPost) {
        return modelMapper.map(crewPost, CrewPostDTO.class);
    }

    public MembersWithDTO entityToDto(MembersWith crewMemberWith) {
        return modelMapper.map(crewMemberWith, MembersWithDTO.class);
    }

    public Page<CrewPostDTO> entityPageToDtoPage(Page<CrewPost> crewPostPage) {
        return modelMapper.map(crewPostPage, new TypeToken<Page<CrewPostDTO>>() {
        }.getType());
    }

    public List<MembersWithDTO> entityListToDtoList(List<MembersWith> membersWithList) {
        return modelMapper.map(membersWithList, new TypeToken<List<MembersWithDTO>>() {
        }.getType());
    }

}
