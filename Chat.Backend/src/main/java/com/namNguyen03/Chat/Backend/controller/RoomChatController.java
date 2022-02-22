/**
 * 
 */
package com.namNguyen03.Chat.Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.namNguyen03.Chat.Backend.service.model.share.PageRequestModel;
import com.namNguyen03.Chat.Backend.service.model.share.PageResponseModel;
import com.namNguyen03.Chat.Backend.service.roomChat.RoomChatService;
import com.namNguyen03.Chat.Backend.service.roomChat.RoomChatResponseModels.GetPageRoomChatResponse;

/**
 * @author nam
 *
 */
@RestController
@RequestMapping("/api/room-chat")
@CrossOrigin(origins = "*")
public class RoomChatController {
	
	@Autowired
	private RoomChatService roomChatService;

	@GetMapping()
	public PageResponseModel<GetPageRoomChatResponse> getPage(
		@RequestParam(value = "pageCurrent", defaultValue = "1") int pageCurrent,
		@RequestParam(value = "itemPerPage", defaultValue = "10") int itemPerPage,
		@RequestParam(value = "fieldNameSort", required = false) String fieldNameSort,
		@RequestParam(value = "isIncrementSort", defaultValue = "true") boolean isIncrementSort,
		@RequestParam(value = "fieldNameSearch", required = false) String fieldNameSearch,
		@RequestParam(value = "valueFieldNameSearch", required = false) String valueFieldNameSearch
 		){

		return roomChatService.search(new PageRequestModel(
			pageCurrent,
			itemPerPage,
			fieldNameSort,
			isIncrementSort,
			fieldNameSearch,
			valueFieldNameSearch
		));
	}
}
