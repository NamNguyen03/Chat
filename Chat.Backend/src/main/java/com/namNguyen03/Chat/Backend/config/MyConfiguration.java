/**
 * 
 */
package com.namNguyen03.Chat.Backend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author nam
 *
 */
@Configuration
public class MyConfiguration {
	
	@Bean
	public ModelMapper initModelMapper() {
		return new ModelMapper();
	}
}
