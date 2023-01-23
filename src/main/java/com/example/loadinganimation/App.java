package com.example.loadinganimation;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("LoadingAnimation");
        var loadingNode = new LoadingNode();
        var label = new Label("I'm bored");
        label.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        var root = new VBox(10, loadingNode,label);
        root.setAlignment(Pos.CENTER);
        var scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.show();

        loadingNode.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
