package com.capgemini.gamecapmates.restService;

import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.domain.Availability;
import com.capgemini.gamecapmates.dto.AvailabilityDto;
import com.capgemini.gamecapmates.mapper.AvailabilityMapper;
import com.capgemini.gamecapmates.repository.AvailabilityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvailabilityWebService {

    private AvailabilityRepository availabilityRepository;
    private AvailabilityMapper availabilityMapper;

    public AvailabilityWebService(AvailabilityMapper availabilityMapper, AvailabilityRepository availabilityRepository){
        this.availabilityRepository= availabilityRepository;
        this.availabilityMapper= availabilityMapper;
    }

    public List<AvailabilityDto> findAvailabilityAfterParam(AvailabilityDto availabilityDto) throws NoSuchUserException {

        List<Availability> availabilityList = availabilityRepository.findAll().stream()
                .filter(availabilityDto1 -> availabilityDto1.getInformation().equalsIgnoreCase((availabilityDto.getInformation()))
                        || availabilityDto.getInformation() == null)
                .filter(availabilityDto1 -> availabilityDto.getDateTo().equals(availabilityDto1.getDateTo()) || availabilityDto.getDateTo()==null)
                .filter(availability -> availabilityDto.getDateFrom().equals(availability.getDateFrom()) || availabilityDto.getDateFrom() == null)
                .collect(Collectors.toList());

        return availabilityMapper.mapListToDto(availabilityList);
    }

}
