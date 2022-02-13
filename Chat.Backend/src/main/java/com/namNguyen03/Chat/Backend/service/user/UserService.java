/**
 * 
 */
package com.namNguyen03.Chat.Backend.service.user;
import com.namNguyen03.Chat.Backend.service.user.UserRequestModels.RegisterRequestModel;
import com.namNguyen03.Chat.Backend.service.user.UserResponseModes.LoginResponseModel;
import com.namNguyen03.Chat.Backend.service.user.UserResponseModes.RegisterResponseModel;

/**
 * @author nam
 *
 */

public interface UserService {

    /**
	 * register new a user.
	 *
	 * @param user must not be {@literal null}.
	 * @return the userResponse if an user with the given username not exists or {@literal null}.
  	 * @throws BusinessException if {@literal username} exists.
	 */
    RegisterResponseModel register(RegisterRequestModel user);

	 /**
	 * login and given LoginResponseModel.
	 *
	 * @param loginModel must not be {@literal null}.
	 * @return the loginResponseModel if an user with the given username exists and {@literal password}  equals password in DB or {@literal null}.
  	 * @throws BusinessException if {@literal username} not exists or {@literal password} not equals password in DB.
	 */
    LoginResponseModel login(UserRequestModels.LoginRequestModel loginModel);

}
