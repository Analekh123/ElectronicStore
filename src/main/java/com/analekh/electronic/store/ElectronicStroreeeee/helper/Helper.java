package com.analekh.electronic.store.ElectronicStroreeeee.helper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import com.analekh.electronic.store.ElectronicStroreeeee.dto.PageableResponse;
import com.analekh.electronic.store.ElectronicStroreeeee.dto.UserDto;
import com.analekh.electronic.store.ElectronicStroreeeee.entities.User;

public class Helper {

	public static <U,V> PageableResponse<V> getPageableResponse(Page<U> page,Class<V> type){
		
		List<U> entity = page.getContent();

		List<V> dtoList = entity.stream()
				.map(object -> new ModelMapper().map(object,type))
				.collect(Collectors.toList());

		PageableResponse<V> response = new PageableResponse<V>();
		response.setContent(dtoList);
		response.setPageNumber(page.getNumber());
		response.setPageSize(page.getSize());
		response.setTotalElements(page.getTotalElements());
		response.setTotalPages(page.getTotalPages());
		response.setLastPage(page.isLast());

		return response;
	}
}
