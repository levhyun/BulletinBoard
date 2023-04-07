package com.springboot.bulletinboard.repository;

import com.springboot.bulletinboard.entity.BulletinBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BulletinBoardRepository extends JpaRepository<BulletinBoardEntity, Long> {
}
