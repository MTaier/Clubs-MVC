package com.springboot.mvc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springboot.mvc.dto.ClubDto;
import com.springboot.mvc.models.Club;
import com.springboot.mvc.models.UserEntity;
import com.springboot.mvc.repository.ClubRepository;
import com.springboot.mvc.repository.UserRepository;
import com.springboot.mvc.security.SecurityUtil;
import com.springboot.mvc.service.ClubService;
import com.springboot.mvc.mapper.ClubMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;

    @Override
    public List<ClubDto> getAllClubs() {

        List<Club> clubs = clubRepository.findAll();

        return clubs.stream().map(ClubMapper::mapToClubDto).collect(Collectors.toList());

    }

    @Override
    public Club saveClub(ClubDto clubDto) {

        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Club club = ClubMapper.mapToClub(clubDto);
        club.setCreatedBy(user);
        return clubRepository.save(club);
    }

    @Override
    public ClubDto findClubById(long clubId) {

        Club club = clubRepository.findById(clubId).get();
        return ClubMapper.mapToClubDto(club);

    }

    @Override
    public void updateClub(ClubDto clubDto) {

        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Club club = ClubMapper.mapToClub(clubDto);
        club.setCreatedBy(user);
        clubRepository.save(club);

    }

    @Override
    public void delete(Long clubId) {

        clubRepository.deleteById(clubId);

    }

    @Override
    public List<ClubDto> searchClubs(String query) {

        List<Club> clubs = clubRepository.searchClubs(query);
        return clubs.stream().map(ClubMapper::mapToClubDto).collect(Collectors.toList());

    }
}
