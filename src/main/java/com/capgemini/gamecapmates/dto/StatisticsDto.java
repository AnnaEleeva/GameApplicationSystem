package com.capgemini.gamecapmates.dto;

import com.capgemini.gamecapmates.domain.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class StatisticsDto {
    private Long rankingPosition;
    private Level level;
}
