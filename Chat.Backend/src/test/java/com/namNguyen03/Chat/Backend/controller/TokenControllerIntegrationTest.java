package com.namNguyen03.Chat.Backend.controller;

import com.namNguyen03.Chat.Backend.TestBase;
import com.namNguyen03.Chat.Backend.exception.BusinessException;
import com.namNguyen03.Chat.Backend.exception.ExceptionHelper;
import com.namNguyen03.Chat.Backend.service.user.UserService;
import com.namNguyen03.Chat.Backend.service.user.UserRequestModels.LoginRequestModel;
import com.namNguyen03.Chat.Backend.service.user.UserResponseModes.LoginResponseModel;

import static org.junit.Assert.assertEquals;
import org.springframework.http.MediaType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


/**
 * @author nam
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TokenControllerIntegrationTest extends TestBase {
    @Autowired
	private MockMvc mvc;

	@MockBean
	private UserService userService;

    @Test
    public void givenLoginResponseModel_whenValidLoginRequestModelIsUsedToLogin_thenReturnJsonObject() throws Exception{
        LoginRequestModel rq = new LoginRequestModel("nam@gmail.com","123456");
        LoginResponseModel rp = new LoginResponseModel("nam@gmail.com","Nam Nguyen","???JWT");
        Mockito.when(userService.login(rq)).thenReturn(rp);
        
        MvcResult mvcResult = mvc.perform(post("/api/token").content(asJsonString(rq))
            .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(rp, objectMapper.readValue(content, LoginResponseModel.class));
    }   

    @Test
    public void givenLoginResponseModel_whenInvalidLoginRequestModelIsUsedToLogin_thenReturnBadRequest() throws Exception{
        LoginRequestModel rq = new LoginRequestModel("nam.com","123456");
        MvcResult mvcResult = mvc.perform(post("/api/token").content(asJsonString(rq))
            .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals("email should be validation",400, status);

        rq = new LoginRequestModel("nam@gmail.com","156");
        mvcResult = mvc.perform(post("/api/token").content(asJsonString(rq))
            .contentType(MediaType.APPLICATION_JSON)).andReturn();
        status = mvcResult.getResponse().getStatus();
        assertEquals("password should be validation",400, status);
    }

    @Test
    public void givenLoginResponseModel_whenUsernameOrPasswordIncorrectIsUsedToLogin_thenReturnBadRequest() throws Exception {
        LoginRequestModel rq = new LoginRequestModel("nam@gmail.com","123456");
        Mockito.when(userService.login(rq)).thenThrow(new BusinessException("username or password incorrect"));
        MvcResult mvcResult = mvc.perform(post("/api/token").content(asJsonString(rq))
            .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();	
        assertEquals(400, status);
		assertEquals("username or password incorrect",objectMapper.readValue(content, ExceptionHelper.ExceptionResponse.class).getMessage());
    }
}
