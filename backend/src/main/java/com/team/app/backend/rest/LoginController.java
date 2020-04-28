package com.team.app.backend.rest;

import com.team.app.backend.dto.UserLoginDto;
import com.team.app.backend.persistance.model.User;
import com.team.app.backend.security.jwt.JwtTokenProvider;
import com.team.app.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(user);

            Map<Object, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("firstName", user.getFirstName());
            response.put("lastName", user.getLastName());
            response.put("email", user.getEmail());
            response.put("password", user.getPassword());
            response.put("image", user.getImage());

            response.put("status", user.getStatus());
            response.put("role", user.getRole());

            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok().body(response);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new BadCredentialsException("Username or password are not valid");
        }
    }

    //testing jwt
//    @GetMapping("/get-user/{id}")
//    public ResponseEntity<String> getUser(@PathVariable Long id) {
//        return new ResponseEntity<>(
//                userService.getUserById(id).toString(),
//                HttpStatus.OK);
//    }

    //TO DO
    @PostMapping("/logout")
    public Map<String, Object> logout() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("content", "Hello World");
        return model;

    }

}
