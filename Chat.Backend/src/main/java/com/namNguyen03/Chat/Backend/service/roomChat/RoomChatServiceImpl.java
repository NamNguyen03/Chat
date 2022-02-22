/**
 * 
 */
package com.namNguyen03.Chat.Backend.service.roomChat;

import com.namNguyen03.Chat.Backend.model.RoomChat;
import com.namNguyen03.Chat.Backend.repository.RoomChatRepo;
import com.namNguyen03.Chat.Backend.service.model.share.PageRequestModel;
import com.namNguyen03.Chat.Backend.service.model.share.PageResponseModel;
import com.namNguyen03.Chat.Backend.service.roomChat.RoomChatResponseModels.GetPageRoomChatResponse;
import com.namNguyen03.Chat.Backend.untils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author nam
 *
 */
@Service
public class RoomChatServiceImpl implements RoomChatService {

    @Autowired
    private RoomChatRepo roomChatRepo;
    
    @Autowired
    private ObjectMapperUtils objectMapperUtils;

    @Override
    public PageResponseModel<GetPageRoomChatResponse> search(PageRequestModel pageRequestModel) {        
        int page = pageRequestModel.getPageCurrent()-1;
        int size = pageRequestModel.getItemPerPage();
        boolean isAscending = pageRequestModel.isIncrementSort();
        String fieldNameSearch = pageRequestModel.getFieldNameSearch();
        String valueSearch = pageRequestModel.getValueSearch();

        Pageable pageable = PageRequest.of(page, size);
        Page<RoomChat> rs = null;

        if("name".equals(pageRequestModel.getFieldNameSort())){
            pageable = PageRequest.of(page, size , isAscending ? Sort.by("name").ascending(): Sort.by("name").descending());
        }
        if("name".equals(fieldNameSearch) && null != valueSearch){
            rs = roomChatRepo.searchByName('%'+ valueSearch +'%', pageable);
        }

        if("createBy".equals(fieldNameSearch) && null != valueSearch){
            rs = roomChatRepo.searchByCreateBy('%'+ valueSearch +'%', pageable);
        }

        if(!"name".equals(fieldNameSearch) && !"createBy".equals(fieldNameSearch) ){
            rs = roomChatRepo.findAll(pageable);
        }

        if(rs == null){
            return new PageResponseModel<>();
        }

        return objectMapperUtils.mapPageToPageResponseModel(rs, GetPageRoomChatResponse.class);
    }

}
