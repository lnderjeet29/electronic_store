package com.lcwd.electronic_store.electronic_store.services.impl;

import com.lcwd.electronic_store.electronic_store.config.ProjectConfig;
import com.lcwd.electronic_store.electronic_store.dto.PageResponse;
import com.lcwd.electronic_store.electronic_store.dto.UserDto;
import com.lcwd.electronic_store.electronic_store.entity.User;
import com.lcwd.electronic_store.electronic_store.exception.ResourceNotFoundException;
import com.lcwd.electronic_store.electronic_store.helper.PageHelper;
import com.lcwd.electronic_store.electronic_store.repository.UserRepo;
import com.lcwd.electronic_store.electronic_store.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    public UserRepo userRepo;
    @Autowired
    public ProjectConfig projectConfig;
    @Value("${img.profile.upload}")
    public String ImagePath;
    Logger logger= LoggerFactory.getLogger(UserServicesImpl.class);
    @Override
    public UserDto createUser(UserDto userDto) {
        Random random=new Random();
        int uid=random.nextInt(9999);
        userDto.setUserId(uid);
        User user=UserDtotoUser(userDto);
        userRepo.save(user);
        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, int id) {
        User search=userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("id data not found..."));
        search.setUserName(userDto.getUserName());
        search.setImage_name(userDto.getImage_name());
        search.setPassword(userDto.getPassword());
        userRepo.save(search);
        return usertoUserdto(search);
    }

    @Override
    public String deleteUser(int id) {
        User user=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("id data not found..."));
        String fullPath=ImagePath+user.getImage_name();
        Path path= Paths.get(fullPath);
        try {
            Files.delete(path);
        }catch (NoSuchFileException e){
            logger.info("No such file found on this path...");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        userRepo.delete(user);
        return "data deleted...";
    }

    @Override
    public PageResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {
        logger.info("userServicesimple invoke{}",sortBy);
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<User> page=userRepo.findAll(pageable);
        PageResponse<UserDto> pageResponse= PageHelper.getPageResponse(page,UserDto.class);
        return pageResponse;
    }

    @Override
    public UserDto getUser(int id) {
        User user=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("id data not found..."));
        return usertoUserdto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user=userRepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("email id data not found..."));
        return usertoUserdto(user);
    }

    private User UserDtotoUser(UserDto userDto) {
//        mannually
//        User user=User.builder()
//                .user_id(userDto.getUser_id())
//                .email(userDto.getEmail())
//                .User_name(userDto.getUser_name())
//                .password(userDto.getPassword())
//                .image_name(userDto.getImage_name())
//                .build();

//        using mapper
        return projectConfig.modelMapper().map(userDto,User.class);
    }
    private UserDto usertoUserdto(User user){

//        UserDto userDto=UserDto.builder()
//                .user_id(user.getUser_id())
//                .image_name(user.getImage_name())
//                .User_name(user.getUser_name())
//                .password(user.getPassword())
//                .email(user.getEmail())
//                .build();

        return projectConfig.modelMapper().map(user,UserDto.class);
    }
}
