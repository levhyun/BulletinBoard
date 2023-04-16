package com.springboot.bulletinboard.repository;

import com.springboot.bulletinboard.entity.BulletinBoardEntity;
import com.springboot.bulletinboard.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByBulletinBoardEntityOrderByIdDesc(BulletinBoardEntity bulletinBoardEntity);
}
