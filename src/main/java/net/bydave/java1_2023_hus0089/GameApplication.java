package net.bydave.java1_2023_hus0089;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameApplication extends Application {

    private GameController controller;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("game-view.fxml"));
        fxmlLoader.setController(new GameController());
        BorderPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Game");
        stage.setScene(scene);
        stage.show();

        controller = fxmlLoader.getController();
        controller.startGame();
        scene.setOnKeyPressed((ev) -> {controller.notifyKeyPressed(ev);});
        scene.setOnKeyReleased((ev) -> {controller.notifyKeyPressed(ev);});
    }

    public static void main(String[] args) {
        launch();
    }
}