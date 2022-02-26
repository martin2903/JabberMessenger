package com.jabbermessenger.model;

import com.jabbermessenger.components.JabberMessage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Model {

    Socket connection;
    public Model(){
        try{
            connection=new Socket("localhost",44444);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean isSignedIn(String username){
        //creating the JabberMessage object to be sent to the server.
        JabberMessage signIn = new JabberMessage("signin "+username);
        //send message to server and get response
        JabberMessage response =sendToServer(signIn);

        if(response.getMessage().equals("signedin")){
            return true;
        }else
            return false;
    }

    public boolean register(String username){
        JabberMessage registerUser = new JabberMessage("register "+username);

        JabberMessage response = sendToServer(registerUser);
        if(response.getMessage().equals("signedin")){
            return true;
        }else
            return false;
    }

    public ArrayList<ArrayList<String>> getTimeline(){
        JabberMessage userTimeline = new JabberMessage("timeline");
        JabberMessage response = sendToServer(userTimeline);
        if(response.getMessage().equals("timeline")){
            return response.getData();
        }else
            return null;
    }


    public JabberMessage sendToServer(JabberMessage message){
        JabberMessage response = null;
        try{
            //sending the JabberMessage in the socket.
            ObjectOutputStream outToServer = new ObjectOutputStream(connection.getOutputStream());
            outToServer.writeObject(message);
            outToServer.flush();

            //receiving server response.
            ObjectInputStream inFromServer = new ObjectInputStream(connection.getInputStream());
            response = (JabberMessage) inFromServer.readObject();
        }catch(Exception e){
            e.printStackTrace();
        }
        return response;
    }

    public void signOut(){
        JabberMessage signOut = new JabberMessage("signout");
        try{
            ObjectOutputStream outToServer = new ObjectOutputStream(connection.getOutputStream());
            outToServer.writeObject(signOut);
            outToServer.flush();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public JabberMessage getUserName(){
        JabberMessage message = new JabberMessage("username");
        JabberMessage response = sendToServer(message);
        return response;
    }

    public void addLike(String jabID){
        JabberMessage message = new JabberMessage("like "+jabID);
        try{
            ObjectOutputStream outToServer = new ObjectOutputStream(connection.getOutputStream());
            outToServer.writeObject(message);
            outToServer.flush();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<ArrayList<String>> getUsersToFollow(){
        JabberMessage message = new JabberMessage("users");
        JabberMessage response = sendToServer(message);
        if(response.getMessage().equals("users")){
            return response.getData();
        }else
            return null;
    }

    public void sendFollowRequest(String username){
        JabberMessage message = new JabberMessage("follow "+username);
        try{
            ObjectOutputStream outToServer = new ObjectOutputStream(connection.getOutputStream());
            outToServer.writeObject(message);
            outToServer.flush();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void postJab(String jab){
        JabberMessage message = new JabberMessage("post "+jab);
        try{
            ObjectOutputStream outToServer = new ObjectOutputStream(connection.getOutputStream());
            outToServer.writeObject(message);
            outToServer.flush();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

