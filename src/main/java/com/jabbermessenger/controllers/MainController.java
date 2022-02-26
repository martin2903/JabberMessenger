package com.jabbermessenger.controllers;

import com.jabbermessenger.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable  {


    static Model model = new Model();
    @FXML
    TextField T1;  //username textfield
    @FXML
    Label label;
    @FXML
    static Pane root;
    @FXML
    static FXMLLoader loader;

    public void signIn(ActionEvent event){

        if(model.isSignedIn(T1.getText())){
            label.setText("Sign in Successful!");
            //hide current frame
            ((Node)event.getSource()).getScene().getWindow().hide();

            try{
                createFrame();
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            label.setText("Username wrong");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sign in Problem");
            alert.setHeaderText("Something went wrong");
            alert.setContentText("Wrong username!");
            alert.showAndWait();
        }
    }
    public void registerUser(ActionEvent event){
        if(model.register(T1.getText())){
            ((Node)event.getSource()).getScene().getWindow().hide();
            try{
                createFrame();

            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sign in Problem");
            alert.setHeaderText("Something went wrong");
            alert.showAndWait();
        }

    }
    public void createFrame(){
        try {
            //create next frame
            Stage stage = new Stage();
            loader = new FXMLLoader();
            root = loader.load(getClass().getResource("user.fxml").openStream());
            UserController uc = (UserController) loader.getController(); /*create an instance of the UserController class so that we can
            use the getUser method. The controller class must be cast to the new controller instance. */
            uc.greetUser(T1.getText());
            //add timeline
            uc.getUserTimeline(model.getTimeline());
            //create and set the scene on the stage

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/com/bham/fsd/assignments/jabberserver/styles.css");
            stage.setTitle("Timeline");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
