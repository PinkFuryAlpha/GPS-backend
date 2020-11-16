package io.gps.com.controllers;

import io.gps.com.config.exception.BusinessException;
import io.gps.com.dto.LocationDTO;
import io.gps.com.entity.Location;
import io.gps.com.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/locations")
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody final LocationDTO locationDTO) throws BusinessException {
        return ResponseEntity.ok(locationService.createLocation(locationDTO));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable final int id) throws BusinessException {
        return ResponseEntity.ok(locationService.getLocationById(id));
    }

    @GetMapping()
    public ResponseEntity<List<Location>> getLocationsByDates(@PathParam("userId") final int userId,
                                                              @RequestParam("startDate")
                                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                              final LocalDateTime startDate,
                                                              @RequestParam("endDate")
                                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                              final LocalDateTime endDate) throws BusinessException{
        return ResponseEntity.ok(locationService.findLocationsBetweenGivenDates(userId,startDate,endDate));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Location> deleteLocationById(@PathVariable final int id) throws BusinessException {
        return ResponseEntity.ok(locationService.deleteLocationById(id));
    }
}
