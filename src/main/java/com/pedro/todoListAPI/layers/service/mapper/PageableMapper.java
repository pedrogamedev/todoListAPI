package com.pedro.todoListAPI.layers.service.mapper;

import com.pedro.todoListAPI.layers.domain.dto.PageableRequestDTO;
import org.hibernate.query.SortDirection;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;

@Component
public class PageableMapper {

    public Pageable toPageable(PageableRequestDTO dto){
        Sort sort = dto.sort();
        if(dto.sort() == null || dto.sort().isUnsorted() || dto.sort().isEmpty()){
            sort = Sort.by("title");
        }
         return PageRequest.of(
                 Integer.parseInt(dto.page()),
                 Integer.parseInt(dto.size()),
                 sort
        );
    }
}
