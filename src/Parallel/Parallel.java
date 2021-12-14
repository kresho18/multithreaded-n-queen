package Parallel;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Parallel extends Application{
    public static int[] array;
    static int m = 0;
    public static int boardSize;

    public static void main(String[] args) {
        boardSize = 8;
        //long t0 = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < boardSize; i++) {
            Runnable worker = new Queen(i);
            executor.execute(worker);
        }
        executor.shutdown();
        while(!executor.isTerminated()){

        }
        //long t1 = System.currentTimeMillis();
        //System.out.println("Vrijeme je " + (t1 - t0) + " ms");
        launch(args);
    }

    @Override
    public void start(Stage stage) {
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