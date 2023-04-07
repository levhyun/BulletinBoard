package com.springboot.bulletinboard.service;

import com.springboot.bulletinboard.dto.BulletinBoardDto;
import com.springboot.bulletinboard.entity.BulletinBoardEntity;
import com.springboot.bulletinboard.repository.BulletinBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
