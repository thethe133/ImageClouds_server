package com.sparta.week06login.controller;

import com.sparta.week06login.dto.DuplicateCheckResponseDto;
import com.sparta.week06login.dto.LoginResponseDto;
import com.sparta.week06login.dto.SignupRequestDto;
import com.sparta.week06login.dto.SignupResponseDto;
import com.sparta.week06login.security.UserDetailsImpl;
import com.sparta.week06login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //회원가입 요청
    @PostMapping("/user/signup")
    public SignupResponseDto registerUser(@RequestBody SignupRequestDto requestDto){
        return userService.registerUser(requestDto);
    }

    // 로그인 여부 확인
    // 궁금증 1. 우선 클라이언트에서 쿠키 값이 있는지를 1차 확인하고, islogin을 실행하는 건가? 이게 필요한 절차인가?
    // 궁금증 2. login성공 후 바로 실행하는 함수인가?
    @PostMapping("/user/islogin") //api 바꿔야하나?
    public LoginResponseDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long uid = userDetails.getUser().getUid();
        String username = userDetails.getUser().getUsername();
        String nickname = userDetails.getUser().getNickname();
    //없으면 어쩔티비
        return new LoginResponseDto(uid, username, nickname);
    }

//    //username 중복체크
//    @PostMapping("/user/usernameCheck")
//    public Boolean duplicateUsernameCheck(DuplicateCheckResponseDto responseDto){
//        String inputUsername = responseDto.getDuplicateCheck();
//
//
//    }
//
//    //닉네임 중복체크
//    @PostMapping("/user/nicknameCheck")
//    public Boolean duplicateNicknameCheck(DuplicateCheckResponseDto responseDto){
//        String inpuNickname = responseDto.getDuplicateCheck();
//
//    }
//
//    //이메일 중복체크
//    @PostMapping("/user/emailCheck")
//    public Boolean duplicateEmailCheck(DuplicateCheckResponseDto responseDto){
//        String inputEmail = responseDto.getDuplicateCheck();
//
//    }

}