package com.sparta.week06login.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class DuplicateCheckResponseDto {
    private String duplicateCheck;

    public DuplicateCheckResponseDto(String duplicateCheck){
        this.duplicateCheck = duplicateCheck;
    }
}