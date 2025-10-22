package com.example.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public static final char EMPTY = ' ';
    public static final char HUMAN = 'X';
    public static final char AI = 'O';

    private final char[] cells = new char[9];

    public Board() {
        for (int i = 0; i < 9; i++) cells[i] = EMPTY;
    }

    public Board(Board other) {
        System.arraycopy(other.cells, 0, this.cells, 0, 9);
    }

    public List<Integer> availableMoves() {
        List<Integer> m = new ArrayList<>();
        for (int i = 0; i < 9; i++) if (cells[i] == EMPTY) m.add(i);
        return m;
    }

    public void set(int idx, char player) {
        cells[idx] = player;
    }

    public char get(int idx) {
        return cells[idx];
    }

    public char winner() {
        int[][] lines = {
                {0,1,2},{3,4,5},{6,7,8},
                {0,3,6},{1,4,7},{2,5,8},
                {0,4,8},{2,4,6}
        };
        for (int[] ln : lines) {
            char a = cells[ln[0]], b = cells[ln[1]], c = cells[ln[2]];
            if (a != EMPTY && a == b && b == c) return a;
        }
        return EMPTY;
    }

    public boolean isFull() {
        for (char c : cells) if (c == EMPTY) return false;
        return true;
    }

    public boolean isTerminal() {
        return winner() != EMPTY || isFull();
    }

    public int score() {
        char w = winner();
        if (w == AI) return +1;
        if (w == HUMAN) return -1;
        return 0;
    }

    public void print() {
        System.out.println();
        for (int r = 0; r < 3; r++) {
            System.out.printf(" %c | %c | %c%n", cells[3*r], cells[3*r+1], cells[3*r+2]);
            if (r < 2) System.out.println("---+---+---");
        }
        System.out.println();
    }
}
