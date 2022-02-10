/**
 * 
 */
package com.namNguyen03.Chat.Backend.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.namNguyen03.Chat.Backend.exception.BusinessException;
import com.namNguyen03.Chat.Backend.model.User;
import com.namNguyen03.Chat.Backend.repository.UserRepo;
import com.namNguyen03.Chat.Backend.service.user.*;
import com.namNguyen03.Chat.Backend.service.user.UserRequestModels.RegisterRequestModel;
import com.namNguyen03.Chat.Backend.service.user.UserResponseModes.RegisterResponseModel;

/**
 * @author nam
 *
 */
@RunWith(SpringRunner.class)
public class UserServiceIntegrationTest {
	
	@TestConfiguration
    static class UserServiceImplTestContextConfiguration {
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }

        @Bean
        public ModelMapper initModelMapper() {
            return new ModelMapper();
        }
    }

	@Autowired
	private UserService userService;

    @MockBean
    private UserRepo userRepo;

    @MockBean
    private ModelMapper mapper;

    private final  ModelMapper m = new ModelMapper();

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void givenUser_whenValidUserIsUsedToRegister_thenReturnUser(){
    	
        String username = "nam_nguyen@gmail.com";
        Mockito.when(userRepo.existsByUsername(username)).thenReturn(false);
        RegisterRequestModel user = new RegisterRequestModel();
        user.setUsername(username);
        user.setPassword("12341234");
        User rq = m.map(user, User.class);
        RegisterResponseModel rp = m.map(rq,RegisterResponseModel.class);

        Mockito.when(mapper.map(user,User.class)).thenReturn(rq);
        Mockito.when(userRepo.save(rq)).thenReturn(rq);
        Mockito.when(mapper.map(rq,RegisterResponseModel.class)).thenReturn(rp);

        assertThat(userService.register(user)).isNotNull();
    }

    @Test()
    public void whenUsernameExistsIsUsedToRegister_thenThrowBusinessException() throws Exception{
    	expectedEx.expect(BusinessException.class);
        expectedEx.expectMessage("username exists");
        
        String username = "nam_nguyen@gmail.com";
        Mockito.when(userRepo.existsByUsername(username)).thenReturn(true);
        RegisterRequestModel user = new RegisterRequestModel();
        user.setUsername(username);
        user.setPassword("12341234");
        userService.register(user);
    }
}
