package com.sparta.week06login.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class SignupResponseDto {
    private Long uid;
    private String username;
    private String nickname;
    //    private password
    private String email;
    private Boolean ok;

    public SignupResponseDto(Long uid, String username, String nickname, String email, Boolean ok){
        this.uid = uid;
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.ok = ok;
    }
}