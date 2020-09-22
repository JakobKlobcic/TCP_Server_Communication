package sample;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    public TextField myMessage;
    public ListView MessageList;
    public TextField PortNumberTextField;
    static ObservableList<String> itemList;
    public Server server;

    //tole gre v server worker z kmounikacijo
    public static String longText = "";
    int longTextCount = 1;
    int count=1;
    //--------------------------------------


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controller.initialize()");
        itemList= FXCollections.observableArrayList();

        MessageList.setItems(itemList);
        myMessage.setText("/UKZ33333333333NK");

        PortNumberTextField.setPromptText("Port"); //to set the hint text
        PortNumberTextField.getParent().requestFocus();
        PortNumberTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!newValue.matches("\\d*")) {
                    PortNumberTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        }); //only allowes numbers to be entered into the EditText

        System.out.println("Controller.initialize()");

        //------tale zadeva gre potem v server worker
        //new TextAnalysis("2353524730303030314B24504C4A0184287BAD9CC562E700010000000143000083D07B629CEB63010001000000010D000084677B599D24642B000000000102EF00000CCF00495624504C4A024E444C24504C4A034E444C000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001A1A0D");
        //new TextAnalysis("2354524630303030314B24504C4A014E444C24504C4A024E444C24504C4A0384287BAD9CC562E700010000000143000083D07B629CEB63010001000000010D000084677B599D24642B000000000102EF00000CCF004956000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001A1A0D");
        //new TextAnalysis("2354524630303030314B24504C4A0184287BAD9CC562E700010000000143000083D07B629CEB63010001000000010D000084677B599D24642B000000000102EF00000CCF00495624504C4A0284287BAD9CC562E700010000000143000083D07B629CEB63010001000000010D000084677B599D24642B000000000102EF00000CCF00495624504C4A034E444C000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001A1A0D");

        DatabaseHandler.selectFromDatabase("Komunikacija" , "ID,ID_Postaje,Sporocilo,Datum_Cas",null,null);
        //DatabaseHandler.insertIntoDatabase("Komunikacija","ID_Postaje, Sporocilo, Datum_Cas","'00001', 'gremo na morje', '23/02/2008 00:00:00'");
    }


    //Button click
    public void handleButtonClick(){
        System.out.println("Controller.handleButtonClick()");
        String message = myMessage.getText();
        myMessage.setText("");
        ServerWorker.send(message);
/*

        //tale zadeva simulira pet poslanih sporočil Todo: to bo prejeto preko povezave od clienta
        if (count ==1){
            checkLongText("2343484E4B00205354525431303030302324504C4A01839E7B939CE36353000100000001420083FF7BA39C79638E000100000001420184087BA59C6E62D7000100000001470283C77BA69CC96319000100000001430384187B969D3563020001000000014304841F7BBA9CF662DD000100000001410584097BB19D0762E10001000000014206840D7BA19CF362E7000100000001420784157BBA9CA96295000100000001470883F27BC99D426309000100002343484E4B002053545023");
            System.out.println("1");
        }
        if (count ==2){
            checkLongText("2343484E4B01205354525431303030302300014709840D7BA99DB562D9000100000001420A84137BA59D3962EB000100000001420B83FA7BD09CD862BD000100000001430C83FA7BA49DD06308000100000001470D84057BF29CE8627A000100000001470E84337BE09D476389000100000001000F0083C97B8D9CE263080001000000010A0083B97B639C4862B40001000000010A0183DB7B469CE163190001000000010A0283B87B519CF462F6000100002343484E4B012053545023");
            System.out.println("2");
        }
        if (count ==3){
            checkLongText("2343484E4B02205354525431303030302300010A0383B37B4A9CC962D10001000000010B0483DE7B6B9D1263010001000000010C0583B07B6C9CD762B90001000000010A0683D27B1F9CE962F90001000000010B0783D87B489CEB62D20001000000010B0883C77B4E9CC162B60001000000010A0983EC7B589D0762C90001000000010B0A83A57B249C8062E50001000000010C0B83D67B879C9262D40001000000010B0C83C17B769C6662D600010000002343484E4B022053545023");
            System.out.println("3");
        }
        if (count ==4){
            checkLongText("2343484E4B032053545254313030303023010C0D83C47B569C9F62C10001000000010B0E83BE7B479D166340000100000001000F00847F7B779CFD6350000000000102ED00845B7B769CFB63AD000000000102EE0184607B659CFF6363000000000102EC02846C7B979CB9635F000000000102EE0384867B6B9CC4635B000000000102ED0484697B6B9CFD6349000000000102EE05847B7B679CEC6381000000000102ED0684847B909C8D63A100000000012343484E4B032053545023");
            System.out.println("4");
        }
        if (count ==5){
            checkLongText("2343484E4B04205354525431303030302302ED0784577B7F9CA463C4000000000102EE0884257B719D3163BA000000000102EE0984477B669C6263EE000000000102EE0A84867B639CD4633F000000000102EE0B84617B469D2F6373000000000102EE0C84827B619CFB6399000000000102ED0D84907B569CDB6346000000000102ED0E84587B759CE963E7000000000102000F000CB600495624504C4A024E444C24504C4A034E444C00000000000000002343484E4B042053545023");
            System.out.println("5");
            count=1;
        }
        count++;

*/
    }


    public static void addListItem(String Item) {
        System.out.println("Controller.addListItem()");
        Platform.runLater(new Runnable() {
            public void run() {
                if (!Item.equals("")) {
                    itemList.add(Item);
                    System.out.println("itemList: \n"+itemList);
                }
            }
        });
    }


        //Button click
    public void onServerConnect(MouseEvent mouseEvent) {
        System.out.println("Controller.onServerConnect()");
        server = new Server(Integer.parseInt(PortNumberTextField.getText()));
        server.start();
    }


    //ko v TextField pritisnem na Enter
    @FXML
    public void onEnter(ActionEvent ae){
        System.out.println("Controller.onEnter()");
        handleButtonClick();

    }




    //todo: tale zadeva se izbriše ko bo možno komunicirati preko TCP povezave in ostane samo v ServerWorker
    public void checkLongText(String text){
        System.out.println("Controller.checkLongText()");
        String CHNK = "2343484E4B";  //2343484E4B = "#CHNK" v hex
        String checkCHNK = text.substring(0, 10);//vzame tekst od elementa 0 do 10
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

}
