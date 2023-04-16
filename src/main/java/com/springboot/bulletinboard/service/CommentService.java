package com.springboot.bulletinboard.service;

import com.springboot.bulletinboard.dto.CommentDto;
import com.springboot.bulletinboard.entity.BulletinBoardEntity;
import com.springboot.bulletinboard.entity.CommentEntity;
import com.springboot.bulletinboard.repository.BulletinBoardRepository;
import com.springboot.bulletinboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BulletinBoardRepository bulletinBoardRepository;

    public Long write(CommentDto commentDto) {
        Optional<BulletinBoardEntity> optionalBulletinBoardEntity = bulletinBoardRepository.findById(commentDto.getBulletinBoardId());
        if (optionalBulletinBoardEntity.isPresent()) {
            BulletinBoardEntity bulletinBoardEntity = optionalBulletinBoardEntity.get();
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDto, bulletinBoardEntity);
            return commentRepository.save(commentEntity).getId();
        } else {
            return null;
        }

    }

    public List<CommentDto> findAll(Long bulletinBoardId) {
        BulletinBoardEntity bulletinBoardEntity = bulletinBoardRepository.findById(bulletinBoardId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByBulletinBoardEntityOrderByIdDesc(bulletinBoardEntity);
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (CommentEntity commentEntity: commentEntityList) {
            CommentDto commentDto = CommentDto.toCommentDto(commentEntity, bulletinBoardId);
            commentDtoList.add(commentDto);
        }
        return commentDtoList;
    }
}
