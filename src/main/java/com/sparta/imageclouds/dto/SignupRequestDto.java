package com.sparta.imageclouds.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private final String username;
    private final String nickname;
    private final String password;
    private final String email;

    public SignupRequestDto(String username, String nickname, String password, String email){
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }
}