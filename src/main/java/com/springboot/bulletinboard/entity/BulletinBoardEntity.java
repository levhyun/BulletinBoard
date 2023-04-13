package com.springboot.bulletinboard.entity;

import com.springboot.bulletinboard.dto.BulletinBoardDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "posts")
public class BulletinBoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String writer;

    @Column
    private String pass;

    @Column
    private String title;

    @Column
    private String contents;

    @Column
    private int hits;

    public static BulletinBoardEntity toSaveEntity(BulletinBoardDto bulletinBoardDto) {
        BulletinBoardEntity bulletinBoardEntity = new BulletinBoardEntity();
        bulletinBoardEntity.setWriter(bulletinBoardDto.getWriter());
        bulletinBoardEntity.setPass(bulletinBoardDto.getPass());
        bulletinBoardEntity.setTitle(bulletinBoardDto.getTitle());
        bulletinBoardEntity.setContents(bulletinBoardDto.getContents());
        bulletinBoardEntity.setHits(0);
        return bulletinBoardEntity;
    }

    public static BulletinBoardEntity toUpdateEntity(BulletinBoardDto bulletinBoardDto) {
        BulletinBoardEntity bulletinBoardEntity = new BulletinBoardEntity();
        bulletinBoardEntity.setId(bulletinBoardDto.getId());
        bulletinBoardEntity.setWriter(bulletinBoardDto.getWriter());
        bulletinBoardEntity.setPass(bulletinBoardDto.getPass());
        bulletinBoardEntity.setTitle(bulletinBoardDto.getTitle());
        bulletinBoardEntity.setContents(bulletinBoardDto.getContents());
        bulletinBoardEntity.setHits(bulletinBoardDto.getHits());
        return bulletinBoardEntity;
    }
}
