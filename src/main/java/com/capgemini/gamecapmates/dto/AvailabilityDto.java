package com.capgemini.gamecapmates.dto;

import com.capgemini.gamecapmates.enums.Disponibility;
import lombok.*;

import java.time.LocalDateTime;

//@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class AvailabilityDto {

    private Long id;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private Disponibility disponibility;
    private String information;

    public AvailabilityDto (){}

   public AvailabilityDto(Long id,LocalDateTime dateFrom, LocalDateTime dateTo, Disponibility disponibility, String information){
        this.id=id;
        this.dateFrom=dateFrom;
        this.dateTo= dateTo;
        this.disponibility=disponibility;
        this.information=information;
    }
}
