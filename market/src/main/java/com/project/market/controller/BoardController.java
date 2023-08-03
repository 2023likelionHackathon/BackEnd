package com.project.market.controller;

import com.project.market.dto.BoardDTO;
import com.project.market.exception.ImageUploadException;
import com.project.market.security.UserPrincipal;
import com.project.market.service.BoardService;
import com.project.market.s3.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    private final S3Service s3Service;
    private final HttpSession httpSession;
    @PostMapping("/post")
    public ResponseEntity post(@RequestPart("board") BoardDTO.Request req,
                               @RequestPart("imgUrl")List<MultipartFile> multipartFiles,
                               @AuthenticationPrincipal UserPrincipal loginUser){
        if(multipartFiles == null){
            throw new ImageUploadException();
        }
        List<String> imgPaths = s3Service.uploadImages(multipartFiles);
        log.info("IMG 경로 : ", imgPaths);
        String status = boardService.post(req, imgPaths, loginUser.getId());

        return ResponseEntity.status(HttpStatus.OK).body(status);
    }
    @GetMapping("/viewAll")
    public ResponseEntity viewAll(){
        List<BoardDTO.Response> boardList = boardService.selectAll();
        return ResponseEntity.status(HttpStatus.OK).body(boardList);
    }
    @GetMapping("/view/{id}")
    public ResponseEntity view(@PathVariable("id") Long id){
        BoardDTO.Response boardResponse = boardService.select(id);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponse);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        String status = boardService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody BoardDTO.Request rep){
        String status = boardService.update(id, rep);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @PostMapping("/like/{id}")
    public ResponseEntity like(@PathVariable("id") Long id){
        //SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        //User user = userService.findUser(sessionUser);
        String status = boardService.like(id);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }



}
