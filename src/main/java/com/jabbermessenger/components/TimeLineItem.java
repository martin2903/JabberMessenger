package com.jabbermessenger.components;
import javafx.scene.control.Button;

public class TimeLineItem {
    private String userName;
    private String jab;
    private String jabId;
    private String numLikes;
    private Button button;

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getJab() {
        return jab;
    }

    public void setJab(String jab) {
        this.jab = jab;
    }

    public String getJabId() {
        return jabId;
    }

    public void setJabId(String jabId) {
        this.jabId = jabId;
    }

    public String getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(String numLikes) {
        this.numLikes = numLikes;
    }
}

