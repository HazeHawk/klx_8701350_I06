package sample;

import javafx.fxml.FXML;

public class Controller {

    @FXML
    private void clickSolve(){
        BoardController boardController = new BoardController();
        boardController.solve();
    }

}
