package sample;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
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


    public static void addListItem(String Item) {
        Platform.runLater(new Runnable() {
            public void run() {
                if (!Item.equals("")) {
                    itemList.add(Item);
                    System.out.println("Controller.addListItem()");
                    System.out.println("itemList: "+itemList);
                }
            }
        });


    }

/*    public static void addListItem(String Item) {
        System.out.println("Controller.addListItem()");
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                if (!Item.equals("")) {
                    itemList.add(Item);
                    System.out.println("Controller.addListItem()");
                    System.out.println("itemList: " + itemList);
                }

                return null;
            }
        };
    }*/


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
