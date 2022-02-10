/**
 * 
 */
package com.namNguyen03.Chat.Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.namNguyen03.Chat.Backend.service.user.UserService;

/**
 * @author nam
 *
 */
@RestController
@RequestMapping("/api/token")
public class TokenController {
	@Autowired
	private UserService userSerivce;

	@GetMapping()
	public String test() {
		return "ok -_- ";
	}
}
