/**
 * 
 */
package com.namNguyen03.Chat.Backend.controller;
import javax.validation.Valid;

import com.namNguyen03.Chat.Backend.service.user.UserRequestModels;
import com.namNguyen03.Chat.Backend.service.user.UserResponseModes;
import com.namNguyen03.Chat.Backend.service.user.UserService;

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
@RequestMapping("/api/token")
@CrossOrigin(origins = "*")
public class TokenController {
	
	@Autowired
	private UserService userService;

	@PostMapping
	public UserResponseModes.LoginResponseModel login(@Valid @RequestBody UserRequestModels.LoginRequestModel loginModel){
		return userService.login(loginModel);
	}

}
