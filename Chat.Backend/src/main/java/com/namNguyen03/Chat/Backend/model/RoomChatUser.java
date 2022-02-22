/**
 * 
 */
package com.namNguyen03.Chat.Backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nam
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class RoomChatUser{
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne()
    @JoinColumn(name="room_chat_id")
    private RoomChat roomChat;
}