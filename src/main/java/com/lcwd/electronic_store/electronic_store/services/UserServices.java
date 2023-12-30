package com.lcwd.electronic_store.electronic_store.services;

import com.lcwd.electronic_store.electronic_store.dto.PageResponse;
import com.lcwd.electronic_store.electronic_store.dto.UserDto;

import java.util.List;

public interface UserServices {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,int id);
    String deleteUser(int id);
    PageResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);
    UserDto getUser(int id);
    UserDto getUserByEmail(String email);


}
