package com.my.code_jam.y2019.round1.round1c.bacterial_tactics;

import java.util.*;

public class Solution {

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
        List<Value> movesGroup = groupMoves(board, moves);
        int result = 0;
        for (Value newMove : movesGroup) {
            if (!internalSolve(copy(board), newMove.move, substract(moves, newMove.move))){
                //System.out.println(newMove);
                result += newMove.count;
            }
        }
        //System.out.println(result + " " + count);
        System.out.println(result + " " + count);
    }

    private static List<Value> groupMoves(int[][] board, Set<Move> moves) {
        Value[] verticalMoves = new Value[board[0].length];
        Value[] horizontalMoves = new Value[board.length];

        for (Move move : moves) {
            if (!isRadiationThere(board, move)) {
                if (move.vertical) {
                    if (verticalMoves[move.j] == null) {
                        verticalMoves[move.j] = new Value(move, 0);
                    }
                    verticalMoves[move.j].count++;
                } else {
                    if (horizontalMoves[move.i] == null) {
                        horizontalMoves[move.i] = new Value(move, 0);
                    }
                    horizontalMoves[move.i].count++;
                }
            }
        }

        List<Value> result = new ArrayList<>();

        for (int i = 0; i < verticalMoves.length; i++) {
            if (verticalMoves[i] != null) {
                result.add(verticalMoves[i]);
            }
        }
        for (int i = 0; i < horizontalMoves.length; i++) {
            if (horizontalMoves[i] != null) {
                result.add(horizontalMoves[i]);
            }
        }
        return result;
    }

    private static int count;

    private static boolean internalSolve(int[][] board, Move move, Set<Move> moves) {
        count++;
        clearMoves(board, moves, move);

        List<Value> movesGroup = groupMoves(board, moves);
        //next move
        for (Value newMove : movesGroup) {
            if (!internalSolve(copy(board), newMove.move, substract(moves, newMove.move))) {
                return true;
            }
        }
        return false;
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
                if (board[k][j] == 2) break;
            }
            for (int k = i; k < board.length; k++) {
                if (board[k][j] == 1) return true;
                if (board[k][j] == 2) break;
            }
        } else {
            for (int k = j; k >= 0; k--) {
                if (board[i][k] == 1) return true;
                if (board[i][k] == 2) break;
            }
            for (int k = j; k < board[0].length; k++) {
                if (board[i][k] == 1) return true;
                if (board[i][k] == 2) break;
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

    private static int[][] copy(int[][] board) {
        int[][] result = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, result[i], 0, board[i].length);
        }
        return result;
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

    private static class Value {
        private Move move;
        private Integer count;

        public Value(Move move, Integer count) {
            this.move = move;
            this.count = count;
        }
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
