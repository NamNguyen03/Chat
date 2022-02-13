/**
 * 
 */
package com.namNguyen03.Chat.Backend.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Before;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.namNguyen03.Chat.Backend.exception.BusinessException;
import com.namNguyen03.Chat.Backend.model.User;
import com.namNguyen03.Chat.Backend.repository.UserRepo;
import com.namNguyen03.Chat.Backend.security.jwt.JwtUtils;
import com.namNguyen03.Chat.Backend.service.user.*;
import com.namNguyen03.Chat.Backend.service.user.UserRequestModels.LoginRequestModel;
import com.namNguyen03.Chat.Backend.service.user.UserRequestModels.RegisterRequestModel;
import com.namNguyen03.Chat.Backend.service.user.UserResponseModes.LoginResponseModel;
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

    @MockBean
	private JwtUtils jwtUtils;

    @MockBean
	private AuthenticationManager authenticationManager;

	@MockBean
	private PasswordEncoder encoder;

    private final  ModelMapper m = new ModelMapper();

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        Mockito.when(encoder.encode("123456")).thenReturn("???encode");
    }

    @Test
    public void whenValidUserIsUsedToRegister_thenReturnUser(){
    	
        String username = "nam_nguyen@gmail.com";
        Mockito.when(userRepo.existsByUsername(username)).thenReturn(false);
        RegisterRequestModel user = new RegisterRequestModel();
        user.setUsername(username);
        user.setPassword("123456");
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
        user.setPassword("123456");
        userService.register(user);
    }

    @Test 
    public void whenValidLoginRequestModel_thenReturnLoginResponseModel(){
        LoginRequestModel loginModel = new LoginRequestModel("nam@gmail.com", "123456");
        User user = new User();
        user.setFullName("Nguyen Duc Nam");
        user.setPassword("???encode");
        user.setUsername("nam@gmail.com");
        Optional<User> userOpt = Optional.of(user);
        Authentication authentication = Mockito.mock(Authentication.class);
        
        Mockito.when(authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginModel.getUsername(), loginModel.getPassword())
        )).thenReturn(authentication);
        Mockito.when(userRepo.findByUsername(loginModel.getUsername())).thenReturn(userOpt);
        Mockito.when(encoder.matches("123456", "???encode")).thenReturn(true);
        Mockito.when(jwtUtils.generateJwtToken(authentication)).thenReturn("???jwt");

        assertEquals(userService.login(loginModel), new LoginResponseModel("nam@gmail.com","Nguyen Duc Nam", "???jwt"));
    }

    @Test
    public void whenPasswordNotEquals_thenThrowBusinessException(){
        expectedEx.expect(BusinessException.class);
        expectedEx.expectMessage("username or password incorrect");
        
        LoginRequestModel loginModel = new LoginRequestModel("nam@gmail.com", "123456");
        User user = new User();
        user.setPassword("???encode");
        Optional<User> userOpt = Optional.of(user);
        Mockito.when(userRepo.findByUsername(loginModel.getUsername())).thenReturn(userOpt);
        Mockito.when(encoder.matches("123456", "???encode")).thenReturn(false);
        userService.login(loginModel);
    }
}
