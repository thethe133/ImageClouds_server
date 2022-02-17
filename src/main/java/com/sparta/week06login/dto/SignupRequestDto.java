package com.sparta.week06login.dto;

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

    public SignupRequestDto(String login_id, String nickname, String password, String email){
        this.username = login_id;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }
}