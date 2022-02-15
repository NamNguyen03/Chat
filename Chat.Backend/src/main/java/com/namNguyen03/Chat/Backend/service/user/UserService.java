/**
 * 
 */
package com.namNguyen03.Chat.Backend.service.user;

import com.namNguyen03.Chat.Backend.service.user.UserRequestModels.RegisterRequestModel;
import com.namNguyen03.Chat.Backend.service.user.UserResponseModes.LoginResponseModel;
import com.namNguyen03.Chat.Backend.service.user.UserResponseModes.ProfileResponseModel;
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
	 * @return the userResponse if an user with the given username not exists or userResponse not be {@literal null}.
  	 * @throws BusinessException if username exists.
	 */
    RegisterResponseModel register(RegisterRequestModel user);

	 /**
	 * login and given LoginResponseModel.
	 *
	 * @param loginModel must not be {@literal null}.
	 * @return the loginResponseModel if an user with the given username  exists and @literal password equals password in DB or loginResponseModel not be {@literal null}.
  	 * @throws BusinessException if username not exists or password not equals password in DB.
	 */
    LoginResponseModel login(UserRequestModels.LoginRequestModel loginModel);

	/**
	 * getProfile and given ProfileResponseModel.
	 *
	 * @return the ProfileRequestModel if an user exists.
  	 * @throws BusinessException if user not exists.
	 */
	ProfileResponseModel getProfile();

}
