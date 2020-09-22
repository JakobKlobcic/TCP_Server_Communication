package sample;


import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread{

    private final int serverPort;

    private List<ServerWorker> workerList = new ArrayList<>();

    Server(int serverPort) {
        this.serverPort = serverPort;
    }


    public List<ServerWorker> getWorkerList(){
        return workerList;
    }

    @Override
    public void run(){
        System.out.println("Server.run()");
        try {
            ServerSocket serverSocket = new ServerSocket(serverPort);
            System.out.println(serverSocket);
            while(true){
                System.out.println("2!!");
                Socket clientSocket = serverSocket.accept();
                ServerWorker worker = new ServerWorker(this, clientSocket);
                workerList.add(worker);
                worker.start();
                System.out.println("2??");
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void removeWorker(ServerWorker serverWorker) {
        workerList.remove(serverWorker);
    }


}