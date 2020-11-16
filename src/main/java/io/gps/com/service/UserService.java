package io.gps.com.service;

import io.gps.com.config.exception.BusinessException;
import io.gps.com.dto.UserLoginDTO;
import io.gps.com.dto.UserRegisterDTO;
import io.gps.com.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User login(final UserLoginDTO userLoginDTO) throws BusinessException;

    Integer save(UserRegisterDTO userRegisterDTO) throws BusinessException;

    List<User> getUsers();

    boolean deleteById(final int id);

    User getUserById(final int id) throws BusinessException;
}
