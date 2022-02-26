package server;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import com.jabbermessenger.components.JabberMessage;

public class ClientHandler implements Runnable{

    private JabberDatabase db;
    private Socket clientSocket;
    private int userID = 0;
    private String userName;

    public ClientHandler(Socket client, JabberDatabase db){
        this.db = db;
        this.clientSocket = client;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            while(true) {
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                String[] identifier = new String[70];
                JabberMessage request = (JabberMessage) in.readObject();
                if (request.getMessage().contains(" ")) {
                    identifier = request.getMessage().split(" ");
                } else {
                    identifier[0] = request.getMessage();

                }

                if (identifier[0].contains("signin")) {
                    signIn(identifier[1], out);
                } else if (identifier[0].contains("register")) {
                    registerUser(identifier[1], out);
                } else if (identifier[0].contains("signout")) {
                    resetCredentials();
                    break;
                } else if (identifier[0].contains("timeline")) {
                    getTimeline(out);
                } else if (identifier[0].contains("users")) {
                    getUnfollowedUsers(out);
                } else if (identifier[0].contains("post")) {
                    StringBuilder stringy = new StringBuilder();
                    for(int i = 1; i < identifier.length; i++){
                        stringy.append(identifier[i]);
                        stringy.append(" ");
                    }
                    postJab(out, stringy.toString().trim());
                } else if (identifier[0].contains("like")) {
                    likeJab(out, Integer.parseInt(identifier[1]));
                } else if (identifier[0].contains("follow")) {
                    followUser(identifier[1], out);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public synchronized String getName() {
        return userName;
    }

    public synchronized void setName(String userName) {
        this.userName = userName;
    }

    public synchronized int getID() {
        return userID;
    }

    public synchronized void setID(int currentUser) {
        this.userID =currentUser;
    }

    public synchronized void signIn(String user, ObjectOutputStream out){
        String message;
        if(db.getUserID(user) != -1){
            message = "signedin";
            setID(db.getUserID(user));
            setName(user);
        } else{
            message = "unknown-user";
        }
        try {
            out.writeObject(new JabberMessage(message));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void registerUser(String user, ObjectOutputStream out){
        String message;
        String noEmail = "N/A";
        db.addUser(user, noEmail);
        setID(db.getUserID(user));
        setName(user);
        message = "signedin";
        try {
            out.writeObject(new JabberMessage(message));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void getUnfollowedUsers(ObjectOutputStream out){
        try {
            String message;
            message = "users";
            ArrayList<ArrayList<String>> users = db.getUsersNotFollowed(getID());
            out.writeObject(new JabberMessage(message, users));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void getTimeline(ObjectOutputStream out){
        try {
            String message;
            ArrayList<ArrayList<String>> timeline = db.getTimelineOfUserEx(getID());
            message = "timeline";
            out.writeObject(new JabberMessage(message, timeline));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void postJab(ObjectOutputStream out, String jab){
        String message;
        db.addJab(getName(), jab);
        message = "posted";
        try {
            out.writeObject(new JabberMessage(message));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void likeJab(ObjectOutputStream out, int jabId){
        String message;
        db.addLike(getID(), jabId);
        message = "posted";
        try {
            out.writeObject(new JabberMessage(message));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void followUser(String user, ObjectOutputStream out) {
        String message;
        db.addFollower(getID(), user);
        message = "posted";
        try {
            out.writeObject(new JabberMessage(message));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void resetCredentials(){
        userName = "";
        userID = 0;
    }
}









