/**
 * 
 */
package com.namNguyen03.Chat.Backend.service.user;

import java.util.UUID;

import lombok.*;

/**
 * @author nam
 *
 */
public class UserResponseModes {

    private UserResponseModes(){
        
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class RegisterResponseModel{
    	private UUID uuid;
        private String username;
        private String password;
        private String fullName;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class LoginResponseModel{
        private String username;
        private String fullName;
        private String jwt;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class ProfileResponseModel{
        private UUID uuid;
        private String username;
        private String fullName;
    }
}
