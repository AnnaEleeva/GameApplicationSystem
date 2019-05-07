package com.capgemini.gamecapmates.repository;

import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.dao.Dao;
import com.capgemini.gamecapmates.domain.Availability;
import com.capgemini.gamecapmates.enums.Disponibility;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.Math.toIntExact;

@Repository
public class AvailabilityRepository implements Dao<Availability> {

    private List<Availability> availabilityList;

    public AvailabilityRepository(){
        availabilityList= new ArrayList<>();

        availabilityList.add(Availability.builder()
                .id(1L)
                .dateFrom(LocalDateTime.of(2019, 4, 29, 12, 0))
                .dateTo(LocalDateTime.of(2019, 4, 29, 15, 0))
                .disponibility(Disponibility.AVAILABLE)
                .information("Battleground?")
                .build());
        availabilityList.add(Availability.builder()
                .id(2L)
                .dateFrom(LocalDateTime.of(2019, 2, 1, 12, 0))
                .dateTo(LocalDateTime.of(2019, 2, 1, 14, 0))
                .disponibility(Disponibility.BUSY)
                .information("Playing new Game on steam")
                .build());
        availabilityList.add(Availability.builder()
                .id(3L)
                .dateFrom(LocalDateTime.of(2019, 6, 20, 12, 0))
                .dateTo(LocalDateTime.of(2019, 6, 20, 13, 0))
                .disponibility(Disponibility.AVAILABLE)
                .information("PVP..")
                .build());
    }

    @Override
    public List<Availability> findAll() {
        return availabilityList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public Availability add(Availability availability) {
        if (availability != null) {
            availabilityList.add(availability);
            return availability;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Availability findById(Long id) {
        if (id != null) {
            return availabilityList.stream()
                    .filter(availability -> id.equals(availability.getId()))
                    .findAny().orElseThrow(()->new IllegalArgumentException());
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void remove(Availability availability) {
        availabilityList.remove(availability);
    }

    @Override
    public Availability edit(Availability availability) throws NoSuchUserException {
        if (availability.getId() <= availabilityList.size()) {
            int index = toIntExact(getIndex(availability.getId()));
            availabilityList.set(index, availability);
            return availability;
        }
        throw new NoSuchUserException();
    }


    private Long getIndex(Long id) {
        Availability availability = availabilityList.stream()
                .filter(av -> av.getId().equals(id))
                .findAny().orElse(null);

        return (long)availabilityList.indexOf(availability);
    }


    @Override
    public void clear() {
        availabilityList.clear();
    }
}
