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

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class RegisterRequestModel{
        @NotBlank
        @Email
        private String username;
        @NotBlank
        @Size(min = 6)
        private String password;
        @NotBlank
        private String fullName;
    }
}
