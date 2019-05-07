package com.capgemini.gamecapmates.mapper;

import com.capgemini.gamecapmates.Exceptions.AvailabilityException;
import com.capgemini.gamecapmates.domain.Availability;
import com.capgemini.gamecapmates.dto.AvailabilityDto;
import com.capgemini.gamecapmates.validation.AvailabilityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AvailabilityMapper {

    @Autowired
    AvailabilityValidator availabilityValidator;

    public Availability mapDtoToEntity(AvailabilityDto availabilityDto) throws AvailabilityException {
        availabilityValidator.checkIfAvailabilityIsNotNull(availabilityDto);
        availabilityValidator.checkIfComponentAvailabilityIsNull(availabilityDto);
        availabilityValidator.checkIfUserUpdateBirthDateIsSmaller(availabilityDto);

        return Availability.builder()
                .id(availabilityDto.getId())
                .dateFrom(availabilityDto.getDateFrom())
                .dateTo(availabilityDto.getDateTo())
                .disponibility(availabilityDto.getDisponibility())
                .information(availabilityDto.getInformation())
                .build();
    }

    public AvailabilityDto mapEntityToDto (Availability availability) {

        return AvailabilityDto.builder()
                .id(availability.getId())
                .dateFrom(availability.getDateFrom())
                .dateTo(availability.getDateTo())
                .disponibility(availability.getDisponibility())
                .information(availability.getInformation())
                .build();
    }


    public List<AvailabilityDto> mapListToDto(List<Availability> availabilities) {
        return availabilities.stream()
                .map(availability -> new AvailabilityDto(
                        availability.getId(),
                        availability.getDateFrom(),
                        availability.getDateTo(),
                        availability.getDisponibility(),
                        availability.getInformation()
                )).collect(Collectors.toList());


    }

}
