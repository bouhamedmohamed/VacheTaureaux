package com.mohamed.rest.vt.springboot2restservicevachetaureaux.controller;

import com.mohamed.rest.vt.springboot2restservicevachetaureaux.model.User;
import com.mohamed.rest.vt.springboot2restservicevachetaureaux.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class RestApiController {

    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    UserService userService; //Service which will do all data retrieval/manipulation work

    // -------------------Retrieve All Users---------------------------------------------
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // -------------------Retrieve Single User------------------------------------------
    @RequestMapping(value = "/user/{userlogin}", method = RequestMethod.GET )
    public ResponseEntity<?> getUser(@PathVariable("userlogin") String userLogin) {
        logger.info("Fetching User with login {}", userLogin);

        Optional<User> user = userService.findByLoginUser(userLogin);
        if (!user.isPresent()) {
            logger.error("User with login  {} not found.", userLogin);
            return new ResponseEntity(new CustomErrorType("User with login " + userLogin
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user.get(), HttpStatus.OK);
    }

    // -------------------Create a User-------------------------------------------

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        logger.info("Creating User : {}", user);
        Optional<User> userFromDB = userService.findByLoginUser(user.getLoginUser());
        if (userFromDB.isPresent()) {
            logger.error("User with login  {} exists.", user.getLoginUser());
            return new ResponseEntity(new CustomErrorType("User with login " + user.getLoginUser()
                    + " already exist"), HttpStatus.CONFLICT);
        }
        userService.saveUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{userlogin}").buildAndExpand(
                user.getLoginUser()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a User ------------------------------------------------

    @RequestMapping(value = "/user/{userlogin}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("userlogin") String userlogin, @RequestBody User user) {
        logger.info("Updating User with login {}", userlogin);

        Optional<User> userFromDB = userService.findByLoginUser(user.getLoginUser());

        if (!userFromDB.isPresent()) {
            logger.error("Unable to update. User with login {} not found.", userlogin);
            return new ResponseEntity(new CustomErrorType("Unable to upate. User with login " + userlogin + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        userFromDB.get().setNameUser(user.getNameUser());
        userFromDB.get().setPwdUser(user.getPwdUser());
        userService.updateUser(userFromDB.get());
        return new ResponseEntity<User>(userFromDB.get(), HttpStatus.OK);
    }

    // ------------------- Delete a User-----------------------------------------

    @RequestMapping(value = "/user/{userlogin}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("userlogin") String userlogin) {
        logger.info("Fetching & Deleting User with login {}", userlogin);

        Optional<User> userFromDB = userService.findByLoginUser(userlogin);

        if (!userFromDB.isPresent()) {
            logger.error("Unable to update. User with login {} not found.", userlogin);
            return new ResponseEntity(new CustomErrorType("Unable to upate. User with login " + userlogin + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(userFromDB.get().getIdUser());
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}