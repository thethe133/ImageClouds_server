package com.sparta.week06login.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class LoginResponseDto {
    private Long uid;
    private String username;
    private String nickname;

    public LoginResponseDto(Long uid, String username, String nickname){
        this.uid = uid;
        this.username = username;
        this.nickname = nickname;
    }
}