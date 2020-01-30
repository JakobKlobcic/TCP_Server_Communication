package sample;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    public TextField myMessage;
    public ListView MessageList;
    public TextField IpTextField;
    public TextField PortNumberTextField;
    static ObservableList<String> itemList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemList= FXCollections.observableArrayList();

        MessageList.setItems(itemList);

        IpTextField.setPromptText("IP"); //to set the hint text
        IpTextField.getParent().requestFocus();

        PortNumberTextField.setPromptText("Port"); //to set the hint text
        PortNumberTextField.getParent().requestFocus();
        PortNumberTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    PortNumberTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        System.out.println("Controller.initialize()");

    }

/*    String IPtext="";
    public void handleIpButtonClick(){

    }*/



    //Button click
    public void handleButtonClick(){
        String message = myMessage.getText().toString();
        //addListItem(message);
        myMessage.setText("");
        ServerWorker.send(message);
        System.out.println("Controller.handleButtonClick()");

    }


    public static void addListItem(String Item) {
        System.out.println("Controller.addListItem()");
        Platform.runLater(new Runnable() {
            public void run() {
                if (!Item.equals("")) {
                    itemList.add(Item);
                    System.out.println("itemList: "+itemList);
                }
            }
        });
    }


        //Button click
    public void onServerConnect(MouseEvent mouseEvent) {
        Server server = new Server(Integer.parseInt(PortNumberTextField.getText()));
        server.start();
        System.out.println("Controller.onServerConnect()");
    }


    //ko v TextField pritisnem na Enter
    @FXML
    public void onEnter(ActionEvent ae){
        handleButtonClick();

    }


}
