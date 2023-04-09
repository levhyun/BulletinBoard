package com.springboot.bulletinboard.service;

import com.springboot.bulletinboard.dto.BulletinBoardDto;
import com.springboot.bulletinboard.entity.BulletinBoardEntity;
import com.springboot.bulletinboard.repository.BulletinBoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BulletinBoardService {
    private final BulletinBoardRepository bulletinBoardRepository;
    public void write(BulletinBoardDto bulletinBoardDto) {
        BulletinBoardEntity bulletinBoardEntity = BulletinBoardEntity.toSaveEntity(bulletinBoardDto);
        bulletinBoardRepository.save(bulletinBoardEntity);
    }

    public List<BulletinBoardDto> findAllPosts() {
        List<BulletinBoardEntity> bulletinBoardEntityList = bulletinBoardRepository.findAll();
        List<BulletinBoardDto> bulletinBoardDtoList = new ArrayList<>();
        for (BulletinBoardEntity bulletinBoardEntity: bulletinBoardEntityList) {
            bulletinBoardDtoList.add(BulletinBoardDto.toBulletinBoardDto(bulletinBoardEntity));
        }
        return bulletinBoardDtoList;
    }

    @Transactional
    public void updateHits(Long id) {
        bulletinBoardRepository.updateHits(id);
    }

    public BulletinBoardDto findPost(Long id) {
        Optional<BulletinBoardEntity> optionalBulletinBoardEntity = bulletinBoardRepository.findById(id);
        if (optionalBulletinBoardEntity.isPresent()) {
            BulletinBoardEntity bulletinBoardEntity = optionalBulletinBoardEntity.get();
            return BulletinBoardDto.toBulletinBoardDto(bulletinBoardEntity);
        } else {
            return null;
        }
    }
}
