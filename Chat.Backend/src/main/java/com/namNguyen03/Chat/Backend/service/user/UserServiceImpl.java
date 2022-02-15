/**
 * 
 */
package com.namNguyen03.Chat.Backend.service.user;
import java.util.Optional;
import com.namNguyen03.Chat.Backend.exception.BusinessException;
import com.namNguyen03.Chat.Backend.model.User;
import com.namNguyen03.Chat.Backend.repository.UserRepo;
import com.namNguyen03.Chat.Backend.security.jwt.JwtUtils;
import com.namNguyen03.Chat.Backend.service.MyService;
import com.namNguyen03.Chat.Backend.service.user.UserRequestModels.RegisterRequestModel;
import com.namNguyen03.Chat.Backend.service.user.UserResponseModes.LoginResponseModel;
import com.namNguyen03.Chat.Backend.service.user.UserResponseModes.ProfileResponseModel;
import com.namNguyen03.Chat.Backend.service.user.UserResponseModes.RegisterResponseModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
	private JwtUtils jwtUtils;

    @Autowired
	private AuthenticationManager authenticationManager;

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

    @Override
    public LoginResponseModel login(UserRequestModels.LoginRequestModel loginModel) {
        Optional<User> userOpt = userRepository.findByUsername(loginModel.getUsername());

        if(userOpt.isEmpty()) {
            throw new BusinessException("username or password incorrect");
        }
        if(!encoder.matches(loginModel.getPassword(),userOpt.get().getPassword())){
			throw new BusinessException("username or password incorrect");
		}
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginModel.getUsername(), loginModel.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication, userOpt.get());
        
        return new LoginResponseModel(userOpt.get().getUsername(), userOpt.get().getFullName(), jwt);
    }

    @Override
    public ProfileResponseModel getProfile() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            Optional<User> userOpt = userRepository.findByUsername(username);
            if(userOpt.isEmpty()){
                throw new BusinessException("user not exists");
            }
            return mapper.map(userOpt.get(),ProfileResponseModel.class);
        } 

        return null;
    }

}
