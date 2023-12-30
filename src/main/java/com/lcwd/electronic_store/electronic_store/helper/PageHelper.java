package com.lcwd.electronic_store.electronic_store.helper;

import com.lcwd.electronic_store.electronic_store.dto.PageResponse;
import com.lcwd.electronic_store.electronic_store.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class PageHelper {
    public static <U,V>PageResponse<V> getPageResponse(Page<U> page,Class<V> type){
        List<U> list1=page.getContent();
        List<V> list=list1.stream().map(object->new ModelMapper().map(object,type)).collect(Collectors.toList());
        PageResponse<V> pageResponse=new PageResponse<>();
        pageResponse.setContent(list);
        pageResponse.setPageNumber(page.getNumber());
        pageResponse.setTotalElement(page.getTotalElements());
        pageResponse.setPageSize(page.getSize());
        pageResponse.setLastPage(page.isLast());
        pageResponse.setTotalPage(page.getTotalPages());
        return pageResponse;
    }
}
