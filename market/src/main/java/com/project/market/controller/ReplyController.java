package com.project.market.controller;

import com.project.market.dto.ReplyDTO;
import com.project.market.security.UserPrincipal;
import com.project.market.service.ReplyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://172.16.65.251:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/reply")
public class ReplyController {
    private ReplyService replyService;
    @PostMapping("/post")
    public ResponseEntity post(@RequestBody ReplyDTO.Request request, @AuthenticationPrincipal UserPrincipal loginUser){
        log.info("loginUser id = {}", loginUser.getId());
        List<ReplyDTO.Response> replyList = replyService.post(request, loginUser.getId());
        return ResponseEntity.status(HttpStatus.OK).body(replyList);
    }
    @GetMapping("/view/{boardId}")
    public ResponseEntity view(@PathVariable("boardId") Long boardId){
        List<ReplyDTO.Response> replyList = replyService.view(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(replyList);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id, @AuthenticationPrincipal UserPrincipal loginUser){
        List<ReplyDTO.Response> replyList = replyService.delete(id, loginUser.getId());
        return ResponseEntity.status(HttpStatus.OK).body(replyList);
    }

}
