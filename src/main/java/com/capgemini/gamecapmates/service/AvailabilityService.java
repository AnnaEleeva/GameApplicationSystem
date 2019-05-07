package com.capgemini.gamecapmates.service;

import com.capgemini.gamecapmates.Exceptions.AvailabilityException;
import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.domain.Availability;
import com.capgemini.gamecapmates.dto.AvailabilityDto;
import com.capgemini.gamecapmates.mapper.AvailabilityMapper;
import com.capgemini.gamecapmates.repository.AvailabilityRepository;
import com.capgemini.gamecapmates.repository.UserRepository;
import com.capgemini.gamecapmates.validation.AvailabilityValidator;
import com.capgemini.gamecapmates.validation.UserValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailabilityService {
    private AvailabilityRepository availabilityRepository;
    private AvailabilityMapper availabilityMapper;
    private UserRepository userRepository;
    private UserValidator userValidator;
    private AvailabilityValidator availabilityValidator;

    public AvailabilityService(AvailabilityRepository availabilityRepository, AvailabilityMapper availabilityMapper, UserRepository userRepository,
                               UserValidator userValidator, AvailabilityValidator availabilityValidator) {
        this.availabilityRepository = availabilityRepository;
        this.availabilityMapper = availabilityMapper;
        this.userRepository = userRepository;
        this.userValidator= userValidator;
        this.availabilityValidator= availabilityValidator;
    }

    // rest of Validation
    /**
     * Add when user is available or not to system
     * @param availabilityDto add new availability to user
     * @throws  AvailabilityException- check if availability object is not null
     * @throws  NoSuchUserException- check if user Id is not null
     * */
    public void addAvailabilityHours(AvailabilityDto availabilityDto, Long userId) throws NoSuchUserException, AvailabilityException {
        userValidator.checkIfUserIdIsNull(userId);
        availabilityValidator.checkIfAvailabilityIsNotNull(availabilityDto);

        Availability userAvailability = availabilityMapper.mapDtoToEntity(availabilityDto);
        Long newAvailability = availabilityRepository.add(userAvailability).getId();

        userRepository.findById(userId).getUserAvailabilityHours().add(newAvailability);
    }
    /**
     * Remove availability of user from system
     * @param availabilityId search for availability object in collection
     * @param userId search by user Id
     * @throws  AvailabilityException- check if availability object is not null
     * @throws  NoSuchUserException- check if user Id is not null
     * */
    public void removeAvailabilityHours(Long userId, Long availabilityId) throws NoSuchUserException, AvailabilityException {
        userValidator.checkIfUserIdIsNull(userId);
        availabilityValidator.chechIfIdAvailabilityIsNull(availabilityId);

        List<Long> userAvailability = userRepository.findById(userId).getUserAvailabilityHours();
        userAvailability.remove(availabilityId);
    }
    /**
     * Edit and user can change his availability status
     * @param availabilityDto add new availability to user
     * @param userId search by user Id
     * @throws  AvailabilityException- check if new availability object is not null
     * @throws  NoSuchUserException- check if user Id is not null
     * */
    public AvailabilityDto editAvailabilityHours(AvailabilityDto availabilityDto, Long userId) throws NoSuchUserException, AvailabilityException { // edit with comments
        userValidator.checkIfUserIdIsNull(userId);
        availabilityValidator.checkIfAvailabilityIsNotNull(availabilityDto);

            Availability availability = availabilityMapper.mapDtoToEntity(availabilityDto);
            Availability updatedAvailability = availabilityRepository.edit(availability);

            List<Long> av = userRepository.findById(userId).getUserAvailabilityHours();
            Long updatedAvailabilityId = updatedAvailability.getId();
            for (Long id : av) {
                if (id.equals(updatedAvailabilityId)) {
                    removeAvailabilityHours(userId, id);
                    addAvailabilityHours(availabilityDto, userId);
                }
            }
            return availabilityMapper.mapEntityToDto(updatedAvailability);

    }
}
