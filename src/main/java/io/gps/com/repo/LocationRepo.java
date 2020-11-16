package io.gps.com.repo;

import io.gps.com.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface LocationRepo extends JpaRepository<Location, Integer> {
    Location save(Location location);

    Location getById(final int id);

    Location deleteById(final int id);

    @Query(value="SELECT * FROM location WHERE user_id=?1 AND date>=?2 AND date<=?3",nativeQuery = true)
    List<Location> customQuery(final int userId, final LocalDateTime startDate, final LocalDateTime endDate);
}
