package io.gps.com.service;

import io.gps.com.config.exception.BusinessException;
import io.gps.com.dto.LocationDTO;
import io.gps.com.entity.Location;

import java.time.LocalDateTime;
import java.util.List;

public interface LocationService {

    Location createLocation(LocationDTO locationDTO) throws BusinessException;

    Location getLocationById(final int id) throws BusinessException;

    Location deleteLocationById(final int id) throws BusinessException;

    List<Location> findLocationsBetweenGivenDates(final int id, LocalDateTime startDate, LocalDateTime endDate) throws BusinessException;
}
