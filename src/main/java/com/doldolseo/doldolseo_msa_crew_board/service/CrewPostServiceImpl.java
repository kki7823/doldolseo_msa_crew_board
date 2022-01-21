package com.doldolseo.doldolseo_msa_crew_board.service;

import com.doldolseo.doldolseo_msa_crew_board.repository.CrewMemberWithRepository;
import com.doldolseo.doldolseo_msa_crew_board.repository.CrewPostRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CrewPostServiceImpl implements CrewPostService{
    @Autowired
    CrewPostRepository crewPostRepository;
    @Autowired
    CrewMemberWithRepository crewMemberWithRepository;
}
