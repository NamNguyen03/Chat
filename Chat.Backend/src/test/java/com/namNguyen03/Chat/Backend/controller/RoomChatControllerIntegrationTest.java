package com.namNguyen03.Chat.Backend.controller;

import com.namNguyen03.Chat.Backend.TestBase;
import com.namNguyen03.Chat.Backend.service.model.share.PageRequestModel;
import com.namNguyen03.Chat.Backend.service.model.share.PageResponseModel;
import com.namNguyen03.Chat.Backend.service.roomChat.RoomChatService;
import com.namNguyen03.Chat.Backend.service.roomChat.RoomChatResponseModels.GetPageRoomChatResponse;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

/**
 * @author nam
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RoomChatControllerIntegrationTest extends TestBase {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private RoomChatService roomChatService;

    private final  Class< PageResponseModel<GetPageRoomChatResponse>> clazzRp = (Class< PageResponseModel<GetPageRoomChatResponse>>) (Object) PageResponseModel.class;

    @Test 
    @WithUserDetails("nam@gmail.com")
    public void givenPageResponseRoomChat_whenExistsRoomChatIsUsedToSearchRoomChat_theReturnJsonArray() throws Exception{
        PageRequestModel rq = new PageRequestModel();
        rq.setItemPerPage(10);
        rq.setPageCurrent(1);
        PageResponseModel<GetPageRoomChatResponse> rp = new PageResponseModel<>();
        rp.setPageCurrent(1);

        Mockito.when(roomChatService.search(rq)).thenReturn(rp);

        MvcResult mvcResult = mvc.perform(get("/api/room-chat")
            .param("pageCurrent", "1")
            .param("itemPerPage", "10")
            .contentType(MediaType.APPLICATION_JSON))
            .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals("should return 200 when page current is 1 and item per page is 10", 200, status);
        String content = mvcResult.getResponse().getContentAsString();

        assertEquals("should return page room chat when page current is 1 and item per page is 10", rp, objectMapper.readValue(content,clazzRp));

        mvcResult = mvc.perform(get("/api/room-chat")
            .param("pageCurrent", "-1")
            .param("itemPerPage", "-1")
            .contentType(MediaType.APPLICATION_JSON))
            .andReturn();
           
        status = mvcResult.getResponse().getStatus();
        assertEquals("should return 200 when page current is -1 and item per page is -1", 200, status);
        content = mvcResult.getResponse().getContentAsString();
           
        assertEquals("should return page room chat when page current is -1 and item per page is -1", rp, objectMapper.readValue(content,clazzRp));
    } 
}
