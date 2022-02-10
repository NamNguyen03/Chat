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
	 * @param username
	 * @return
	 */
	Optional<User> findByUsername(String username);

}
