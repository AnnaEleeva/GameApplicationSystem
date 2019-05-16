package com.gamecapmates.validation;

import com.gamecapmates.Exceptions.AvailabilityException;
import com.gamecapmates.dto.AvailabilityDto;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityValidator {

    // check if availability is in repo

    public void checkIfAvailabilityIsNotNull(AvailabilityDto availabilityDto) throws AvailabilityException {
        if (availabilityDto == null) {
            throw new AvailabilityException();
        }
    }

    public void chechIfIdAvailabilityIsNull(Long availabilityId) throws AvailabilityException {
        if (availabilityId == null) {
            throw new AvailabilityException();
        }
    }

    public void checkIfComponentAvailabilityIsNull(AvailabilityDto availabilityDto) throws AvailabilityException{
        if(availabilityDto.getDateFrom()== null || availabilityDto.getDateTo() == null
        || availabilityDto.getDisponibility()== null || availabilityDto.getInformation()== null){
            throw new AvailabilityException();
        }
    }

    public void checkIfUserUpdateBirthDateIsSmaller(AvailabilityDto availabilityDto) {
        if(availabilityDto.getDateFrom().compareTo(availabilityDto.getDateTo())> 0){
            throw new IllegalArgumentException();
        }
    }

}
