package com.springboot.bulletinboard.dto;

import com.springboot.bulletinboard.entity.CommentEntity;
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
    private Long bulletinBoardId;
    private LocalDateTime commentCreatedTime;

    public static CommentDto toCommentDto(CommentEntity commentEntity, Long bulletinBoardId) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(commentEntity.getId());
        commentDto.setWriter(commentEntity.getWriter());
        commentDto.setContent(commentEntity.getContent());
        commentDto.setCommentCreatedTime(commentEntity.getCreatedTime());
        commentDto.setBulletinBoardId(bulletinBoardId);
        return commentDto;
    }
}
