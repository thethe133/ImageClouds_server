package com.sparta.week06login.controller;

import com.sparta.week06login.dto.ArticleResponseDto;
import com.sparta.week06login.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    //태그 검색
    @GetMapping("/pictures/{keyword}")
    public ResponseEntity<List<ArticleResponseDto>> searchTag(@PathVariable String keyword) throws UnsupportedEncodingException {
        keyword = URLDecoder.decode(keyword, "UTF-8");
        List<ArticleResponseDto> tagSearchResponseDtoList = tagService.searchTag(keyword);
        return ResponseEntity.ok(tagSearchResponseDtoList);
    }

}
