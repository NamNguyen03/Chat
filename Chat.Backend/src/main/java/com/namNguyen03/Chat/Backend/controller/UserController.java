/**
 * 
 */
package com.namNguyen03.Chat.Backend.controller;

import com.namNguyen03.Chat.Backend.service.user.UserRequestModels;
import com.namNguyen03.Chat.Backend.service.user.UserResponseModes;
import com.namNguyen03.Chat.Backend.service.user.UserService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nam
 *
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserResponseModes.RegisterResponseModel register(@Valid @RequestBody UserRequestModels.RegisterRequestModel user){
        return userService.register(user);
    }
}
