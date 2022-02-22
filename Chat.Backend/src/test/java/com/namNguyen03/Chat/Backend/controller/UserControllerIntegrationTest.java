/**
 * 
 */
package com.namNguyen03.Chat.Backend.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.namNguyen03.Chat.Backend.service.user.UserRequestModels.RegisterRequestModel;
import com.namNguyen03.Chat.Backend.service.user.UserResponseModes.ProfileResponseModel;
import com.namNguyen03.Chat.Backend.service.user.UserResponseModes.RegisterResponseModel;
import com.namNguyen03.Chat.Backend.TestBase;
import com.namNguyen03.Chat.Backend.exception.BusinessException;
import com.namNguyen03.Chat.Backend.exception.ExceptionHelper;
import com.namNguyen03.Chat.Backend.service.user.UserService;

/**
 * @author nam
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest extends TestBase {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserService userService;

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

	@Test
	public void givenUser_whenUsernameExistsIsUsedToRegister_thenReturnBadRequest() throws Exception {
		RegisterRequestModel rq = new RegisterRequestModel("nam@gmail.com", "123123123", "Nam Nguyen");
		Mockito.when(userService.register(rq)).thenThrow(new BusinessException("username exists"));
		
		MvcResult mvcResult = mvc.perform(post("/api/users/register").content(asJsonString(rq))
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();	
		assertEquals(400, status);
		assertEquals("username exists",objectMapper.readValue(content, ExceptionHelper.ExceptionResponse.class).getMessage());
	}
	
	@Test
	@WithUserDetails("nam@gmail.com")
	public void givenUser_whenUsernameExistsIsUsedToGetProfile_theReturnJsonObject() throws Exception{
		ProfileResponseModel rp = new ProfileResponseModel();
		rp.setUsername("nam@gmail.com");
		rp.setFullName("Nguyen Duc Named");
		rp.setUuid(UUID.randomUUID());
		Mockito.when(userService.getProfile()).thenReturn(rp);

		MvcResult mvcResult = mvc.perform(get("/api/users/me")
			.contentType(MediaType.APPLICATION_JSON)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(rp, objectMapper.readValue(content, ProfileResponseModel.class));
	}

	@Test
	public void givenUser_whenNotLoginIsUsedToGetProfile_theReturnUnauthorizedError() throws Exception{
		ProfileResponseModel rp = new ProfileResponseModel();
		rp.setUsername("nam@gmail.com");
		rp.setFullName("Nguyen Duc Named");
		rp.setUuid(UUID.randomUUID());
		Mockito.when(userService.getProfile()).thenReturn(rp);

		MvcResult mvcResult = mvc.perform(get("/api/users/me")
			.contentType(MediaType.APPLICATION_JSON)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(401, status);
	}

	@Test
	@WithUserDetails("nam@gmail.com")
	public void givenUser_whenUsernameNotExistsIsUsedToGetProfile_theReturnJsonObject() throws Exception{
		Mockito.when(userService.getProfile()).thenThrow(new BusinessException("user not exists"));

		MvcResult mvcResult = mvc.perform(get("/api/users/me")
			.contentType(MediaType.APPLICATION_JSON)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals("user not exists",objectMapper.readValue(content, ExceptionHelper.ExceptionResponse.class).getMessage());
	}
}
