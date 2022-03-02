package com.doldolseo.doldolseo_msa_crew_board.configuration;

import com.doldolseo.doldolseo_msa_crew_board.utils.UploadCrewPostFileUtil;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class CrewPostConfiguration {
    //Entity-DTO 간 변환
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public UploadCrewPostFileUtil uploadReviewFileUtil(){
        return new UploadCrewPostFileUtil(uploadPath());
    }

    @Bean(name = "uploadPath")
    public String uploadPath() {
        return System.getProperty("user.dir") + "/src/main/resources/static/";
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
        return new MappingJackson2HttpMessageConverter();
    }

    //HTTP hidden Method : delete, put, patch ..
    @Bean
    public HiddenHttpMethodFilter httpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }
}
