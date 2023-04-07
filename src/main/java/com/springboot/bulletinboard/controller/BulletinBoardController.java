package com.springboot.bulletinboard.controller;

import com.springboot.bulletinboard.dto.BulletinBoardDto;
import com.springboot.bulletinboard.service.BulletinBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bulletin-board")
public class BulletinBoardController {
    private final BulletinBoardService bulletinBoardService;
    @GetMapping("/write")
    public String writeFrom() {
        return "write";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute BulletinBoardDto bulletinBoardDto) {
        System.out.println("bulletinBoardDto = " + bulletinBoardDto);
        bulletinBoardService.write(bulletinBoardDto);
        return "index";
    }

    @GetMapping("/")
    public String postList(Model model) {
        List<BulletinBoardDto> bulletinBoardDtoList = bulletinBoardService.findAllPosts();
        model.addAttribute("bulletinBoardList", bulletinBoardDtoList);
        return "list";
    }
}
