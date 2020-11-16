package io.gps.com.repo;

import io.gps.com.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    User findByEmail(final String email);

    User findById(final int id);

    User save(User user);

    boolean deleteById(int id);
}
