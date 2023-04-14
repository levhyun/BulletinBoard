package com.springboot.bulletinboard.service;

import com.springboot.bulletinboard.dto.BulletinBoardDto;
import com.springboot.bulletinboard.entity.BulletinBoardEntity;
import com.springboot.bulletinboard.entity.BulletinBoardFileEntity;
import com.springboot.bulletinboard.repository.BulletinBoardFileRepository;
import com.springboot.bulletinboard.repository.BulletinBoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BulletinBoardService {
    private final BulletinBoardRepository bulletinBoardRepository;
    private final BulletinBoardFileRepository bulletinBoardFileRepository;

    public void write(BulletinBoardDto bulletinBoardDto) throws IOException {
        if (bulletinBoardDto.getFile().isEmpty()) {
            BulletinBoardEntity bulletinBoardEntity = BulletinBoardEntity.toSaveEntity(bulletinBoardDto);
            bulletinBoardRepository.save(bulletinBoardEntity);
        } else {
            BulletinBoardEntity bulletinBoardEntity = BulletinBoardEntity.toSaveFileEntity(bulletinBoardDto);
            Long savedId = bulletinBoardRepository.save(bulletinBoardEntity).getId();
            BulletinBoardEntity board = bulletinBoardRepository.findById(savedId).get();
            for (MultipartFile file: bulletinBoardDto.getFile()) {
                String originalFilename = file.getOriginalFilename();
                String storedFileName = System.currentTimeMillis() + " " + originalFilename;
                String savePath = "C:\\springboot_img\\" + storedFileName;
                file.transferTo(new File(savePath));

                BulletinBoardFileEntity bulletinBoardFileEntity = BulletinBoardFileEntity.toBulletinBoardFileEntity(board, originalFilename, storedFileName);
                bulletinBoardFileRepository.save(bulletinBoardFileEntity);
            }
        }
    }

    @Transactional
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

    @Transactional
    public BulletinBoardDto findPost(Long id) {
        Optional<BulletinBoardEntity> optionalBulletinBoardEntity = bulletinBoardRepository.findById(id);
        if (optionalBulletinBoardEntity.isPresent()) {
            BulletinBoardEntity bulletinBoardEntity = optionalBulletinBoardEntity.get();
            return BulletinBoardDto.toBulletinBoardDto(bulletinBoardEntity);
        } else {
            return null;
        }
    }

    public BulletinBoardDto updatePostValue(BulletinBoardDto bulletinBoardDto) {
        BulletinBoardEntity bulletinBoardEntity = BulletinBoardEntity.toUpdateEntity(bulletinBoardDto);
        bulletinBoardRepository.save(bulletinBoardEntity);
        return findPost(bulletinBoardDto.getId());
    }

    public void delete(Long id) {
        bulletinBoardRepository.deleteById(id);
    }

    public Page<BulletinBoardDto> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3;
        Page<BulletinBoardEntity> bulletinBoardEntities = bulletinBoardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        return bulletinBoardEntities.map(bulletinBoard -> new BulletinBoardDto(bulletinBoard.getId(), bulletinBoard.getWriter(), bulletinBoard.getTitle(), bulletinBoard.getHits(), bulletinBoard.getCreatedTime()));
    }
}
