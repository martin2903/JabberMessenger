package com.jabbermessenger.controllers;

import com.jabbermessenger.components.TimeLineItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class TimeLineController implements Initializable {
    @FXML
    private Label userName;

    @FXML
    private Label jab;

    @FXML
    Image likeB = new Image(getClass().getResourceAsStream("likeB.jpg"));

    @FXML
    private ImageView likeButton = new ImageView();


    @FXML
    private Label numLikes;
    private String jabID;
    boolean hasLiked=true;


    public void setTimeline(TimeLineItem item){
        userName.setText(item.getUserName());
        jab.setText(item.getJab());
        numLikes.setText(item.getNumLikes());
        jabID=item.getJabId();
    }

    public void addLike(ActionEvent event){


        while(hasLiked){
            int newLikes = Integer.parseInt(numLikes.getText())+1;
            numLikes.setText(Integer.toString(newLikes));
            MainController.model.addLike(jabID);
            hasLiked=false;
        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        likeButton.setImage(likeB);
    }
}

