package com.springboot.bulletinboard.repository;

import com.springboot.bulletinboard.entity.BulletinBoardFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BulletinBoardFileRepository extends JpaRepository<BulletinBoardFileEntity, Long> {

}
