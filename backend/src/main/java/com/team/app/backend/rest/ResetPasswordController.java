package com.team.app.backend.rest;

import com.team.app.backend.dto.ResetPasswordDto;
import com.team.app.backend.dto.UserUpdateDto;
import com.team.app.backend.exception.NotMatchingPasswordsException;
import com.team.app.backend.exception.UserNotFoundException;
import com.team.app.backend.persistance.model.User;
import com.team.app.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResetPasswordController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/reset-password")
    public ResponseEntity resetPassword(@RequestBody ResetPasswordDto resetPasswordDto)
            throws NotMatchingPasswordsException, UserNotFoundException {

        User user = userService.findByUsername(resetPasswordDto.getUsername());

        if (user == null) {
            throw new UserNotFoundException(
                    "No user with username: " + resetPasswordDto.getUsername() + " found in the database"
            );
        }

        if (!passwordEncoder.matches(resetPasswordDto.getOldPassword(), user.getPassword())) {
            throw new NotMatchingPasswordsException(
                    "Old password from the database and old password obtained from the request don't match"
            );
        }

        user.setPassword(passwordEncoder.encode(resetPasswordDto.getNewPassword()));

        UserUpdateDto userUpdateDto = new UserUpdateDto(user);

        userService.updateUser(userUpdateDto);

        return ResponseEntity.ok("User password successfully reset");

    }

}
