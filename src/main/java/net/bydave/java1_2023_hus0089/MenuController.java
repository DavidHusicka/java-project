package net.bydave.java1_2023_hus0089;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    private final Stage stage;
    MenuController(Stage stage){
        this.stage = stage;
    }

    @FXML
    void gameStart() {
        System.out.println("game start");
        FXMLLoader loader = new FXMLLoader(MenuController.class.getResource("game-view.fxml"));
        loader.setController(new GameController(stage));
        try {
            BorderPane root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            GameController controller = loader.getController();
            controller.startGame();
            scene.setOnKeyPressed(controller::notifyKeyPressed);
            scene.setOnKeyReleased(controller::notifyKeyPressed);
        }
        catch (IOException e) {}
    }
}
