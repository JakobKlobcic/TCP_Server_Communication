package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    public TextField myMessage;
    public ListView MessageList;
    static ObservableList<String> itemList;


    //Button click
    public void handleButtonClick(){
        String message = myMessage.getText().toString();
        addListItem(message);
        System.out.println("Controller.handleButtonClick()");
    }

            //Exception in thread "Thread-5" java.lang.IllegalStateException: Not on FX application thread; currentThread = Thread-5
    //TODO: poglej kako deluje naslednja zadeva--> Platform.runLater()
    public static void addListItem(String Item) {
        if (!Item.equals("")) {
            itemList.add(Item);
            System.out.println("Controller.addListItem()");
            System.out.println("itemList: "+itemList);
        }

    }

        //Button click
    public void onServerConnect(MouseEvent mouseEvent) {
        Server server = new Server(2001);
        server.start();
        System.out.println("Controller.onServerConnect()");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemList= FXCollections.observableArrayList();
        MessageList.setItems(itemList);
        System.out.println("Controller.initialize()");
    }
}
