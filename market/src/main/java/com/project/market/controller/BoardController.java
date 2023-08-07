package com.project.market.controller;

import com.project.market.domain.Board;
import com.project.market.dto.BoardDTO;
import com.project.market.exception.ImageUploadException;
import com.project.market.repository.BoardRepository;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    private final S3Service s3Service;

    @GetMapping("test")
    public ResponseEntity test(){
        Map<String, Object> map = new HashMap<>();
        map.put("user", "김채원");
        map.put("postImage","https://s3.ap-northeast-2.amazonaws.com/markeybucket/post/image/b118de25-bb26-413f-96af-1ab70afa2cb1.jpg");
        map.put("likes",3);
        map.put("timeStamp","2023-07-30T19:03:19.456967");
        map.put("chats", 3);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }
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
    public ResponseEntity like(@PathVariable("id") Long id, @AuthenticationPrincipal UserPrincipal loginUser){
        Map<String, Integer> map = new HashMap<>();
        // int(자료형, primitive type) => 산술연산 가능, null로 초기화 불가
        // Integer(래퍼 클래스, Wrapper class) => unboxing하지 않을 시 산술연산 불가능, null로 초기화 가능
        Integer likes = boardService.like(id, loginUser.getId());
        map.put("likes", likes);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }



}
