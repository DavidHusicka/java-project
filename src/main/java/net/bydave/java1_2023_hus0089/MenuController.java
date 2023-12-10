package net.bydave.java1_2023_hus0089;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuController {
    private final Stage stage;

    @FXML
    private ListView<String> scoresList;

    private List<String> scores;

    MenuController(Stage stage){
        this.stage = stage;
    }

    void load() {
        ObservableList<String> scoreListO = FXCollections.observableArrayList(new ArrayList<>());
        scores = scoreListO;
        scoresList.setItems(scoreListO);
        scores.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("scores.txt"))) {
            String line;
            while (null != (line = br.readLine())) {
                if (line.isBlank()) {
                    continue;
                }

                scores.add(line);
            }
        } catch (IOException e) {}
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
