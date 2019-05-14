package com.capgemini.gamecapmates.controller;

import com.capgemini.gamecapmates.dto.AvailabilityDto;
import com.capgemini.gamecapmates.dto.UserDto;
import com.capgemini.gamecapmates.restService.AvailabilityWebService;
import com.capgemini.gamecapmates.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availabilities")
public class AvailabilityRestController {

    private AvailabilityService availabilityService;

    @Autowired
    public AvailabilityRestController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @GetMapping("/availability/{id}")
    public ResponseEntity<List<AvailabilityDto>> getAvailabilityForUser(@PathVariable("id") Long id) throws Exception {
        if (id < 0) {
            return ResponseEntity.badRequest().body(null);
        }
        List<AvailabilityDto> availabilityDtos = availabilityService.getAvailabilityForUser(id);
        return ResponseEntity.ok().body(availabilityDtos);
    }

    @PostMapping(value= "/addAvailability", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AvailabilityDto> addAvailability(@RequestBody AvailabilityDto availabilityDto) throws Exception {
        AvailabilityDto availability = availabilityService.addAvailability(availabilityDto);
        return ResponseEntity.ok().body(availability);
    }

}
