package com.capgemini.gamecapmates.controller;

import com.capgemini.gamecapmates.dto.AvailabilityDto;
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

    /**
     *          Method which handles HTTP GET request. It is returning JSON file with wanted
     *          {@link com.capgemini.gamecapmates.dto.AvailabilityDto} object found by its id
     * @param id
     *          Long object with id of wanted {@link com.capgemini.gamecapmates.dto.AvailabilityDto}
     * @return
     *          JSON file with wanted {@link com.capgemini.gamecapmates.dto.AvailabilityDto} object
     * @throws Exception
     *          when {@link com.capgemini.gamecapmates.domain.Availability} with given id is null
     */
    @GetMapping("/availability/{id}")
    public ResponseEntity<List<AvailabilityDto>> getAvailabilityForUser(@PathVariable("id") Long id) throws Exception {
        if (id < 0) {
            return ResponseEntity.badRequest().body(null);
        }
        List<AvailabilityDto> availabilityDtos = availabilityService.getAvailabilityForUser(id);
        return ResponseEntity.ok().body(availabilityDtos);
    }

    /**
     *          Method which handles HTTP POST request. It is returning JSON file with wanted
     *          {@link com.capgemini.gamecapmates.dto.AvailabilityDto} object found by its id
     * @param availabilityDto
     *          object {@link com.capgemini.gamecapmates.dto.AvailabilityDto}
     * @return
     *          JSON file with wanted {@link com.capgemini.gamecapmates.dto.AvailabilityDto} object
     * @throws Exception
     *          when {@link com.capgemini.gamecapmates.domain.Availability} with given object is null
     */
    @PostMapping(value= "/addAvailability", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AvailabilityDto> addAvailability(@RequestBody AvailabilityDto availabilityDto) throws Exception {
        AvailabilityDto availability = availabilityService.addAvailability(availabilityDto);
        return ResponseEntity.ok().body(availability);
    }

}
