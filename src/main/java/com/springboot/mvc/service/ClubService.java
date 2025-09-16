package com.springboot.mvc.service;

import java.util.List;

import com.springboot.mvc.dto.ClubDto;
import com.springboot.mvc.models.Club;

public interface ClubService {

    List<ClubDto> getAllClubs();

    Club saveClub(ClubDto clubDto);

    ClubDto findClubById(long clubId);

    void updateClub(ClubDto clubDto);

    void delete(Long clubId);

    List<ClubDto> searchClubs(String query);

}
