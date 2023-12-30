package com.lcwd.electronic_store.electronic_store.controller;

import com.lcwd.electronic_store.electronic_store.dto.ApiRepositoryMessage;
import com.lcwd.electronic_store.electronic_store.dto.ImageResponse;
import com.lcwd.electronic_store.electronic_store.dto.PageResponse;
import com.lcwd.electronic_store.electronic_store.dto.UserDto;
import com.lcwd.electronic_store.electronic_store.services.UserServices;
import com.lcwd.electronic_store.electronic_store.services.fileServices;
import com.sun.net.httpserver.spi.HttpServerProvider;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController{
    @Autowired
    public UserServices userServices;
    @Autowired
    public fileServices fileService;
    @Value("${img.profile.upload}")
    public String imagePath;
    Logger logger= LoggerFactory.getLogger(UserController.class);
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto user=userServices.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<PageResponse<UserDto>> getAllUser
            (@RequestParam(value = "pageNumber",defaultValue = "0",required = false)int pageNumber,
             @RequestParam(value = "pageSize",defaultValue = "10",required = false)int pageSize,
             @RequestParam(value = "sortBy",defaultValue = "email",required = false)String sortBy,
             @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir
            ){
        logger.info("getall user method invoke");
        PageResponse<UserDto> list=userServices.getAllUser(pageNumber,pageSize,sortBy,sortDir);
        logger.info("get list {}",list);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") int user_Id){
        UserDto userDto=userServices.getUser(user_Id);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") int id,@Valid @RequestBody UserDto userDto){
        UserDto userDto1=userServices.updateUser(userDto,id);
        return new ResponseEntity<>(userDto1,HttpStatus.OK);
    }
    @GetMapping("/search/email")
    public ResponseEntity<UserDto> searchUserByEmail(@RequestParam("email") String email){
        UserDto userDto=userServices.getUserByEmail(email);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiRepositoryMessage> deleteUser(@PathVariable("id") int id){
        userServices.deleteUser(id);
        ApiRepositoryMessage message=ApiRepositoryMessage.builder().message("user deleted successfully...").status(true)
                .httpStatus(HttpStatus.OK).build();
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @PostMapping("/uploadImag/{id}")
    public ResponseEntity<ImageResponse> uploadImage(@RequestParam("file")MultipartFile file, @PathVariable("id") int id) throws IOException {
        logger.info("id = {}",id);
        String imageName=fileService.fileUpload(file,imagePath);
        logger.info("file upload method run...");
        UserDto userDto=userServices.getUser(id);
        userDto.setImage_name(imageName);
        userServices.updateUser(userDto,id);
        ImageResponse imageResponse=ImageResponse.builder().imageName(imageName).status(true).httpStatus(HttpStatus.ACCEPTED).build();
        return new ResponseEntity<>(imageResponse,HttpStatus.OK);
    }
    @GetMapping("/getImage/{id}")
    public void serveImage(@PathVariable("id") int userId, HttpServletResponse response) throws IOException {
        UserDto user=userServices.getUser(userId);
        InputStream resource=fileService.getFileResource(imagePath,user.getImage_name());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());

    }
}
