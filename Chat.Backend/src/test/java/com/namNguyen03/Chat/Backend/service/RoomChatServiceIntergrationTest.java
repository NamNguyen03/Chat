package com.namNguyen03.Chat.Backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.namNguyen03.Chat.Backend.model.RoomChat;
import com.namNguyen03.Chat.Backend.repository.RoomChatRepo;
import com.namNguyen03.Chat.Backend.service.model.share.PageRequestModel;
import com.namNguyen03.Chat.Backend.service.model.share.PageResponseModel;
import com.namNguyen03.Chat.Backend.service.roomChat.RoomChatService;
import com.namNguyen03.Chat.Backend.service.roomChat.RoomChatServiceImpl;
import com.namNguyen03.Chat.Backend.service.roomChat.RoomChatResponseModels.GetPageRoomChatResponse;
import com.namNguyen03.Chat.Backend.untils.ObjectMapperUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author nam
 *
 */
@RunWith(SpringRunner.class)
public class RoomChatServiceIntergrationTest {

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {
        @Bean
        public RoomChatService userService() {
            return new RoomChatServiceImpl();
        }
    }

    @Autowired
    private RoomChatService service;

    @MockBean
    private RoomChatRepo roomChatRepo;

    @MockBean
    private ObjectMapperUtils objectMapperUtils; 

    @Test
    public void whenExistsRoomChatIsUsedToSearchRoomChatService_thenReturnPageRoomChat(){
        int page = 2;
        int size = 3;
        PageRequestModel rq  = new PageRequestModel();
        rq.setPageCurrent(page);
        rq.setItemPerPage(size);
        Pageable pageable = PageRequest.of(page, size);
        Page<RoomChat> pageResponse = Mockito.mock(Page.class);
        PageResponseModel<GetPageRoomChatResponse> expected = new PageResponseModel<>();

        Mockito.when(roomChatRepo.findAll(pageable)).thenReturn(pageResponse);   
        Mockito.when(objectMapperUtils.mapPageToPageResponseModel(pageResponse, GetPageRoomChatResponse.class)).thenReturn(expected);    

        PageResponseModel<GetPageRoomChatResponse> rp = service.search(rq);

        assertEquals(expected, rp);

    }

    @Test
    public void whenExistsRoomChatLikeNameIsUsedToSearchRoomChatService_thenReturnPageRoomChat(){
        int page = 2;
        int size = 3;
        String fieldNameSearch = "name";
        String valueSearch = "otherName";
        PageRequestModel rq  = new PageRequestModel();
        rq.setPageCurrent(page);
        rq.setItemPerPage(size);
        rq.setFieldNameSearch(fieldNameSearch);
        rq.setValueSearch(valueSearch);
        Page<RoomChat> pageResponse = Mockito.mock(Page.class);
        Pageable pageable = PageRequest.of(page, size);
        PageResponseModel<GetPageRoomChatResponse> expected = new PageResponseModel<>();
        
        Mockito.when(roomChatRepo.searchByName(valueSearch, pageable)).thenReturn(pageResponse);
        Mockito.when(objectMapperUtils.mapPageToPageResponseModel(pageResponse, GetPageRoomChatResponse.class)).thenReturn(expected);    

        PageResponseModel<GetPageRoomChatResponse> rp = service.search(rq);

        assertEquals(expected, rp);
    }

    @Test
    public void whenExistsRoomChatLikeCreateByIsUsedToSearchRoomChatService_thenReturnPageRoomChat(){
        int page = 2;
        int size = 3;
        String fieldNameSearch = "createBy";
        String valueSearch = "otherCreateBy";
        PageRequestModel rq  = new PageRequestModel();
        rq.setPageCurrent(page);
        rq.setItemPerPage(size);
        rq.setFieldNameSearch(fieldNameSearch);
        rq.setValueSearch(valueSearch);
        Page<RoomChat> pageResponse = Mockito.mock(Page.class);
        Pageable pageable = PageRequest.of(page, size);
        PageResponseModel<GetPageRoomChatResponse> expected = new PageResponseModel<>();

        Mockito.when(roomChatRepo.searchByCreateBy(valueSearch, pageable)).thenReturn(pageResponse);
        Mockito.when(objectMapperUtils.mapPageToPageResponseModel(pageResponse, GetPageRoomChatResponse.class)).thenReturn(expected);    

        PageResponseModel<GetPageRoomChatResponse> rp = service.search(rq);

        assertEquals(expected, rp);
    }

    @Test
    public void whenExistsRoomChatLikeCreateByIsUsedToSearchRoomChatService_thenReturnPageRoomChatAndSortNameAscending(){

        int page = 2;
        int size = 3;
        String fieldNameSearch = "createBy";
        String valueSearch = "otherCreateBy";
        PageRequestModel rq  = new PageRequestModel();
        rq.setPageCurrent(page);
        rq.setItemPerPage(size);
        rq.setFieldNameSearch(fieldNameSearch);
        rq.setValueSearch(valueSearch);
        Page<RoomChat> pageResponse = Mockito.mock(Page.class);
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        PageResponseModel<GetPageRoomChatResponse> expected = new PageResponseModel<>();

        Mockito.when(roomChatRepo.searchByCreateBy(valueSearch, pageable)).thenReturn(pageResponse);
        Mockito.when(objectMapperUtils.mapPageToPageResponseModel(pageResponse, GetPageRoomChatResponse.class)).thenReturn(expected);    

        PageResponseModel<GetPageRoomChatResponse> rp = service.search(rq);

        assertEquals(expected, rp);
    }
}
