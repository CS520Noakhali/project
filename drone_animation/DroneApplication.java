package com.example.drone;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main application to open scene to show drone flying.
 */
public class DroneApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DroneApplication.class.getResource("drone-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 1000);
        stage.setTitle("Drone animation view!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launch scene for drone flying.
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}