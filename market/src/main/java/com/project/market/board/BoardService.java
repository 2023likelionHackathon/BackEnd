package com.project.market.board;

import com.project.market.board.dto.ReplyDTO;
import com.project.market.board.dto.request.BoardWrite;
import com.project.market.board.dto.response.BoardResponse;
import com.project.market.user.User;
import com.project.market.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
@Service
public class BoardService {

    private BoardRepository boardRepository;
    private UserRepository userRepository;
    private BoardLikeRepository boardLikeRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, UserRepository userRepository, BoardLikeRepository boardLikeRepository){
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.boardLikeRepository = boardLikeRepository;
    }
    public String register(BoardWrite boardWrite) {
        User user = userRepository.findById(2L)
                .orElseThrow(()-> new RuntimeException("존재하지 않는 사용자입니다."));

        Board board = Board.builder()
                .title(boardWrite.getTitle())
                .content(boardWrite.getContent())
                .user(user)
                .writer(boardWrite.getWriter())
                .picture(null)
                .replyList(null)
                .build();
        boardRepository.save(board);

        return "SUCCESS";
    }


    public String delete(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 글입니다."));
        boardRepository.delete(board);
        return "SUCCESS";
    }

    public String update(Long id, BoardWrite boardWrite) {

        Board new_board = Board.builder()
                .id(id)
                .title(boardWrite.getTitle())
                .content(boardWrite.getContent())
                .build();
        boardRepository.save(new_board);
        return "SUCCESS";
    }

    public BoardResponse select(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("존재하지 않는 글입니다."));
        List<ReplyDTO.Response> replylist = new ArrayList<>();
        board.getReplyList().forEach(v->
                replylist.add(new ReplyDTO.Response(v)));
        BoardResponse boardResponse = BoardResponse.builder()
                .boardId(board.getId())
                .userId(board.getUser().getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .likes(board.getLikes())
                .createdDate(String.valueOf(board.getCreatedDate()))
                .replyList(replylist)
                .build();
        return boardResponse;
    }

    public List<BoardResponse> selectAll() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardResponse> result = new ArrayList<>();
        boardList.forEach(v->{
            result.add(select(v.getId()));
        });
        return result;
    }

    public String like(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("존재하지 않는 게시글입니다."));
        User user = userRepository.findById(2L)
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
}
