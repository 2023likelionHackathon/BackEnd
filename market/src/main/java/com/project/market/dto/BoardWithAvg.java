package com.project.market.dto;

import com.project.market.domain.Board;
import lombok.Data;

@Data
public class BoardWithAvg {
    private Board board;
    private Double scoreAvg;
    public BoardWithAvg(Board board, Double scoreAvg){
        this.board = board;
        this.scoreAvg = scoreAvg;
    }
}
