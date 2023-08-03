package com.project.market.controller;

import com.project.market.dto.BoardDTO;
import com.project.market.s3.S3Service;
import com.project.market.dto.UserDTO;
import com.project.market.security.UserPrincipal;
import com.project.market.service.BoardService;
import com.project.market.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final BoardService boardService;
    private final S3Service s3Service;
    @PostMapping("/join")
    public ResponseEntity register(@RequestPart("img") MultipartFile multipartFile,
                                   @RequestPart("user") @Valid UserDTO.Request req, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError error : bindingResult.getFieldErrors()){
                errorMap.put("valid_"+error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
        }
        log.info("controller req=>{}", req.getUserId());
        String imgUrl = null;
        if(multipartFile != null){
            imgUrl = s3Service.uploadImage(multipartFile);
        }
        log.info("controller imgUrl=>{}",imgUrl);
        String status = userService.register(req, imgUrl);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @GetMapping("/profile")
    public ResponseEntity profile(@AuthenticationPrincipal UserPrincipal loginUser) {
        Map<String, Object> res = new HashMap<>();
        UserDTO.Profile user = userService.viewUser(loginUser.getId());
        res.put("user", user);
        List<BoardDTO.Response> boardList = boardService.selectByUser(loginUser.getId());
        res.put("boardList", boardList);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
