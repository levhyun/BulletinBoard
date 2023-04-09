package com.springboot.bulletinboard.repository;

import com.springboot.bulletinboard.entity.BulletinBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BulletinBoardRepository extends JpaRepository<BulletinBoardEntity, Long> {
    @Modifying
    @Query(value = "update BulletinBoardEntity b set b.hits = b.hits + 1 where b.id = :id")
    void updateHits(@Param("id") Long id);
}
