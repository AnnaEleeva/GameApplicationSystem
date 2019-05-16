package com.gamecapmates.service;

import com.gamecapmates.Exceptions.AvailabilityException;
import com.gamecapmates.Exceptions.NoSuchUserException;
import com.gamecapmates.domain.Availability;
import com.gamecapmates.dto.AvailabilityDto;
import com.gamecapmates.enums.Disponibility;
import com.gamecapmates.mapper.AvailabilityMapper;
import com.gamecapmates.repository.AvailabilityRepository;
import com.gamecapmates.repository.UserRepository;
import com.gamecapmates.validation.AvailabilityValidator;
import com.gamecapmates.validation.UserValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        this.userValidator = userValidator;
        this.availabilityValidator = availabilityValidator;
    }

    public AvailabilityDto addAvailability(AvailabilityDto availabilityDto) {
        Availability availability = availabilityMapper.mapDtoToEntity(availabilityDto);
        return availabilityMapper.mapEntityToDto(availability);
    }

    /**
     * Get all availability belongs to user
     *
     * @param userId add new availability to user
     */
    public List<AvailabilityDto> getAvailabilityForUser(Long userId) throws NoSuchUserException {
        List<Long> userAvailabilityID= userRepository.findById(userId).getUserAvailabilityHours();
        List<Availability> availabilityList = availabilityRepository.findAll().stream()
                .filter(availability -> userAvailabilityID.contains(availability.getId())) // contains
                .collect(Collectors.toList());
        return availabilityMapper.mapListToDto(availabilityList);
    }

    public AvailabilityDto getAvailabilityById(Long id) throws AvailabilityException {
        availabilityValidator.chechIfIdAvailabilityIsNull(id);
        return availabilityMapper.mapEntityToDto(availabilityRepository.findById(id));
    }

    /**
     * Add when user is available or not to system
     *
     * @param availabilityDto add new availability to user
     * @throws AvailabilityException- check if availability object is not null
     * @throws NoSuchUserException-   check if user Id is not null
     */
    public void addAvailabilityHours(AvailabilityDto availabilityDto, Long userId) throws NoSuchUserException, AvailabilityException {
        userValidator.checkIfUserIdIsNull(userId);
        availabilityValidator.checkIfAvailabilityIsNotNull(availabilityDto);

        Availability userAvailability = availabilityMapper.mapDtoToEntity(availabilityDto);
        Long newAvailability = availabilityRepository.add(userAvailability).getId();

        userRepository.findById(userId).getUserAvailabilityHours().add(newAvailability);
    }

    /**
     * Remove availability of user from system =
     *
     * @param availabilityId search for availability object in collection
     * @param userId         search by user Id
     * @throws AvailabilityException- check if availability object is not null
     * @throws NoSuchUserException-   check if user Id is not null
     */
    public void removeAvailabilityHours(Long userId, Long availabilityId) throws NoSuchUserException, AvailabilityException {
        userValidator.checkIfUserIdIsNull(userId);
        availabilityValidator.chechIfIdAvailabilityIsNull(availabilityId);

        List<Long> userAvailability = userRepository.findById(userId).getUserAvailabilityHours();
        userAvailability.remove(availabilityId);
    }

    /**
     * Edit and user can change his availability status
     *
     * @param availabilityDto add new availability to user
     * @throws AvailabilityException- check if new availability object is not null
     * @throws NoSuchUserException-   check if user Id is not null
     */
    public AvailabilityDto editAvailabilityHours(AvailabilityDto availabilityDto) throws NoSuchUserException, AvailabilityException { // edit with comments
        availabilityValidator.checkIfAvailabilityIsNotNull(availabilityDto);

        Availability availability = availabilityMapper.mapDtoToEntity(availabilityDto);
        Availability availability1=availabilityRepository.edit(availability);
        return availabilityMapper.mapEntityToDto(availability1);
    }

    /**
     * User can decline invitation by update his availability and write information
     * about decline
     * @param Id availability id
     * @param information decline information
     * @throws AvailabilityException- check if availability object is not null
     * @throws NoSuchUserException-   check if user Id is not null
     */
    public AvailabilityDto declineAvailability(Long Id, String information) throws AvailabilityException, NoSuchUserException {
        AvailabilityDto availabilityToUpdate = getAvailabilityById(Id);
        availabilityToUpdate.setDisponibility(Disponibility.BUSY);
        availabilityToUpdate.setInformation(information);
        Availability availability = availabilityRepository.edit(availabilityMapper.mapDtoToEntity(availabilityToUpdate));
        return availabilityMapper.mapEntityToDto(availability);
    }

}
