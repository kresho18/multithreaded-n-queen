package Sequential;
import java.util.ArrayList;
import java.util.Arrays;

public class Queen {
    public static int boardSize;
    private Integer[] board;
    public static ArrayList<Integer> mat = new ArrayList<>();
    public static int solutionNumber  = 0;
    public static int[] array;
    static int m = 0;

    public Queen(int boardSize) {
        Queen.boardSize = boardSize;
        this.board = new Integer[boardSize];
    }

    public boolean isFree(int pos) {
        for (int i = 0; i < pos; i++) {
            if (board[i].equals(board[pos]) ||
                    Math.abs(board[i] - board[pos]) == pos - i)
                return false;
        }
        return true;
    }

    public void solution(int startPos){
        if (isFree(startPos)) {
            if (startPos == boardSize - 1) {
                solutionNumber++;
                storeBoard();
            } else
                for (int i = 0; i < boardSize; i++) {
                    board[startPos + 1] = i;
                    solution(startPos + 1);
                }
        }
    }

    public void storeBoard() {
        mat.addAll(Arrays.asList(board));
    }

    public static int[] toSout(ArrayList<Integer> matrix) {
        array = new int[boardSize];
        int temp = m+boardSize;
        while (m < matrix.size()) {
            for (int i = 0; i < array.length; i++) {
                if (temp == m) {
                    return array;
                }
                array[i] = matrix.get(m);
                m++;
            }
        }
        return array;
    }

}