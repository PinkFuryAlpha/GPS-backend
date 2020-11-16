package io.gps.com.controllers;

import io.gps.com.config.exception.BusinessException;
import io.gps.com.dto.UserLoginDTO;
import io.gps.com.dto.UserRegisterDTO;
import io.gps.com.entity.User;
import io.gps.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register", consumes = "application/json")
    public ResponseEntity<User> register(@Valid @RequestBody final UserRegisterDTO userRegisterDTO) throws BusinessException {
       // return ResponseEntity.ok(userService.save(userRegisterDTO));
       int id= userService.save(userRegisterDTO);

        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping(path = "/login")
    public ResponseEntity<User> loginUser(@RequestBody final UserLoginDTO userLoginDTO) throws BusinessException {
        return ResponseEntity.ok(userService.login(userLoginDTO));
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") final int id) throws BusinessException{
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Integer> deleteUser(@PathVariable final int id){

       boolean isRemoved = userService.deleteById(id);

        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping(path = "/me", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Boolean> currentUserName(Authentication authentication){
        return ResponseEntity.ok(authentication.isAuthenticated());
    }

}
