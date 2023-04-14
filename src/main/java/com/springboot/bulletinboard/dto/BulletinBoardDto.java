package com.springboot.bulletinboard.dto;

import com.springboot.bulletinboard.entity.BulletinBoardEntity;
import com.springboot.bulletinboard.entity.BulletinBoardFileEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private List<MultipartFile> file;
    private List<String> originalFileName;
    private List<String> storedFileName;
    private int fileAttached;

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
        if (bulletinBoardEntity.getFileAttached() == 0) {
            bulletinBoardDto.setFileAttached(bulletinBoardEntity.getFileAttached());
        } else {
            List<String> originalFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();
            bulletinBoardDto.setFileAttached(bulletinBoardEntity.getFileAttached());
            for (BulletinBoardFileEntity bulletinBoardFileEntity: bulletinBoardEntity.getBulletinBoardFileEntityList()) {
                originalFileNameList.add(bulletinBoardFileEntity.getOriginalFileName());
                storedFileNameList.add(bulletinBoardFileEntity.getStoredFileName());
            }
            bulletinBoardDto.setOriginalFileName(originalFileNameList);
            bulletinBoardDto.setStoredFileName(storedFileNameList);
        }
        return bulletinBoardDto;
    }
}
