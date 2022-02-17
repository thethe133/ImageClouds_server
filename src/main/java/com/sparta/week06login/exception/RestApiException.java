package com.sparta.week06login.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter

//에러 발생 시 http response 본문에 json으로 담아 보내줄 문구

public class RestApiException {
   private String errorMessage;
   private HttpStatus httpStatus;
}
