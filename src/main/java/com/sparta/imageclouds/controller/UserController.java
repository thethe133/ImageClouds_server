package com.sparta.imageclouds.controller;

import com.sparta.imageclouds.dto.SignupRequestDto;
import com.sparta.imageclouds.dto.SignupResponseDto;
import com.sparta.imageclouds.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;
//    private final

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //회원가입 요청
    @PostMapping("/user/signup")
    public SignupResponseDto registerUser(@RequestBody SignupRequestDto requestDto){
        SignupResponseDto responseDto = userService.registerUser(requestDto);
        return responseDto;
    }

//    // 회원 관련 정보 받기??뭐지
//    @PostMapping("/user/userinfo")
//    @ResponseBody
//    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        String username = userDetails.getUser().getUsername();
//        UserRoleEnum role = userDetails.getUser().getRole();
//        boolean isAdmin = (role == UserRoleEnum.ADMIN);
//
//        return new UserInfoDto(username, isAdmin);
//    }
}
