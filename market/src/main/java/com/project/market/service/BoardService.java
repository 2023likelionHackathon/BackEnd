package com.project.market.service;

import com.project.market.domain.*;
import com.project.market.dto.BoardDTO;
import com.project.market.dto.BoardWithAvg;
import com.project.market.dto.ReplyDTO;
import com.project.market.exception.ImageUploadException;
import com.project.market.exception.NonExistentBoardException;
import com.project.market.exception.NonExistentStoreException;
import com.project.market.exception.NonExistentUserException;
import com.project.market.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final ReplyRepository replyRepository;

    public String post(BoardDTO.Request req, List<String> imgPaths, Long userId) {
        postBlankCheck(imgPaths);
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NonExistentUserException());
        Store store = storeRepository.findById(req.getStore_id())
                .orElseThrow(()-> new NonExistentStoreException());
        Board board = boardRepository.save(req.toEntity(user, store));
        List<String> imgList = new ArrayList<>();
        imgPaths.forEach(v->{
            BoardImg img = new BoardImg(v, board);
            boardImgRepository.save(img);
            imgList.add(img.getImageUrl());
        });
        user.updateReward();

        return "SUCCESS";
    }
    private void postBlankCheck(List<String> imgPaths){
        if(imgPaths == null || imgPaths.isEmpty()){
            throw new ImageUploadException();
        }
    }


    public String delete(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new NonExistentBoardException());
        boardRepository.delete(board);
        return "SUCCESS";
    }

    public String update(Long id, BoardDTO.Request req) {

        Board new_board = Board.builder()
                .id(id)
                .content(req.getContent())
                .build();
        boardRepository.save(new_board);
        return "SUCCESS";
    }

    public BoardDTO.Response select(Long boardId) {
//        Board board = boardRepository.findBoardWithReply(boardId)
//                .orElseThrow(()-> new NonExistentBoardException());
        Board board = boardRepository.findBoardById(boardId)
                .orElseThrow(()-> new NonExistentBoardException());
        return makeDTO(board);
    }

    public List<BoardDTO.Response> selectAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id","createdDate");
        List<Board> boardList = boardRepository.findAll(sort);
        List<BoardDTO.Response> result = new ArrayList<>();
        boardList.forEach(v->{
            result.add(select(v.getId()));
        });
        return result;
    }
    public BoardDTO.Response makeDTO(Board board){
        //List<ReplyDTO.Response> replylist = replyRepository.getReplyListByBoard(board.getId());

        List<String> imgUrlList = new ArrayList<>();
        board.getBoardImgList().forEach(v->{
            imgUrlList.add(v.getImageUrl());
        });
        return board.toDTO(imgUrlList);
    }

    public Map<String, Object> like(Long id, Long userId) {
        Board board = boardRepository.findById(id)
                .orElseThrow(()-> new NonExistentBoardException());
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NonExistentUserException());
        Map<String, Object> map = new HashMap<>();

        BoardLike boardLike = boardLikeRepository.findByBoardIdAndUserId(board.getId(), user.getId());
        if(boardLike !=  null) {
            board.deletelike();
            boardLikeRepository.delete(boardLike);
            map.put("isLiked",false);
        }
        else{
            boardLike = BoardLike.builder()
                    .board(board)
                    .user(user)
                    .build();
            boardLikeRepository.save(boardLike);
            board.addlike();
            map.put("isLiked", true);
        }
        map.put("likes", board.getLikes());
        return map;
    }

    public List<BoardDTO.Response> selectByUser(Long userId) {
        List<Board> boardList = boardRepository.findByUserId(userId);
        List<BoardDTO.Response> res = new ArrayList<>();
        boardList.forEach(v->{
            res.add(makeDTO(v));
        });
        return res;
    }

    public Map<String, Object> selectByStore(Long storeId) {
        Map<String, Object> map = new HashMap<>();
        List<BoardWithAvg> boardList = boardRepository.findBoardWithAvgByStoreId(storeId);
        log.info("board userid = {}", boardList.get(0).getBoard().getUser().getUserId());
        log.info("board reply = {}", boardList.get(0).getBoard().getReplyList().stream().count());
        log.info("board img = {}", boardList.get(0).getBoard().getBoardImgList().stream().count());
        List<BoardDTO.Response> board_res = new ArrayList<>();
        boardList.forEach(v->{
            board_res.add(makeDTO(v.getBoard()));
        });
        map.put("boardList", board_res);
        Double storeAvg = boardList.get(0).getScoreAvg();
        map.put("store_avg", storeAvg);
        return map;
    }
}
