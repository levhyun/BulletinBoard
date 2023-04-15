package com.springboot.bulletinboard.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentDto {
    private Long id;
    private String writer;
    private String content;
    private String bulletinBoardId;
    private LocalDateTime commentCreatedTime;
}
