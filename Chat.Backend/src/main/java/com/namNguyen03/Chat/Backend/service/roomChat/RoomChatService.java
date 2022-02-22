/**
 * 
 */
package com.namNguyen03.Chat.Backend.service.roomChat;

import com.namNguyen03.Chat.Backend.service.model.share.PageRequestModel;
import com.namNguyen03.Chat.Backend.service.model.share.PageResponseModel;
import com.namNguyen03.Chat.Backend.service.roomChat.RoomChatResponseModels.GetPageRoomChatResponse;

/**
 * @author nam
 *
 */
public interface RoomChatService {

	/**
	 * search room chat
	 * field sort name
	 * field search name, createBy
	 * @param pageRequestModel
	 * @return PageResponseModel<GetPageRoomChatResponse> 
	 */
    PageResponseModel<GetPageRoomChatResponse> search(PageRequestModel pageRequestModel);

}
