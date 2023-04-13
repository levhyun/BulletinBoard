package com.springboot.bulletinboard.dto;

import com.springboot.bulletinboard.entity.BulletinBoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BulletinBoardDto {
    private Long id;
    private String writer;
    private String pass;
    private String title;
    private String contents;
    private int hits;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public BulletinBoardDto(Long id, String writer, String title, int hits, LocalDateTime createdTime) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.hits = hits;
        this.createdTime = createdTime;
    }

    public static BulletinBoardDto toBulletinBoardDto(BulletinBoardEntity bulletinBoardEntity) {
        BulletinBoardDto bulletinBoardDto = new BulletinBoardDto();
        bulletinBoardDto.setId(bulletinBoardEntity.getId());
        bulletinBoardDto.setWriter(bulletinBoardEntity.getWriter());
        bulletinBoardDto.setPass(bulletinBoardEntity.getPass());
        bulletinBoardDto.setTitle(bulletinBoardEntity.getTitle());
        bulletinBoardDto.setContents(bulletinBoardEntity.getContents());
        bulletinBoardDto.setHits(bulletinBoardEntity.getHits());
        bulletinBoardDto.setCreatedTime(bulletinBoardEntity.getCreatedTime());
        bulletinBoardDto.setUpdatedTime(bulletinBoardEntity.getUpdatedTime());
        return bulletinBoardDto;
    }
}
