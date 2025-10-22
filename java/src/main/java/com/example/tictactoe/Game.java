package com.example.tictactoe;

import java.util.Scanner;

public class Game {

    private static int readMove(Scanner sc, Board b) {
        while (true) {
            System.out.print("Ton coup (1-9): ");
            String line = sc.nextLine().trim();
            try {
                int v = Integer.parseInt(line);
                if (v >= 1 && v <= 9 && b.get(v-1) == Board.EMPTY) return v-1;
            } catch (NumberFormatException ignored) {}
            System.out.println("👉 Entrez un numéro libre entre 1 et 9.");
        }
    }

    private static void help() {
        System.out.println("Index des cases:");
        System.out.println(" 1 | 2 | 3");
        System.out.println("---+---+---");
        System.out.println(" 4 | 5 | 6");
        System.out.println("---+---+---");
        System.out.println(" 7 | 8 | 9");
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Board b = new Board();
        help();
        b.print();

        char turn = Board.HUMAN; // humain commence

        while (true) {
            if (turn == Board.HUMAN) {
                int mv = readMove(sc, b);
                b.set(mv, Board.HUMAN);
            } else {
                System.out.println("IA réfléchit...");
                int mv = MinimaxAI.bestMove(b);
                b.set(mv, Board.AI);
                System.out.printf("IA joue en %d%n", mv + 1);
            }

            b.print();

            if (b.isTerminal()) {
                int s = b.score();
                if (s == +1) System.out.println("💻 IA gagne !");
                else if (s == -1) System.out.println("🎉 Tu gagnes !");
                else System.out.println("🤝 Match nul.");
                break;
            }

            turn = (turn == Board.HUMAN) ? Board.AI : Board.HUMAN;
        }
        sc.close();
    }
}
