/**
 * 
 */
package com.namNguyen03.Chat.Backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.namNguyen03.Chat.Backend.model.User;

/**
 * @author nam
 *
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	/**
	 * Retrieves an user by its username.
	 *
	 * @param username must not be {@literal null}.
	 * @return the user with the given id or {@literal Optional#empty()} if none found.
	 *
	 */
	Optional<User> findByUsername(String username);

	/**
	 * Returns whether an User with the given username exists.
	 *
	 * @param username must not be {@literal null}.
	 * @return {@literal true} if an user with the given username exists, {@literal false} otherwise.
	 *
	 */
	boolean existsByUsername(String username);


}
