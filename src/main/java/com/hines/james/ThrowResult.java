package com.hines.james;

import lombok.Data;

@Data
public class ThrowResult {
    public ThrowResult(Shape playerOneShape, Shape playerTwoShape) {
        this.playerOneShape = playerOneShape;
        this.playerTwoShape = playerTwoShape;
    }

    private Shape playerOneShape;
    private Shape playerTwoShape;
    private Player winner;
    private String outcome;
}
