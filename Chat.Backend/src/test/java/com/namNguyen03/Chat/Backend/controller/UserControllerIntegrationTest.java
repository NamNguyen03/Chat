/**
 * 
 */
package com.namNguyen03.Chat.Backend.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.namNguyen03.Chat.Backend.service.user.UserRequestModels.RegisterRequestModel;
import com.namNguyen03.Chat.Backend.service.user.UserResponseModes.RegisterResponseModel;
import com.namNguyen03.Chat.Backend.service.user.UserService;

/**
 * @author nam
 *
 */


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserService userService;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void givenUser_whenValidUserIsUsedToRegister_thenReturnJsonObject() throws Exception {
		RegisterRequestModel rq = new RegisterRequestModel("nam@gmail.com", "123123123", "Nam Nguyen");
		RegisterResponseModel rp = new RegisterResponseModel(UUID.randomUUID(),"nam@gmail.com", "123123123", "Nam Nguyen");
		Mockito.when(userService.register(rq)).thenReturn(rp);

		MvcResult mvcResult = mvc.perform(post("/api/users/register").content(asJsonString(rq))
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(rp, objectMapper.readValue(content, RegisterResponseModel.class));
	}

	@Test
	public void givenUser_whenInvalidUserIsUsedToRegister_thenReturnBadRequest() throws Exception {
		RegisterRequestModel rq = new RegisterRequestModel("nam.com", "123123123", "Nam Nguyen");

		MvcResult mvcResult = mvc.perform(post("/api/users/register").content(asJsonString(rq))
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals("email should be validation",400, status);
		
		rq = new RegisterRequestModel("nam@gmail.com", "12", "Nam Nguyen");

		mvcResult = mvc.perform(post("/api/users/register").content(asJsonString(rq))
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		status = mvcResult.getResponse().getStatus();
		assertEquals("password should be validation",400, status);

		rq = new RegisterRequestModel("nam@gmail.com", "1212123", null);

		mvcResult = mvc.perform(post("/api/users/register").content(asJsonString(rq))
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		status = mvcResult.getResponse().getStatus();
		assertEquals("fullname should be validation",400, status);
	}


	private String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
