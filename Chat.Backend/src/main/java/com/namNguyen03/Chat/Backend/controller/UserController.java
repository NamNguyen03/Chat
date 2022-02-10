/**
 * 
 */
package com.namNguyen03.Chat.Backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nam
 *
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

	@GetMapping
	public String test() {
		return "security fails";
	}
}
