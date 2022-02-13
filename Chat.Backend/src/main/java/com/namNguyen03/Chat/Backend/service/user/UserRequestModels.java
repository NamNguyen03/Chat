package com.namNguyen03.Chat.Backend.service.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.*;

/**
 * @author nam
 *
 */

public class UserRequestModels {

    private UserRequestModels(){}

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class RegisterRequestModel{
        @NotBlank
        @Email
        @Size(max = 64)
        private String username;
        @NotBlank
        @Size(min = 6, max = 64)
        private String password;
        @NotBlank
        @Size(max = 64)
        private String fullName;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class LoginRequestModel{
        @NotBlank
        @Email
        @Size(max = 64)
        private String username;
        @NotBlank
        @Size(min = 6, max = 64)
        private String password;
    }

    
}
