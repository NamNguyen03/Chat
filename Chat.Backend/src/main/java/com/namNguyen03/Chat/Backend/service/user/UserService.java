/**
 * 
 */
package com.namNguyen03.Chat.Backend.service.user;

import com.namNguyen03.Chat.Backend.service.user.UserRequestModels.RegisterRequestModel;
import com.namNguyen03.Chat.Backend.service.user.UserResponseModes.RegisterResponseModel;

/**
 * @author nam
 *
 */

public interface UserService {

    /**
	 * register new a user.
	 *
	 * @param userRequest must not be {@literal null}.
	 * @return the userResponse if an user with the given username not exists or {@literal null}.
  	 * @throws BusinessException if {@literal username} exists.
	 */
    RegisterResponseModel register(RegisterRequestModel user);

}
