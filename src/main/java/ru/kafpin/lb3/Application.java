package ru.kafpin.lb3;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;  

import java.io.IOException;

public class Application extends javafx.application.Application {
    private static Stage startStage;

    public static Stage getStartStage() {
        return startStage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        startStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("first-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Список студентов");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}