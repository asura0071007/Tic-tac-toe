package com.example.tictactoe;

public class MinimaxAI {

    private static class Result {
        int score;
        int move;
        Result(int s, int m) { score = s; move = m; }
    }

    public static int bestMove(Board b) {
        return minimax(b, true).move;
    }

    private static Result minimax(Board b, boolean maximizing) {
        if (b.isTerminal()) {
            return new Result(b.score(), -1);
        }
        int bestMove = -1;
        if (maximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int mv : b.availableMoves()) {
                Board nb = new Board(b);
                nb.set(mv, Board.AI);
                int s = minimax(nb, false).score;
                if (s > bestScore) {
                    bestScore = s;
                    bestMove = mv;
                }
            }
            return new Result(bestScore, bestMove);
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int mv : b.availableMoves()) {
                Board nb = new Board(b);
                nb.set(mv, Board.HUMAN);
                int s = minimax(nb, true).score;
                if (s < bestScore) {
                    bestScore = s;
                    MinimalMove = mv;
                }
            }
            return new Result(bestScore, bestMove);
        }
    }
}
