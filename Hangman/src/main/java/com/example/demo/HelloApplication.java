package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        HelloController controller = fxmlLoader.getController();

        Scene scene = new Scene(root, 1000, 500);
        stage.setTitle("Wisielec");
        stage.setResizable(false);
        stage.setScene(scene);

        for (String label : controller.buttonsTable) {
            Button button = (Button) scene.lookup("#" + label);
            button.setOnAction(controller::ButtonClick);
        }

        Button ustawieniaButton = (Button) scene.lookup("#Ustawienia");
        ustawieniaButton.setOnAction(controller::SettingsButton);

        Button lvlButton = (Button) scene.lookup("#lvll");
        lvlButton.setOnAction(controller::lvlButton);

        Button DecButton = (Button) scene.lookup("#DecB");
        DecButton.setOnAction(controller::DecButton);

        stage.show();
    }
}