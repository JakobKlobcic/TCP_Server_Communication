package sample;

import sun.misc.IOUtils;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ServerWorker extends Thread{

    private final Socket clientSocket;
    private BufferedReader reader;
    private final Server server;
    private static OutputStream outputStream;
    public static String longText = "";
    int longTextCount = 1;




    ServerWorker(Server server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
        System.out.println("Server Worker Constructor");
        System.out.println(clientSocket);
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
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            this.outputStream = clientSocket.getOutputStream();
            /*BufferedReader*/reader = new BufferedReader(new InputStreamReader(inputStream));
            System.out.println(inputStream);


            Controller controller = new Controller();

            String result ="";
            String line = "";
            System.out.println("bytearray");
            System.out.println(getByte(inputStream).length);
            while((line=reader.readLine()) != null){
                result += line + " ";
                System.out.println("Hex: "+stringToHex(line) );
                System.out.println("line: "+line );

                System.out.println("byteArray: "+getByte(inputStream).toString());
                new TextAnalysis(stringToHex(line));

                //controller.addListItem("C:\n"+stringToHex(line));
                String data = "'getID', '"+line+"', '"+getCurrentTime()+"'";
                //DatabaseHandler.insertIntoDatabase("Komunikacija", "ID_Postaje, Sporocilo, Datum_Cas",data);
                //controller.addListItem("C:\n"+line);                      //"C:\n" v ListView doda oznako da je sporočilo od clienta
            }


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
                //byte[] b = string.getBytes();   Spremeni text v byte
                outputStream.write(onLineMsg.getBytes());
                    Controller controller = new Controller();
                   controller.addListItem("S:\n"+onLineMsg);
            }catch(IOException e){
                System.out.println("ServerWorker.send(); Error: "+e);
            }
        }

            //Turn received ascii text to hex text
    public static String stringToHex(String text){

            // Step-1 - Convert ASCII string to char array
            char[] ch = text.toCharArray();
            // Step-2 Iterate over char array and cast each element to Integer.
            StringBuilder builder = new StringBuilder();

            for (char c : ch) {
                int i = (int) c;
                // Step-3 Convert integer value to hex using toHexString() method.
                builder.append(Integer.toHexString(i).toUpperCase());
            }
            System.out.println(builder.toString());

        return builder.toString();
        }

    public void checkLongText(String text){
        String CHNK = "2343484E4B";  //2343484E4B = "#CHNK" v hex
        String checkCHNK = text.substring(0, 10);
        String checkCount = text.substring(10,12);
        System.out.println("CheckLongText");
        System.out.println(longTextCount);

        if (checkCHNK.equals(CHNK) && checkCount.equals("00") && longTextCount==1){
            longTextCount++;
            longText=text;
        }else if(checkCHNK.equals(CHNK) && checkCount.equals("01")&& longTextCount==2){
            longTextCount++;
            longText+=text;
        }
        else if(checkCHNK.equals(CHNK) && checkCount.equals("02")&& longTextCount==3){
            longTextCount++;
            longText+=text;
        }
        else if(checkCHNK.equals(CHNK) && checkCount.equals("03")&& longTextCount==4){
            longTextCount++;
            longText+=text;
        }
        else if(checkCHNK.equals(CHNK) && checkCount.equals("04")&& longTextCount==5){
            longTextCount=0;
            longText+=text;
            new TextAnalysis(longText);
        }
    }

    public byte [] getByte(InputStream is){
        try{
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            buffer.flush();
            byte[] byteArray = buffer.toByteArray();

            return byteArray;
        }catch(Exception e){
            return null;
        }

    }

    //pretvori hex string to ascii string
    public static String hexToString(String hex){
        System.out.println("ServerWorker.hexToString()");
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < hex.length(); i+=2) {
            String str = hex.substring(i, i+2);
            output.append((char)Integer.parseInt(str, 16));
        }
        return output.toString();
    }

    public static String getCurrentTime(){
        SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println("Right now "+formatter.format(date));
        return formatter.format(date);
    }
}