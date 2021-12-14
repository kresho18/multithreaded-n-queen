package Sequential;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Sequential extends Application {

    public static void main(String[] args) {
        //long t0 = System.currentTimeMillis();
        Queen q1 = new Queen(9);
        q1.solution(-1);
        //long t1 = System.currentTimeMillis();
        //System.out.println("Vrijeme je "+(t1-t0)+ " ms");
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("N Queen problem");
        GridPane[] grid = new GridPane[Queen.solutionNumber];
        for (int i = 0; i < grid.length; i++) {
            int[] are = Queen.toSout(Queen.mat);
            grid[i] = new GridPane();
            Button[][] buttons = new Button[Queen.boardSize][Queen.boardSize];
            for (int j = 0; j < Queen.boardSize; j++) {
                for (int k = 0; k < Queen.boardSize; k++) {
                    buttons[j][k] = new Button();
                    buttons[j][k].setPrefSize(40,40);
                    grid[i].add(buttons[j][k], j, k);
                    if (k == are[j]) {
                        buttons[j][k].setStyle("-fx-background-color: #ff0000");
                    } else {
                        if (j % 2 == 0) {
                            if (k % 2 == 0)
                                buttons[j][k].setStyle("-fx-background-color: #ffffff;");
                            else
                                buttons[j][k].setStyle("-fx-background-color: #000000;");
                        } else {
                            if (k % 2 != 0)
                                buttons[j][k].setStyle("-fx-background-color: #ffffff;");
                            else
                                buttons[j][k].setStyle("-fx-background-color: #000000;");

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
        sp.setStyle("-fx-background-color: #f00000");
        Scene scene = new Scene(sp,800,600);
        stage.setScene(scene);
        stage.show();
    }
}