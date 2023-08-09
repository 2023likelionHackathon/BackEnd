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
                                   @RequestPart("user") @Valid UserDTO.Request req){
        String imgUrl = null;
        if(multipartFile != null){
            imgUrl = s3Service.uploadImage(multipartFile);
        }
        log.info("controller imgUrl=>{}",imgUrl);
        String status = userService.register(req, imgUrl);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @GetMapping("/userId/{userId}/exists")
    public ResponseEntity checkIdDuplicate(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.checkUserIdDuplicaton(userId));
    }
    @GetMapping("/nickname/{nickname}/exists")
    public ResponseEntity checkNicknameDuplicate(@PathVariable String nickname){
        return ResponseEntity.status(HttpStatus.OK).body(userService.checkNicknameDuplicaton(nickname));
    }
    @GetMapping("/email/{email}/exists")
    public ResponseEntity checkEmailDuplicate(@PathVariable String email){
        return ResponseEntity.status(HttpStatus.OK).body(userService.checkEmailDuplicaton(email));
    }

    @GetMapping("/profile")
    public ResponseEntity profileByLoginuser(@AuthenticationPrincipal UserPrincipal loginUser) {
        Map<String, Object> res = new HashMap<>();
        UserDTO.Profile user = userService.viewUser(loginUser.getId());
        res.put("user", user);
        List<BoardDTO.Response> boardList = boardService.selectByUser(loginUser.getId());
        res.put("boardList", boardList);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    @GetMapping("/profile/{id}")
    public ResponseEntity profile(@PathVariable("id") Long id) {
        Map<String, Object> res = new HashMap<>();
        UserDTO.Profile user = userService.viewUser(id);
        res.put("user", user);
        List<BoardDTO.Response> boardList = boardService.selectByUser(id);
        res.put("boardList", boardList);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
