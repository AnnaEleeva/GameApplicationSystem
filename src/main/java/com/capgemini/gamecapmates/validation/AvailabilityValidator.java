package com.capgemini.gamecapmates.validation;

import com.capgemini.gamecapmates.Exceptions.AvailabilityException;
import com.capgemini.gamecapmates.dto.AvailabilityDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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
