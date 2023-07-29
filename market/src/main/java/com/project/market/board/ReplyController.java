package com.project.market.board;

import com.project.market.board.dto.ReplyDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "http://172.16.65.251:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/reply")
public class ReplyController {
    private ReplyService replyService;
    @PostMapping("/post")
    public ResponseEntity post(@RequestBody ReplyDTO.Request request){
        //SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        //User user = userService.findUser(sessionUser);
        String status = replyService.post(request);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        String status = replyService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

}
