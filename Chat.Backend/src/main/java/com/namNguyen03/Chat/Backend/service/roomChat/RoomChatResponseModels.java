/**
 * 
 */
package com.namNguyen03.Chat.Backend.service.roomChat;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nam
 *
 */
public class RoomChatResponseModels {

	private RoomChatResponseModels() {
		
	}
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class GetPageRoomChatResponse{
	    private UUID uuid;
	    private String name;
	    private String description; 
	    private boolean isPublic = false;
	    private String createBy;
		private LocalDate createDate;
	}
}
