package io.gps.com.service;


import io.gps.com.config.exception.BusinessException;
import io.gps.com.dto.LocationDTO;
import io.gps.com.entity.Location;
import io.gps.com.entity.User;
import io.gps.com.repo.LocationRepo;
import io.gps.com.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private LocationRepo locationRepo;

    public Location createLocation(LocationDTO locationDTO) throws BusinessException {

        User user = userRepo.findById(locationDTO.getUserId());

        if(user == null){
            throw new BusinessException(404,"User is not existent!");
        }

        Location location = new Location(locationDTO.getLatitude(),
                locationDTO.getLongitude(),
                locationDTO.getDate(),
                user);
        return locationRepo.save(location);
    }

    public Location getLocationById(int id) throws BusinessException {
        Location location = locationRepo.getById(id);

        if(location == null){
            throw new BusinessException(404, "Location not found!");
        }

        return location;
    }

    public Location deleteLocationById(int id) throws BusinessException{
        return locationRepo.deleteById(id);
    }

    public List<Location> findLocationsBetweenGivenDates(final int userId, LocalDateTime startDate, LocalDateTime endDate) throws BusinessException{
        if(startDate.compareTo(endDate)>0){
            throw new BusinessException(403,"start date can't be greater than end date");
        }
        return locationRepo.customQuery(userId,startDate,endDate);
    }
}
