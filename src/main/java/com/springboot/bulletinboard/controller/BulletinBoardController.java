package com.springboot.bulletinboard.controller;

import com.springboot.bulletinboard.dto.BulletinBoardDto;
import com.springboot.bulletinboard.dto.CommentDto;
import com.springboot.bulletinboard.service.BulletinBoardService;
import com.springboot.bulletinboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bulletin-board")
public class BulletinBoardController {
    private final BulletinBoardService bulletinBoardService;
    private final CommentService commentService;

    @GetMapping("/write")
    public String writeFrom() {
        return "write";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute BulletinBoardDto bulletinBoardDto) throws IOException {
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

    @GetMapping("/{id}")
    public String post(@PathVariable Long id, Model model, @PageableDefault(page = 1) Pageable pageable) {
        bulletinBoardService.updateHits(id);
        BulletinBoardDto bulletinBoardDto = bulletinBoardService.findPost(id);
        List<CommentDto> commentDtoList = commentService.findAll(id);
        model.addAttribute("commentList", commentDtoList);
        model.addAttribute("bulletinBoard", bulletinBoardDto);
        model.addAttribute("page", pageable.getPageNumber());
        return "detail";
    }

    @GetMapping("/update/{id}")
    public String updatePostInput(@PathVariable Long id, Model model) {
        BulletinBoardDto bulletinBoardDto = bulletinBoardService.findPost(id);
        model.addAttribute("bulletinBoardUpdate", bulletinBoardDto);
        return "update";
    }

    @PostMapping("/update")
    public String updatePosts(@ModelAttribute BulletinBoardDto bulletinBoardDto, Model model) {
        BulletinBoardDto bulletinBoard = bulletinBoardService.updatePostValue(bulletinBoardDto);
        model.addAttribute("bulletinBoard", bulletinBoard);
        return "detail";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        bulletinBoardService.delete(id);
        return "redirect:/bulletin-board/";
    }

    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<BulletinBoardDto> bulletinBoardList = bulletinBoardService.paging(pageable);
        int pageButtonLimit = 3;
        int startPageNumber = (((int)(Math.ceil((double)pageable.getPageNumber() / pageButtonLimit))) - 1) * pageButtonLimit + 1;
        int endPageNumber = Math.min((startPageNumber + pageButtonLimit - 1), bulletinBoardList.getTotalPages());
        String nowPageNumber = "PostListPaging - " + pageable.getPageNumber() + "page";
        model.addAttribute("bulletinBoardList",bulletinBoardList);
        model.addAttribute("startPageNumber",startPageNumber);
        model.addAttribute("endPageNumber",endPageNumber);
        model.addAttribute("nowPageNumber",nowPageNumber);
        return "paging";
    }
}
