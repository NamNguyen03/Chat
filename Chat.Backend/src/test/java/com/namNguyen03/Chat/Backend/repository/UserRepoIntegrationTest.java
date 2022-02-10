/**
 * 
 */
package com.namNguyen03.Chat.Backend.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.namNguyen03.Chat.Backend.model.User;

/**
 * @author nam
 *
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepoIntegrationTest {
	@Autowired
    private TestEntityManager entityManager;

	@Autowired
	private UserRepo userRepo;
	
	@Test
	public void givenUser_whenUsernameExistsIsUsedToFindUser_thenReturnUser() {
		String username = "nam_nguyen@gmail.com";
		User user = new User();
		user.setUsername(username);
		entityManager.persist(user);
		entityManager.flush();
		assertThat(userRepo.findByUsername(username)).isNotNull();	
	}

	@Test
	public void givenUser_whenUsernameNotExistsIsUsedToFindUser_thenReturnEmptyUser() {
		String username = "nam_nguyen@gmail.com";
		User user = new User();
		user.setUsername(username);
		entityManager.persist(user);
		entityManager.flush();
		assertThat(userRepo.findByUsername("username_not_exists")).isEmpty();	
	}

	@Test
	public void whenExistsUsernameIsUsedToCheckUser_thenReturnTrue() {
		String username = "nam_nguyen@gmail.com";
		User user = new User();
		user.setUsername(username);
		entityManager.persist(user);
		entityManager.flush();
		assertThat(userRepo.existsByUsername(username)).isTrue();	
	}

	@Test
	public void whenNotExistsUsernameIsUsedToCheckUser_thenReturnFalse() {
		String username = "nam_nguyen@gmail.com";
		User user = new User();
		user.setUsername(username);
		entityManager.persist(user);
		entityManager.flush();
		assertThat(userRepo.existsByUsername("username_not_exists")).isFalse();	
	}
}
