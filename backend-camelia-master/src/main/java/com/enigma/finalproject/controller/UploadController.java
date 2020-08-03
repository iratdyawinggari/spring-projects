package com.enigma.finalproject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.finalproject.entity.User;
import com.enigma.finalproject.entity.UserInfo;
import com.enigma.finalproject.service.UserInfoService;
import com.enigma.finalproject.service.UserService;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

@RestController
@RequestMapping("/upload")
public class UploadController {

@Autowired
UserService userService;

@Autowired
UserInfoService userInfoService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> doUpload(@RequestBody byte[] file, @RequestParam("userId") Integer id) throws UnirestException {
    User user = userService.getUserById(id);
    String name =user.getUsername()+"images";
    UserInfo userInfo = user.getUserInfo();
    userInfo.setPhotoPath(name);
    userInfoService.addUserInfo(userInfo);
    HttpResponse<String> response = Unirest.post("https://content.dropboxapi.com/2/files/upload")
     .header("Content-Type", "application/octet-stream")
     .header("Authorization", "Bearer YtUMg6S7KLAAAAAAAAAAbujCVn_NXjGpImb4Kh33QoGe1wnXGHSJFf-Ye91SrfnO")
     .header("Dropbox-API-Arg", "{\"path\":\"/scolilo-images/"+name+".png\",\"mode\":\"add\",\"autorename\":true,\"mute\":false}").body(file)
                .asString();
        return ResponseEntity.ok(response.getBody());
    }
   
    @PostMapping("/delete")
    public ResponseEntity<?> deleteFile(@RequestParam("name") String name) throws UnirestException {
    HttpResponse<String> response = Unirest.post("https://api.dropboxapi.com/2/files/delete_v2")
     .header("Authorization", "Bearer YtUMg6S7KLAAAAAAAAAAbujCVn_NXjGpImb4Kh33QoGe1wnXGHSJFf-Ye91SrfnO")
     .header("Content-Type", "application/json")
     .body("{\n\t\"path\": \"/scolilo-images/"+name+".png\"\n}")
     .asString();
    return ResponseEntity.ok(response.getBody());
    }
   
    @PostMapping("/update")
    public ResponseEntity<?> updateFile(@RequestBody byte[] file,@RequestParam("userId") Integer id) throws UnirestException {
    String name = userService.getUserById(id).getUsername()+"images";
    deleteFile(name);
    return doUpload(file, id);
    }
}