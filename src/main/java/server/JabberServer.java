package server;
import java.net.ServerSocket;
import java.net.Socket;

public class JabberServer implements Runnable {
    private static final int PORT_NUMBER=44444;
    private ServerSocket serverSocket;


    public JabberServer(){
        try{
            serverSocket=new ServerSocket(PORT_NUMBER);
//      serverSocket.setSoTimeout(300);
            new Thread(this).start();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        Socket clientSocket;
        try{
            while(true){
                Thread.sleep(100);
                clientSocket=serverSocket.accept();
                System.out.println("Client connected");
                ClientHandler client = new ClientHandler(clientSocket,new JabberDatabase());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new JabberServer();
    }
}