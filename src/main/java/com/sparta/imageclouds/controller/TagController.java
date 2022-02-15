package com.sparta.imageclouds.controller;

import com.sparta.imageclouds.dto.TagSearchResponseDto;
import com.sparta.imageclouds.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    //태그 검색
    @GetMapping("/pictures/{keyword}")
    public ResponseEntity<List<TagSearchResponseDto>> searchTag(@PathVariable String keyword){
        List<TagSearchResponseDto> tagSearchResponseDtoList = tagService.searchTag(keyword);
        return ResponseEntity.ok(tagSearchResponseDtoList);
    }

}

