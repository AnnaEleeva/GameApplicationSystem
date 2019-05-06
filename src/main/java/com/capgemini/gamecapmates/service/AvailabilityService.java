package com.capgemini.gamecapmates.service;

import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.domain.Availability;
import com.capgemini.gamecapmates.dto.AvailabilityDto;
import com.capgemini.gamecapmates.mapper.AvailabilityMapper;
import com.capgemini.gamecapmates.repository.AvailabilityRepository;
import com.capgemini.gamecapmates.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailabilityService {
    private AvailabilityRepository availabilityRepository;
    private AvailabilityMapper availabilityMapper;
    private UserRepository userRepository;

    public AvailabilityService(AvailabilityRepository availabilityRepository, AvailabilityMapper availabilityMapper, UserRepository userRepository) {
        this.availabilityRepository = availabilityRepository;
        this.availabilityMapper = availabilityMapper;
        this.userRepository = userRepository;
    }

    public void addAvailabilityHours(AvailabilityDto availabilityDto, Long userId) throws NoSuchUserException {
        Availability userAvailability = availabilityMapper.mapDtoToEntity(availabilityDto);
        Long newAvailability = availabilityRepository.add(userAvailability).getId();

        userRepository.findById(userId).getUserAvailabilityHours().add(newAvailability);
    }

    public void removeAvailabilityHours(Long userId, Long availabilityId) throws NoSuchUserException {
        List<Long> userAvailability = userRepository.findById(userId).getUserAvailabilityHours();
        userAvailability.remove(availabilityId);
    }

    public AvailabilityDto editAvailabilityHours() { // edit with comments
        return null;
    }
}
