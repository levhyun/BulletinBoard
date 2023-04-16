package com.springboot.bulletinboard.controller;

import com.springboot.bulletinboard.dto.CommentDto;
import com.springboot.bulletinboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/write")
    public ResponseEntity writeComment(@ModelAttribute CommentDto commentDto) {
        Long saveResult = commentService.write(commentDto);
        if (saveResult != null) {
            List<CommentDto> commentDtoList = commentService.findAll(commentDto.getBulletinBoardId());
            return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("해당 게시물이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }
    }
}
