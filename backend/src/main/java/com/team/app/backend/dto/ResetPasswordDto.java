package com.team.app.backend.dto;

import lombok.Data;

@Data
public class ResetPasswordDto {

    private String username;
    private String oldPassword;
    private String newPassword;

}
