package com.project.market.board;

import com.project.market.board.dto.request.BoardWrite;
import com.project.market.board.dto.response.BoardResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://172.16.65.251:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    private HttpSession httpSession;

    @GetMapping("/viewAll")
    public ResponseEntity viewAll(){
        List<BoardResponse> boardList = boardService.selectAll();
        return ResponseEntity.status(HttpStatus.OK).body(boardList);
    }

    @PostMapping("/post")
    public ResponseEntity post(@RequestBody BoardWrite boardWrite){
        //SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        //User user = userService.findUser(sessionUser);
        log.info("boardWrite={}", boardWrite);
        String status = boardService.register(boardWrite);

        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        String status = boardService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody BoardWrite boardWrite){
        String status = boardService.update(id, boardWrite);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }


    @GetMapping("/view/{id}")
    public ResponseEntity view(@PathVariable("id") Long id){
        BoardResponse boardResponse = boardService.select(id);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponse);
    }

    @PostMapping("/like/{id}")
    public ResponseEntity like(@PathVariable("id") Long id){
        //SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        //User user = userService.findUser(sessionUser);
        String status = boardService.like(id);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }



}
