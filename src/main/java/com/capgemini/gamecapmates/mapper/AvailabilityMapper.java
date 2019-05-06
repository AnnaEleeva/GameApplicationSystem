package com.capgemini.gamecapmates.mapper;

import com.capgemini.gamecapmates.domain.Availability;
import com.capgemini.gamecapmates.dto.AvailabilityDto;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityMapper {


    public AvailabilityDto mapEntityToDto(Availability availability){
        return AvailabilityDto.builder()
                .id(availability.getId())
                .dateFrom(availability.getDateFrom())
                .dateTo(availability.getDateTo())
                .disponibility(availability.getDisponibility())
                .information(availability.getInformation())
                .build();
    }


    public Availability mapDtoToEntity(AvailabilityDto availabilityDto){
        return Availability.builder()
                .id(availabilityDto.getId())
                .dateFrom(availabilityDto.getDateFrom())
                .dateTo(availabilityDto.getDateTo())
                .disponibility(availabilityDto.getDisponibility())
                .information(availabilityDto.getInformation())
                .build();
    }

}
