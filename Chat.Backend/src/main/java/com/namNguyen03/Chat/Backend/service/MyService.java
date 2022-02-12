/**
 * 
 */
package com.namNguyen03.Chat.Backend.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.namNguyen03.Chat.Backend.seedwork.MyEntity;

/**
 * @author nam
 *
 */
public class MyService<M extends MyEntity, R extends JpaRepository<M, Integer> > {

	@Autowired
	private R repo;
	
	public M save(M model) {
		model.setUuid(UUID.randomUUID());
		return repo.save(model);
	}
}
