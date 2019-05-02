package com.capgemini.gamecapmates.mapper;

import com.capgemini.gamecapmates.domain.History;
import com.capgemini.gamecapmates.dto.HistoryDto;

public class HistoryMapper {

    public HistoryDto mapToHistoryDto(History history){
        return HistoryDto.builder()
                .date(history.getDate())
                .action(history.getAction())
                .details(history.getDetails())
                .rating(history.getRating())
                .build();
    }
}
