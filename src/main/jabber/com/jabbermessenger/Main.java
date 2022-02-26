package com.jabbermessenger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println(getClass().getResource("views/user.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("views/signIn.fxml"));
        primaryStage.setTitle("Sign In/Register");
        Scene scene = new Scene(root,300,275);
        scene.getStylesheets().add("../../resources/styles/styles.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

