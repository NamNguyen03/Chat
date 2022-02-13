package com.namNguyen03.Chat.Backend;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestBase {

    protected final ObjectMapper objectMapper = new ObjectMapper();

    protected String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
