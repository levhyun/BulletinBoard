package com.springboot.bulletinboard.entity;

import com.springboot.bulletinboard.dto.CommentDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comments")
public class CommentEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String writer;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private BulletinBoardEntity bulletinBoardEntity;

    public static CommentEntity toSaveEntity(CommentDto commentDto, BulletinBoardEntity bulletinBoardEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setWriter(commentDto.getWriter());
        commentEntity.setContent(commentDto.getContent());
        commentEntity.setBulletinBoardEntity(bulletinBoardEntity);
        return commentEntity;
    }
}
