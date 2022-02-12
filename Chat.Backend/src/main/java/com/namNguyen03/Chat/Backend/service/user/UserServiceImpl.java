/**
 * 
 */
package com.namNguyen03.Chat.Backend.service.user;

import com.namNguyen03.Chat.Backend.exception.BusinessException;
import com.namNguyen03.Chat.Backend.model.User;
import com.namNguyen03.Chat.Backend.repository.UserRepo;
import com.namNguyen03.Chat.Backend.service.MyService;
import com.namNguyen03.Chat.Backend.service.user.UserRequestModels.RegisterRequestModel;
import com.namNguyen03.Chat.Backend.service.user.UserResponseModes.RegisterResponseModel;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author nam
 *
 */
@Service
public class UserServiceImpl extends MyService<User, UserRepo> implements UserService {

    @Autowired
    private UserRepo userRepository;
    
    @Autowired
    private ModelMapper mapper;


	@Autowired
	private PasswordEncoder encoder;

    @Override
    public RegisterResponseModel register(RegisterRequestModel user) {
        if(userRepository.existsByUsername(user.getUsername())){
            throw new BusinessException("username exists");
        }
        var rq = mapper.map(user,User.class);
        rq.setPassword( encoder.encode(user.getPassword()));
        return mapper.map(super.save(rq), RegisterResponseModel.class);
    }

}
