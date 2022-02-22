package com.namNguyen03.Chat.Backend.repository;

import com.namNguyen03.Chat.Backend.model.RoomChatUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nam
 *
 */
@Repository
public interface RoomChatUserRepo  extends JpaRepository<RoomChatUser, Integer> {
    
}
