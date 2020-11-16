package io.gps.com.service;

import io.gps.com.config.exception.BusinessException;
import io.gps.com.dto.UserLoginDTO;
import io.gps.com.dto.UserRegisterDTO;
import io.gps.com.entity.Role;
import io.gps.com.entity.User;
import io.gps.com.repo.RoleRepo;
import io.gps.com.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public Integer save(UserRegisterDTO registerDTO) throws BusinessException {
        User user = userRepo.findByEmail(registerDTO.getEmail());
        if (user != null) {
            throw new BusinessException(403, "User already exists");
        } else {
            user = new User(registerDTO.getFirstName(),
                    registerDTO.getEmail(),
                    passwordEncoder.encode(registerDTO.getPassword()),
                    registerDTO.getLastName(),
                    Collections.singletonList(new Role("BASIC_USER")));

            int id =userRepo.save(user).getId();
            return id;
        }
    }

    public boolean deleteById(int id){
        return userRepo.deleteById(id);
    }

    public User getUserById(final int id) throws BusinessException{
        User user = userRepo.findById(id);
        if(user==null){
            throw new BusinessException(404, "User not found!");
        }
        return user;
    }

    public User login(UserLoginDTO userLoginDTO) throws BusinessException {

        if (Objects.isNull(userLoginDTO)) {
            throw new BusinessException(401, "Body null !");
        }

        if (Objects.isNull(userLoginDTO.getEmail())) {
            throw new BusinessException(400, "Email cannot be null ! ");
        }

        if (Objects.isNull(userLoginDTO.getPassword())) {
            throw new BusinessException(400, "Password cannot be null !");
        }

        final User user = userRepo.findByEmail(userLoginDTO.getEmail());

        if (Objects.isNull(user)) {
            throw new BusinessException(401, "Bad credentials !");
        }

        if (!passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new BusinessException(401, "Bad credentials !");
        }

        return user;
    }

    public List<User> getUsers(){
        return (List<User>) userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password!");
        }
        return new org.springframework.security.core.userdetails.User(user.getFirstName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(String.format("ROLE_%s", role.getName())))
                .collect(Collectors.toList());
    }


}
