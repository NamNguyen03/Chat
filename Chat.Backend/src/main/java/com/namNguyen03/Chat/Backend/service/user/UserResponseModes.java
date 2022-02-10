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
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class RegisterResponseModel{
    	private UUID uuid;
        private String username;
        private String password;
        private String fullName;
    }
}
