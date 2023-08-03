package com.project.market.service;

import com.project.market.domain.*;
import com.project.market.dto.BoardDTO;
import com.project.market.dto.ReplyDTO;
import com.project.market.exception.ImageUploadException;
import com.project.market.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final BoardImgRepository boardImgRepository;
    private final StoreRepository storeRepository;

    public String post(BoardDTO.Request req, List<String> imgPaths, Long userId) {
        postBlankCheck(imgPaths);
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("존재하지 않는 사용자입니다."));
        Store store = storeRepository.findById(req.getStore_id())
                .orElseThrow(()-> new RuntimeException("존재하지 않는 점포입니다."));
        Board board = boardRepository.save(req.toEntity(user, store));
        List<String> imgList = new ArrayList<>();
        imgPaths.forEach(v->{
            BoardImg img = new BoardImg(v, board);
            boardImgRepository.save(img);
            imgList.add(img.getImageUrl());
        });

        return "SUCCESS";
    }
    private void postBlankCheck(List<String> imgPaths){
        if(imgPaths == null || imgPaths.isEmpty()){
            throw new ImageUploadException();
        }
    }


    public String delete(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 글입니다."));
        boardRepository.delete(board);
        return "SUCCESS";
    }

    public String update(Long id, BoardDTO.Request req) {

        Board new_board = Board.builder()
                .id(id)
                .title(req.getTitle())
                .content(req.getContent())
                .build();
        boardRepository.save(new_board);
        return "SUCCESS";
    }

    public BoardDTO.Response select(Long boardId) {
        Board board = boardRepository.findBoardWithReply(boardId);
        log.info("board", board.getTitle());
        log.info("reply", board.getReplyList());
        List<ReplyDTO.Response> replylist = new ArrayList<>();
        if(board.getReplyList()!=null){
            board.getReplyList().forEach(v->
                    replylist.add(new ReplyDTO.Response(v)));
        }

        List<BoardImg> imgList = boardImgRepository.findAllByBoardId(boardId);
        List<String> imgUrlList = new ArrayList<>();
        imgList.forEach(v->{
            imgUrlList.add(v.getImageUrl());
        });
        return board.toDTO(replylist, imgUrlList);
    }

    public List<BoardDTO.Response> selectAll() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardDTO.Response> result = new ArrayList<>();
        boardList.forEach(v->{
            result.add(select(v.getId()));
        });
        return result;
    }

    public String like(Long id, Long userId) {
        Board board = boardRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("존재하지 않는 게시글입니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("존재하지 않는 사용자입니다."));
        BoardLike boardLike = boardLikeRepository.findByBoardIdAndUserId(board.getId(), 2L);
        if(boardLike !=  null) {
            board.deletelike();
            boardLikeRepository.delete(boardLike);
        }
        else{
            boardLike = BoardLike.builder()
                    .board(board)
                    .user(user)
                    .build();
            boardLikeRepository.save(boardLike);
            board.addlike();
        }

        return "SUCCESS";
    }

    public List<BoardDTO.Response> selectByUser(Long userId) {
        List<Board> boardList = boardRepository.findByUserId(userId);
        List<BoardDTO.Response> res = new ArrayList<>();
        boardList.forEach(v->{
            res.add(select(v.getId()));
        });
        return res;
    }
}
