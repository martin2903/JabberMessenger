package com.jabbermessenger.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;



public class FollowUserController {
    @FXML
    private Label userToFollow;
    boolean hasFollowed=true;
    @FXML
    private Button followButton;
    @FXML
    public void setUserToFollow(String user){
        userToFollow.setText(user);
    }

    @FXML
    public void followUser(ActionEvent event){
        while(hasFollowed){
            MainController.model.sendFollowRequest(userToFollow.getText());
            followButton.setText("");
            userToFollow.setText("");
            hasFollowed=false;
        }
    }
}

