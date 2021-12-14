package Distributed;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import mpi.*;

public class Distributed extends Application {
    public static int rank;
    public static int size;
    public static int boardSize;
    public static int[] array;
    static int m = 0;

    public static void main(String[] args) {
        MPI.Init(args);
        rank = MPI.COMM_WORLD.Rank();
        size = MPI.COMM_WORLD.Size();
        boardSize = 9;
        int[] position = new int[2];


        if (rank == 0) {
            position[1] = (boardSize/3) - 1;
            MPI.COMM_WORLD.Send(position, 0,2,MPI.INT,1,0);

            position[0] = (boardSize/3);
            position[1] = ((boardSize/3)*2) - 1;
            MPI.COMM_WORLD.Send(position, 0,2,MPI.INT,2,0);

            position[0] = (boardSize/3)*2;
            position[1] = boardSize - 1;
            MPI.COMM_WORLD.Send(position, 0,2,MPI.INT,3,0);

        } else {
            int[] buffer = new int[2];
            MPI.COMM_WORLD.Recv(buffer, 0, 2, MPI.INT, 0, 0);
            for (int i = buffer[0]; i <= buffer[1]; i++) {
                Queen q = new Queen(i);
            }
        }

        if (rank == 1)
            launch(args);

        MPI.Finalize();
        //System.out.println(rank + " " + Queen.solutionNumber);
    }

    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("N Queen problem");
        GridPane[] grid = new GridPane[Queen.solutionNumber];
        for (int i = 0; i < grid.length; i++) {
            int[] are = toSout(Queen.mat);
            grid[i] = new GridPane();
            Button[][] buttons = new Button[boardSize][boardSize];
            for (int j = 0; j < boardSize; j++) {
                for (int k = 0; k < boardSize; k++) {
                    buttons[j][k] = new Button();
                    buttons[j][k].setPrefSize(40,40);
                    grid[i].add(buttons[j][k],j,k);
                    if (k == are[j]) {
                        buttons[j][k].setStyle("-fx-background-color: #ff0000;");
                    } else {
                        if (j % 2 == 0) {
                            if (k % 2 == 0)
                                buttons[j][k].setStyle("-fx-background-color: #ffffff");
                            else
                                buttons[j][k].setStyle("-fx-background-color: #000000");
                        } else {
                            if (k % 2 != 0)
                                buttons[j][k].setStyle("-fx-background-color: #ffffff");
                            else
                                buttons[j][k].setStyle("-fx-background-color: #000000");
                        }
                    }
                }
            }
        }

        BorderPane[] layouts = new BorderPane[Queen.solutionNumber];
        VBox full = new VBox();
        for (int i = 0; i < layouts.length; i++) {
            layouts[i] = new BorderPane();
            layouts[i].setPadding(new Insets(60,60,60,250));
            layouts[i].setCenter(grid[i]);
            full.getChildren().addAll(layouts[i]);
        }
        ScrollPane sp = new ScrollPane();
        sp.setContent(full);
        sp.setStyle("-fx-background-color: #f76711");
        Scene scene = new Scene(sp,800,600);
        stage.setScene(scene);
        stage.show();
    }

    public static int[] toSout(ArrayList<Integer> matrix) {
        array = new int[boardSize];
        int temp = m + boardSize;
        while (m < matrix.size()) {
            for (int i = 0; i < array.length; i++) {
                if(temp == m) {
                    return array;
                }
                array[i] = matrix.get(m);
                m++;
            }
        }
        return array;
    }

}
