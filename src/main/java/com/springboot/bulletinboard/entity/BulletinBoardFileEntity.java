package com.springboot.bulletinboard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "files")
public class BulletinBoardFileEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private BulletinBoardEntity bulletinBoardEntity;

    public static BulletinBoardFileEntity toBulletinBoardFileEntity(BulletinBoardEntity bulletinBoardEntity, String originalFileName, String storedFileName) {
        BulletinBoardFileEntity bulletinBoardFileEntity = new BulletinBoardFileEntity();
        bulletinBoardFileEntity.setOriginalFileName(originalFileName);
        bulletinBoardFileEntity.setStoredFileName(storedFileName);
        bulletinBoardFileEntity.setBulletinBoardEntity(bulletinBoardEntity);
        return bulletinBoardFileEntity;
    }
}
