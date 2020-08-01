package com.hines.james;

public class PaperRockScissorsGame {
    public ThrowResult throwing(Player playerOne, Player playerTwo) {
        String P1_WINS = "player one wins!";
        String P2_WINS = "player two wins!";

        String PAPER_BEATS_ROCK = " paper beats rock";
        String SCISSORS_BEATS_PAPER = " scissors beats paper";
        String ROCK_BEATS_SCISSORS = " rock beats scissors";

        ThrowResult throwResult = new ThrowResult(playerOne.getShape(), playerTwo.getShape());

        if(playerOne.getShape() == Shape.PAPER && playerTwo.getShape() == Shape.ROCK) {
            throwResult.setOutcome(P1_WINS + PAPER_BEATS_ROCK);
            throwResult.setWinner(playerOne);

            return throwResult;
        }

        if(playerOne.getShape() == Shape.ROCK && playerTwo.getShape() == Shape.SCISSORS) {
            throwResult.setOutcome(P1_WINS + ROCK_BEATS_SCISSORS);
            throwResult.setWinner(playerOne);

            return throwResult;
        }

        if(playerOne.getShape() == Shape.SCISSORS && playerTwo.getShape() == Shape.PAPER) {
            throwResult.setOutcome(P1_WINS + SCISSORS_BEATS_PAPER);
            throwResult.setWinner(playerOne);

            return throwResult;
        }

        if(playerTwo.getShape() == Shape.PAPER && playerOne.getShape() == Shape.ROCK) {
            throwResult.setOutcome(P2_WINS + PAPER_BEATS_ROCK);
            throwResult.setWinner(playerTwo);

            return throwResult;
        }

        if(playerTwo.getShape() == Shape.ROCK && playerOne.getShape() == Shape.SCISSORS) {
            throwResult.setOutcome(P2_WINS + ROCK_BEATS_SCISSORS);
            throwResult.setWinner(playerTwo);

            return throwResult;
        }

        if(playerTwo.getShape() == Shape.SCISSORS && playerOne.getShape() == Shape.PAPER) {
            throwResult.setOutcome(P2_WINS + SCISSORS_BEATS_PAPER);
            throwResult.setWinner(playerTwo);

            return throwResult;
        }

        throwResult.setOutcome("it's a draw");

        return throwResult;
    }
}
