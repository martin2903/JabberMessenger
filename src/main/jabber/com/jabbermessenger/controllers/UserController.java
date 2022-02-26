package com.jabbermessenger.controllers;

import com.jabbermessenger.components.TimeLineItem;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class UserController implements Initializable {

    @FXML
    private Label userLabel;
    @FXML
    private Button B3;

    @FXML
    private VBox timeLineLayout;
    private List<TimeLineItem> userTimeline=new ArrayList<>();

    @FXML
    private VBox followUsers;
    @FXML
    private TextField jabField;

    Image image = new Image(getClass().getResourceAsStream("picture.png"));
    @FXML
    ImageView profilePicture= new ImageView();




    public void greetUser(String username){
        userLabel.setText("Hello "+username +" !");
    }

    public void signOut(ActionEvent event){
        ((Node)event.getSource()).getScene().getWindow().hide();
        Platform.exit();
        System.exit(0);
        MainController.model.signOut();
    }

    public void getUserTimeline(ArrayList<ArrayList<String>> timeLine){

        Platform.runLater(new Runnable() {
            @Override
            public void run()  {
                //retrieve the data for each timelineItem
                for(int i=0;i<timeLine.size();i++){
                    TimeLineItem item = new TimeLineItem();
                    item.setUserName(timeLine.get(i).get(0)+":");
                    item.setJab(timeLine.get(i).get(1));
                    item.setJabId(timeLine.get(i).get(2));
                    item.setNumLikes(timeLine.get(i).get(3));
                    item.setButton(new Button());
                    userTimeline.add(item);
                }

                //Dynamically add the Hboxes to the timeline Vbox with the relevant data
                for(int i=0;i<userTimeline.size();i++){
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("TimeLineItem.fxml"));

                    try{
                        HBox hBox = fxmlLoader.load();
                        TimeLineController controller = fxmlLoader.getController();
                        controller.setTimeline(userTimeline.get(i));
                        timeLineLayout.getChildren().add(hBox);

                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }

                ArrayList<ArrayList<String>> usersToFollow = MainController.model.getUsersToFollow();
                for(int i=0;i<usersToFollow.size();i++){
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("followUserItem.fxml"));
                    try{
                        HBox hBox=fxmlLoader.load();
                        FollowUserController controller = fxmlLoader.getController();
                        controller.setUserToFollow(usersToFollow.get(i).get(0));
                        followUsers.getChildren().add(hBox);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    public void postJab(ActionEvent event){
        MainController.model.postJab(jabField.getText());
        jabField.clear();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        profilePicture.setImage(image);
    }
}

