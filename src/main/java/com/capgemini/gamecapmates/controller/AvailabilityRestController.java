package com.capgemini.gamecapmates.controller;

import com.capgemini.gamecapmates.dto.AvailabilityDto;
import com.capgemini.gamecapmates.dto.UserDto;
import com.capgemini.gamecapmates.restService.AvailabilityWebService;
import com.capgemini.gamecapmates.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityRestController {

    private AvailabilityService availabilityService;
    private AvailabilityWebService availabilityWebService;

    @Autowired
    public AvailabilityRestController(AvailabilityService availabilityService, AvailabilityWebService availabilityWebService) {
        this.availabilityService = availabilityService;
        this.availabilityWebService= availabilityWebService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<AvailabilityDto>> findAllAvailability(@PathVariable("id") Long id) throws Exception {
        if (id < 0) {
            return ResponseEntity.badRequest().body(null);
        }
        List<AvailabilityDto> allBooks = availabilityService.getAvailabilityForUser(id);
        return ResponseEntity.ok().body(allBooks);
    }

    @PostMapping("/edit")
    public ResponseEntity<AvailabilityDto> editAvailabilityHours(@RequestBody AvailabilityDto availabilityDto) throws Exception {
        AvailabilityDto availabilityDto1=availabilityService.editAvailabilityHours(availabilityDto);
        return ResponseEntity.ok().body(availabilityDto1);
    }

    @GetMapping("/user/{id}")
   public ResponseEntity<List<AvailabilityDto>> getAvailabilityForUser(@PathVariable ("id") Long id) throws Exception{
        if (id < 0) {
            return ResponseEntity.badRequest().body(null);
        }
        List<AvailabilityDto> availabilityDtos=availabilityService.getAvailabilityForUser(id);
        return ResponseEntity.ok().body(availabilityDtos);
    }

    @PostMapping(value = "/search")
    public ResponseEntity<List<AvailabilityDto>> searchForUser(@RequestBody AvailabilityDto availabilityDto) throws Exception{
        List<AvailabilityDto> availabilityDtos= availabilityWebService.findAvailabilityAfterParam(availabilityDto);
        return ResponseEntity.ok().body(availabilityDtos);
    }

    //400, 404 - response entity, odpowiedni status dodac ,sensowne wyjasnienie

}
