package Distributed;

import java.util.ArrayList;

public class Queen {

    private int[] board;
    public static int solutionNumber = 0;
    public static ArrayList<Integer> mat = new ArrayList<>();

    public Queen(int row) {
        board = new int[Distributed.boardSize];
        board[0] = row;
        solution(0);
    }

    public boolean isFree(int pos) {
        for (int i = 0; i < pos; i++) {
            if (board[i] == board[pos] ||
                    Math.abs(board[i] - board[pos]) == pos - i)
                return false;
        }
        return true;
    }

    public void solution(int startPos) {
        if (isFree(startPos)) {
            if (startPos == Distributed.boardSize - 1) {
                solutionNumber++;
                storeBoard(board);
            } else {
                for (int i = 0; i < Distributed.boardSize; i++) {
                    board[startPos + 1] = i;
                    solution(startPos + 1);
                }
            }
        }
    }

    public static void storeBoard(int[] board) {
        for (int elt: board) {
            mat.add(elt);
        }
    }
}