package com.my.code_jam.y2019.round1.round1c.bacterial_tactics;

import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Solution2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            int R = in.nextInt();
            int C = in.nextInt();

            int[][] board = new int[R][C];
            for (int r = 0; r < R; r++) {
                String row = in.next();
                for (int c = 0; c < C; c++) {
                    board[r][c] = row.charAt(c) == '.'? 0: 1;
                }
            }
            System.out.print("Case #" + i + ": ");
            solve(board);
        }
    }

    private static void solve(int[][] board) {
        Set<Move> moves = calcPossibleMoves(board);
        int result = 0;
        //new HashSet<>(Arrays.asList(new Move(1, 3, false)))
        for (Move newMove : moves) {
            if (!isRadiationThere(board, newMove)) {
                if (!internalSolve(copy(board), newMove, substract(moves, newMove))){
                    //System.out.println(newMove);
                    result++;
                }
            }
        }
        System.out.println(result);
    }

    private static boolean internalSolve(int[][] board, Move move, Set<Move> moves) {
        clearMoves(board, moves, move);

        //next move
        for (Move newMove : moves) {
            if (!isRadiationThere(board, newMove)) {
                if (!internalSolve(copy(board), newMove, substract(moves, newMove))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int[][] copy(int[][] board) {
        int[][] result = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, result[i], 0, board[i].length);
        }
        return result;
    }

    private static Set<Move> calcPossibleMoves(int[][] board) {
        Set<Move> moves = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != 1) {
                    moves.add(new Move(i, j, false));
                    moves.add(new Move(i, j, true));
                }
            }
        }
        return moves;
    }

    private static boolean isRadiationThere(int[][] board, Move move) {
        int i = move.i;
        int j = move.j;
        boolean vertical = move.vertical;
        if (vertical) {
            for (int k = i; k >= 0; k--) {
                if (board[k][j] == 1) return true;
                if (board[k][j] == 2) return false;
            }
            for (int k = i; k < board.length; k++) {
                if (board[k][j] == 1) return true;
                if (board[k][j] == 2) return false;
            }
        } else {
            for (int k = j; k >= 0; k--) {
                if (board[i][k] == 1) return true;
                if (board[i][k] == 2) return false;
            }
            for (int k = j; k < board[0].length; k++) {
                if (board[i][k] == 1) return true;
                if (board[i][k] == 2) return false;
            }
        }
        return false;
    }

    private static void clearMoves(int[][] board, Set<Move> moves, Move move) {
        int i = move.i;
        int j = move.j;
        boolean vertical = move.vertical;
        board[move.i][move.j] = 2;
        if (vertical) {
            for (int k = i - 1; k >= 0; k--) {
                Move m1 = new Move(k, j, false);
                Move m2 = new Move(k, j, true);
                if (clearMoves(moves, board, m1, m2)) break;
            }
            for (int k = i + 1; k < board.length; k++) {
                Move m1 = new Move(k, j, false);
                Move m2 = new Move(k, j, true);
                if (clearMoves(moves, board, m1, m2)) break;
            }
        } else {
            for (int k = j - 1; k >= 0; k--) {
                Move m1 = new Move(i, k, false);
                Move m2 = new Move(i, k, true);
                if (clearMoves(moves, board, m1, m2)) break;
            }
            for (int k = j + 1; k < board[0].length; k++) {
                Move m1 = new Move(i, k, false);
                Move m2 = new Move(i, k, true);
                if (clearMoves(moves, board, m1, m2)) break;
            }
        }
    }

    private static boolean clearMoves(Set<Move> moves, int[][] board, Move m1, Move m2) {
        if (board[m1.i][m1.j] == 2) return true;
        moves.remove(m1);
        moves.remove(m2);
        board[m1.i][m1.j] = 2;
        return false;
    }

    private static Set<Move> substract(Set<Move> moves, Move move) {
        Set<Move> result = new HashSet<>(moves);
        result.remove(move);
        result.remove(new Move(move.i, move.j, !move.vertical));
        return result;
    }

    private static class Move {
        private int i;
        private int j;
        private boolean vertical;

        public Move(int i, int j, boolean vertical) {
            this.i = i;
            this.j = j;
            this.vertical = vertical;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Move move = (Move) o;
            return i == move.i &&
                    j == move.j &&
                    vertical == move.vertical;
        }

        @Override
        public int hashCode() {

            return Objects.hash(i, j, vertical);
        }

        @Override
        public String toString() {
            return "[" + i + " " + j + "], v = " + vertical;
        }
    }
}
