/**
 * 
 */
package com.namNguyen03.Chat.Backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nam
 *
 */
@RestController
@RequestMapping("/api/token")
public class TokenController {
	@GetMapping()
	public String test() {
		return "ok -_- ";
	}
}
