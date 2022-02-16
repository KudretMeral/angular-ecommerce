package com.meral.ecommerce.controller;

import com.meral.ecommerce.constant.SecurityConstant;
import com.meral.ecommerce.exception.model.*;
import com.meral.ecommerce.model.User;
import com.meral.ecommerce.model.UserPrincipal;
import com.meral.ecommerce.service.UserService;
import com.meral.ecommerce.util.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = {"/", "/user"})
public class UserController extends ExceptionHandling {

    @Autowired
    private UserService userService;

    private AuthenticationManager authenticationManager;

    private JWTTokenProvider jwtTokenProvider;


    public UserController(AuthenticationManager authenticationManager, JWTTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        authenticate(user.getUsername(), user.getPassword());
        User loginUser = userService.findUserByUsername(user.getUsername());
        UserPrincipal userPrincipal = new UserPrincipal(loginUser);
        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);

        return new ResponseEntity<>(loginUser, jwtHeader, HttpStatus.OK);
    }

    private HttpHeaders getJwtHeader(UserPrincipal userPrincipal) {


        HttpHeaders headers= new HttpHeaders();
        headers.add(SecurityConstant.JWT_TOKEN_HEADER,jwtTokenProvider.generateJwtToken(userPrincipal));

        return headers;
    }

    private void authenticate(String username, String password) {
authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws UserNotFoundException, UsernameExistException, EmailExistException {


        User newUser = userService.register(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail());

        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

}
