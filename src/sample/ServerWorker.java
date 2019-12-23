package sample;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

public class ServerWorker extends Thread{

    private final Socket clientSocket;
    private BufferedReader reader;
    private final Server server;
    private static OutputStream outputStream;




    ServerWorker(Server server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run(){
        try {
            try {
                System.out.println("Server Worker Run()");
                handleClientSocket();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private void handleClientSocket() throws IOException, ClassNotFoundException, SQLException {

        try{

            System.out.println("Server Worker handleClientSocket()");
            InputStream inputStream = clientSocket.getInputStream();
            this.outputStream = clientSocket.getOutputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
           // send("Sporočilo 123");
            String result ="";

            String line = "";
            System.out.println("before outputStream");
             //TODO:vprašaj urbana o tej vrstici  //outputStream.write(line.getBytes());

            while((line = reader.readLine()) != null){
                result += line;
                System.out.println("Line: "+line);

                Controller.addListItem("C:\n"+line);



            }

           // controller.addListItem(result);
        }catch(IOException e){
            System.out.println("Catch Exception handleClientSocket(): "+e);
        }

    }
/*
    public String getLogin(){
        return login;
    }

    private void handleLogin(OutputStream outputStream, String[] tokens) throws IOException, ClassNotFoundException, SQLException {
        if(tokens.length == 3){
            String login = tokens[1];
            String password = tokens[2];

            if(!isDataBaseConnected()){
                connect();
            }
            if(isLogin(login, password)){
                String msg = "ok Login\n";
                outputStream.write(msg.getBytes());
                this.login = login;
                System.out.println("User logged in successfully: " + login + "\n");

                List<ServerWorker> workerList = server.getWorkerList();
                //current user all online users
                for(ServerWorker worker: workerList){
                    if(worker.getLogin() != null){
                        if(!login.equals(worker.getLogin())){
                            String msg2 = "online " + worker.getLogin() + "\n";
                            send(msg2);
                        }
                    }

                }
                //notifies other users about active users
                String onLineMsg = "online " + login + "\n";
                for(ServerWorker worker: workerList){
                    if(!login.equals(worker.getLogin())){
                        worker.send(onLineMsg);
                    }
                }

            }else {
                String msg = "error login\n";
                outputStream.write(msg.getBytes());
            }
        }
    }



    private void handleLogoff() throws IOException {
        server.removeWorker(this);
        List<ServerWorker> workerList = server.getWorkerList();

        String onLineMsg = "offline " + login + "\n";
        for(ServerWorker worker: workerList){
            if(!login.equals(worker.getLogin())){
                worker.send(onLineMsg);
            }
        }
        clientSocket.close();
    }

*/
        public static void send(String onLineMsg){
            try {
                outputStream.write(onLineMsg.getBytes());
                    Controller controller = new Controller();
                    controller.addListItem("S:\n"+onLineMsg);
            }catch(IOException e){
                //notify about message faliour
            }
        }


}



