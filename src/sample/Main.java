package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    BoardController boardController;
    GridPane boardGrid;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        boardController = new BoardController();
        boardGrid = new GridPane();
        boardGrid.setLayoutX(75);
        boardGrid.setLayoutY(40);

        for(int y = 0; y < 10; y++)
        {
            for(int x = 0; x < 10; x++)
            {
                TextField tf = new TextField();
                tf.setPrefHeight(35);
                tf.setPrefWidth(35);
                tf.setAlignment(Pos.CENTER);
                tf.setEditable(false);
                tf.setText(tfValue(y,x));
                tf.setBackground(tfBackground(y,x));

                boardGrid.add(tf,x,y);
            }
        }

        ((Pane)root).getChildren().add(boardGrid);

        primaryStage.setTitle("Chocona");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    public String tfValue(int y, int x){
        if (boardController.getBoard().getBoardPanel(y,x).getGroup().isHasValue()) {
            return String.valueOf(boardController.getBoard().getBoardPanel(y, x).getGroup().getValue());
        }
        else return "N";
    }

    public Background tfBackground(int y, int x){
        if (boardController.getBoard().getBoardPanel(y, x).isBlack()) {
            return new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY));
        }
        else if (boardController.getBoard().getBoardPanel(y, x).isBlocked())
        {
            return new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY));
        }
        else {
            return new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
