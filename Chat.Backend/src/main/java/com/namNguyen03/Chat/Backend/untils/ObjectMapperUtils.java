package com.namNguyen03.Chat.Backend.untils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.namNguyen03.Chat.Backend.service.model.share.PageResponseModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


/**
 * @author nam
 *
 */
@Component
public class ObjectMapperUtils {
    
    @Autowired
    private ModelMapper mapper;

    public <D, T> PageResponseModel<D> mapPageToPageResponseModel(Page<T> page , Class<D> outCLass){
        return new PageResponseModel<>(
            page.getNumber() + 1,
            page.getTotalPages(),
            mapAll(page.getContent(), outCLass)
        );
    }

    public <D, T> List<D> mapAll(Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()  
                .map(entity -> map(entity, outCLass))
                .collect(Collectors.toList());
    }

    public <D, T> D map(T entity, Class<D> outClass) {
        return mapper.map(entity, outClass);
    }

}
