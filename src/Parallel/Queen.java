package Parallel;

import java.util.ArrayList;

public class Queen implements Runnable {

    public static ArrayList<Integer> mat = new ArrayList<>();
    private int row;
    private int[] board;
    public static int solutionNumber = 0;

    @Override
    public void run() {
        board[0] = row;
        solution(0);
    }

    public Queen(int row) {
        this.row = row;
        board = new int[Parallel.boardSize];
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
            if (startPos == Parallel.boardSize - 1) {
                solutionNumber++;
                storeBoard();
            } else {
                for (int i = 0; i < Parallel.boardSize; i++) {
                    board[startPos + 1] = i;
                    solution(startPos + 1);
                }
            }
        }
    }

    public void storeBoard() {
        for (int elt: board) {
            mat.add(elt);
        }

    }
}