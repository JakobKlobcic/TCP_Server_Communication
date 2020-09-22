package sample;

import java.util.ArrayList;
import java.util.List;

public class TextAnalysis {
    static String textInQuestion="";
    static List<String> mainTextList = new ArrayList<String>();


    TextAnalysis(String message) {
        System.out.println("TextAnalysis()");
        this.textInQuestion = message;
        mainTextList=textToList(message);
        System.out.println("mssg: "+NicerListString(mainTextList.toString()));
/*
        char [] id = new char[5];
        id[0]=hexToString(  mainTextList.get(4) ).charAt(0);
        id[1]=hexToString(  mainTextList.get(5) ).charAt(0);
        id[2]=hexToString(  mainTextList.get(6) ).charAt(0);
        id[3]=hexToString(  mainTextList.get(7) ).charAt(0);
        id[4]=hexToString(  mainTextList.get(8) ).charAt(0);
        String ID = new String(id);
        System.out.println("ID: "+ID);*/

        if(mainTextList.get(9).equals("4B")){//4B='K'
            shortText();
        }else if(mainTextList.get(0).equals("23") && mainTextList.get(1).equals("43") && mainTextList.get(2).equals("48") ){//23 43 48 4E 4B 01 20 53 54 52 54 31 30 30 30 30 23 --> #  C  H  N  K <01>   S  T  R  T  1  0  0  0  0  #
            longText();
        }

    }

    public static void shortText(){
        System.out.println("short Text: ");
        String newOutput="";
        System.out.println(mainTextList.size());

        newOutput+=NicerListString(mainTextList.subList(0,10).toString()) + "\n";
        int j =0;
        while(j<mainTextList.size()){
                //System.out.println("To naj bi bilo:4E444C--> "+mainTextList.get(j+15)+mainTextList.get(j+16)+mainTextList.get(j+17));


                if(mainTextList.get(j+11).equals("50"/*P*/) && mainTextList.get(j+12).equals("4C"/*L*/) && mainTextList.get(j+13).equals("4A"/*J*/)){
                    if(mainTextList.get(j+15).equals("4E"/*N*/) && mainTextList.get(j+16).equals("44"/*D*/) && mainTextList.get(j+17).equals("4C"/*L*/)){

                        newOutput+=NicerListString(mainTextList.subList(j+10,j+18).toString()) + "\n";
                        System.out.println("PLY: "+ mainTextList.get(j+14)+"; NDL -> ne dela");

                        j += 8;
                    }else{
                        System.out.println("\n podatki za PLJ "+mainTextList.get(j+14)+" so med elementom "+ (j+15)+" in " +(j+70)+"; j = "+j);
                        //System.out.println("Podatki: "+ NicerListString(mainTextList.subList(j+15,j+70).toString()));

                        newOutput+=NicerListString(mainTextList.subList(j+10,j+15).toString()) + "\n";//$PLJ01

                        newOutput+=NicerListString(mainTextList.subList(j+15,j+31).toString()) + "\n";
                        newOutput+=mainTextList.get(31) + "\n";
                        newOutput+=NicerListString(mainTextList.subList(j+32,j+48).toString()) + "\n";
                        newOutput+=mainTextList.get(48) + "\n";
                        newOutput+=NicerListString(mainTextList.subList(j+49,j+65).toString()) + "\n";
                        newOutput+=mainTextList.get(65) + "\n";
                        newOutput+=NicerListString(mainTextList.subList(j+66,j+71).toString()) + "\n";

                        j+=61;
                    }
                }else{
                    j=mainTextList.size();
                }

        }
        System.out.println("newOutput="+ "\n"+newOutput);
        Controller.addListItem(newOutput);
       /*
        int i = 0 ;
        while(i<87){
                  if (i<15){
                      newOutput = newOutput + mainTextList.get(i) + " ";
                            if(i==14){newOutput=newOutput+"\n";}

            }else if(i>= 15 && i<31){
                      newOutput = newOutput + mainTextList.get(i) + " ";
                            if(i==30){newOutput=newOutput+"\n";}

            }else if(i == 31){
                      newOutput = newOutput + mainTextList.get(i) + " "+"\n";

            }else if(i>= 32 && i<48){
                      newOutput = newOutput + mainTextList.get(i) + " ";
                            if(i==47){newOutput=newOutput+"\n";}

            }else if(i == 48){
                      newOutput = newOutput + mainTextList.get(i) + " "+"\n";

            }else if(i>= 49 && i<65){
                      newOutput = newOutput + mainTextList.get(i) + " ";
                            if(i==64){newOutput=newOutput+"\n";}

            }else if(i==65){
                      newOutput = newOutput + mainTextList.get(i) + " "+"\n";

            }else if(i>= 66 && i<71){
                      newOutput = newOutput + mainTextList.get(i) + " ";
                            if(i==70){newOutput=newOutput+"\n";}

            }else if(i>= 71 && i<79){
                      newOutput = newOutput + mainTextList.get(i) + " ";
                            if(i==78){newOutput=newOutput+"\n";}

            }else if(i>= 79 && i<88){
                      newOutput = newOutput + mainTextList.get(i) + " ";
                            if(i==87){newOutput=newOutput+"\n";}

            }
            i++;
        } //razdeli tekst na lepo obliko
            System.out.println(newOutput);
        Controller.addListItem(newOutput);
        */
    }

    public static void  longText(){
        System.out.println("long Text \n length: "+mainTextList.size());
        List<String> listToUse=new ArrayList<>(ChnkRemoval(mainTextList));


        String newOutput="";
        int j = 0;
        while(j<listToUse.size()){
            //System.out.println("j+18,j+19,j+20 so: "+listToUse.get(j+18)+" "+ listToUse.get(j+19)+" "+listToUse.get(j+20));
            if(listToUse.get(j+18).equals("50"/*P*/) && listToUse.get(j+19).equals("4C"/*L*/) && listToUse.get(j+20).equals("4A"/*J*/)){
                if(listToUse.get(j+22).equals("4E"/*N*/) && listToUse.get(j+23).equals("44"/*D*/) && listToUse.get(j+24).equals("4C"/*L*/)){

                    newOutput+=NicerListString(listToUse.subList(j+17,j+25).toString()) + "\n";
                    System.out.println("PLY: "+ listToUse.get(j+21)+"; NDL -> ne dela");

                    j += 8;
                }else{
                    System.out.println("\n podatki za PLJ "+listToUse.get(j+21)+" so med elementom "+ (j+22)+" in " +(j+798)+"; j = "+j);
                    //System.out.println("Podatki: "+ NicerListString(listToUse.subList(j+22,j+798).toString()));

                    newOutput+=NicerListString(listToUse.subList(j+17,j+22).toString()) + "\n"; //$PLJ01
                    //J JE ŠTEVEC 1.)PRVA ŠTEVILKA NASTAVI POZICIJO 2.) DRUGA SE PRIŠTEVA ZARADI VRSTIC V KATERIH JE SAMO EN ELEMENT 3.)KER JE V VSAKI DOLGI VRSTICI 16ELEMENTOV SE DODAJA PO ŠESTNAJST VSSAKO VRSTO
                    //vSE TOLE PA OBLIKUJE TEKST IN GA DODA V NEW OUTPUT IN TO PRIKAŽE V ListView
                    newOutput+=NicerListString(listToUse.subList(j+22+0+16*0,j+38+0+16*0).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+0+16*1,j+38+0+16*1).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+0+16*2,j+38+0+16*2).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+0+16*3,j+38+0+16*3).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+0+16*4,j+38+0+16*4).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+0+16*5,j+38+0+16*5).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+0+16*6,j+38+0+16*6).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+0+16*7,j+38+0+16*7).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+0+16*8,j+38+0+16*8).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+0+16*9,j+38+0+16*9).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+0+16*10,j+38+0+16*10).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+0+16*11,j+38+0+16*11).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+0+16*12,j+38+0+16*12).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+0+16*13,j+38+0+16*13).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+0+16*14,j+38+0+16*14).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+0+16*15,j+38+0+16*15).toString()) + "\n"
                              +listToUse.get(j+22+16*16) + "\n"
                              +NicerListString(listToUse.subList(j+22+1+16*16,j+1+38+16*16).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+1+16*17,j+1+38+16*17).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+1+16*18,j+1+38+16*18).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+1+16*19,j+1+38+16*19).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+1+16*20,j+1+38+16*20).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+1+16*21,j+1+38+16*21).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+1+16*22,j+1+38+16*22).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+1+16*23,j+1+38+16*23).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+1+16*24,j+1+38+16*24).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+1+16*25,j+1+38+16*25).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+1+16*26,j+1+38+16*26).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+1+16*27,j+1+38+16*27).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+1+16*28,j+1+38+16*28).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+1+16*29,j+1+38+16*29).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+1+16*30,j+1+38+16*30).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+1+16*31,j+1+38+16*31).toString()) + "\n"
                              +listToUse.get(j+1+38+16*31) + "\n"
                              +NicerListString(listToUse.subList(j+22+2+16*31,j+38+2+16*31).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+2+16*32,j+38+2+16*32).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+2+16*33,j+38+2+16*33).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+2+16*34,j+38+2+16*34).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+2+16*35,j+38+2+16*35).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+2+16*36,j+38+2+16*36).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+2+16*37,j+38+2+16*37).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+2+16*38,j+38+2+16*38).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+2+16*39,j+38+2+16*39).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+2+16*40,j+38+2+16*40).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+2+16*41,j+38+2+16*41).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+2+16*42,j+38+2+16*42).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+2+16*43,j+38+2+16*43).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+2+16*44,j+38+2+16*44).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+2+16*45,j+38+2+16*45).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+2+16*46,j+38+2+16*46).toString()) + "\n"
                              +NicerListString(listToUse.subList(j+22+2+16*47,j+38+2+16*47).toString()) + "\n"
                              +listToUse.get(j+38+2+16*47) + "\n"
                              +NicerListString(listToUse.subList(j+23+2+16*48,j+2+28+16*48).toString()) + "\n"   ;
                                //System.out.println("Razlika je: "+ Integer.toString(j+2+28+16*48-j-22) + "j="+j);


                    j+=781;
                }
            }else{
                j=listToUse.size();
            }
        //System.out.println("Zanka: " + j);
        }
       /* while(i<mainTextList.size()){
            if(i<17){

            }else if(i<22){
                newOutput = newOutput + mainTextList.get(i) + " ";
                if(i==21){newOutput=newOutput+"\n";}

            }else if(i<38){
                newOutput = newOutput + mainTextList.get(i) + " ";
                if(i==37){newOutput=newOutput+"\n";}
//16
            }else if(i<54){
                newOutput = newOutput + mainTextList.get(i) + " ";
                if(i==53){newOutput=newOutput+"\n";}

            }else if(i<70){
                newOutput = newOutput + mainTextList.get(i) + " ";
                if(i==69){newOutput=newOutput+"\n";}
            }else if(i<86){
                newOutput = newOutput + mainTextList.get(i) + " ";
                if(i==85){newOutput=newOutput+"\n";}
            }else if(i<102){
                newOutput = newOutput + mainTextList.get(i) + " ";
                if(i==101){newOutput=newOutput+"\n";}
            }else if(i<118){
                newOutput = newOutput + mainTextList.get(i) + " ";
                if(i==117){newOutput=newOutput+"\n";}
            }else if(i<134){
                newOutput = newOutput + mainTextList.get(i) + " ";
                if(i==133){newOutput=newOutput+"\n";}
            }else if(i<150){
                newOutput = newOutput + mainTextList.get(i) + " ";
                if(i==149){newOutput=newOutput+"\n";}
            }else if(i<166){
                newOutput = newOutput + mainTextList.get(i) + " ";
                if(i==165){newOutput=newOutput+"\n";}
//16
            }else if(i<178){
                newOutput = newOutput + mainTextList.get(i) + " ";

            }else if(i<206){
        //ne naredi nič   konec CHNK 0 in zacetek CHNK 1
            }else if(i<210){
                newOutput = newOutput + mainTextList.get(i) + " ";
                if(i==209){newOutput=newOutput+"\n";}
            }else if(i<226){
                newOutput = newOutput + mainTextList.get(i) + " ";
                if(i==225){newOutput=newOutput+"\n";}
//16
            }else if(i<242){
                newOutput = newOutput + mainTextList.get(i) + " ";
                if(i==241){newOutput=newOutput+"\n";}
            }else if(i<258){
                newOutput = newOutput + mainTextList.get(i) + " ";
                if(i==257){newOutput=newOutput+"\n";}
            }else if(i<274){
                newOutput = newOutput + mainTextList.get(i) + " ";
                if(i==273){newOutput=newOutput+"\n";}
            }else if(i<290){
                newOutput = newOutput + mainTextList.get(i) + " ";
                if(i==289){newOutput=newOutput+"\n";}
            }else if(i<306){
                newOutput = newOutput + mainTextList.get(i) + " ";
                if(i==305){newOutput=newOutput+"\n";}
//16
            }else if(i==306){
                newOutput = newOutput + mainTextList.get(i) + " "+"\n";

            }else if(i<323){
                newOutput = newOutput + mainTextList.get(i) + " ";
                if(i==322){newOutput=newOutput+"\n";}
//16
            }else if(i<339){
                newOutput = newOutput + mainTextList.get(i) + " ";
                 if(i==338){newOutput=newOutput+"\n";}
            }else if(i<355){
                newOutput = newOutput + mainTextList.get(i) + " ";
                 if(i==354){newOutput=newOutput+"\n";}
//16
            }else if(i<367){
                newOutput = newOutput + mainTextList.get(i) + " ";
            }else if(i<395){
//konec CHNK 1 začetek CHNK 2
            }else if(i<399){
                newOutput = newOutput + mainTextList.get(i) + " ";
                 if(i==398){newOutput=newOutput+"\n";}
            }else if(i<415){
//16
                newOutput = newOutput + mainTextList.get(i) + " ";
                 if(i==414){newOutput=newOutput+"\n";}
            }else if(i<431){
                newOutput = newOutput + mainTextList.get(i) + " ";
                 if(i==430){newOutput=newOutput+"\n";}
            }else if(i<447){
                newOutput = newOutput + mainTextList.get(i) + " ";
                 if(i==446){newOutput=newOutput+"\n";}
            }else if(i<463){
                newOutput = newOutput + mainTextList.get(i) + " ";
                 if(i==462){newOutput=newOutput+"\n";}
            }else if(i<479){
                newOutput = newOutput + mainTextList.get(i) + " ";
                 if(i==478){newOutput=newOutput+"\n";}
            }else if(i<495){
                newOutput = newOutput + mainTextList.get(i) + " ";
                 if(i==494){newOutput=newOutput+"\n";}
            }else if(i<511){
                newOutput = newOutput + mainTextList.get(i) + " ";
                 if(i==510){newOutput=newOutput+"\n";}
            }else if(i<527){
                newOutput = newOutput + mainTextList.get(i) + " ";
                 if(i==526){newOutput=newOutput+"\n";}
            }else if(i<543){
                newOutput = newOutput + mainTextList.get(i) + " ";
                 if(i==542){newOutput=newOutput+"\n";}
//16
            }else if(i<557){
                newOutput = newOutput + mainTextList.get(i) + " ";
            }else if(i<585){
//konec chnk 2 zacetek chnk 3
            }else if(i<587){
                newOutput = newOutput + mainTextList.get(i) + " ";
                 if(i==586){newOutput=newOutput+"\n";}
            }else if(i<603){
                newOutput = newOutput + mainTextList.get(i) + " ";
                    if(i==602){newOutput=newOutput+"\n";}
            }else if(i<619){
                newOutput = newOutput + mainTextList.get(i) + " ";
                if(i==618){newOutput=newOutput+"\n";}
            }else if(i==619){
                newOutput = newOutput + mainTextList.get(i) + " " + "\n";
            }else if(i<636) {
                newOutput = newOutput + mainTextList.get(i) + " ";
                if (i ==635) { newOutput = newOutput + "\n"; }
            }else if(i<652) {
                newOutput = newOutput + mainTextList.get(i) + " ";
                if (i ==651) { newOutput = newOutput + "\n"; }
            }else if(i<668) {
                newOutput = newOutput + mainTextList.get(i) + " ";
                if (i ==667) { newOutput = newOutput + "\n"; }
            }else if(i<684) {
                newOutput = newOutput + mainTextList.get(i) + " ";
                if (i ==683) { newOutput = newOutput + "\n"; }
            }else if(i<700) {
                newOutput = newOutput + mainTextList.get(i) + " ";
                if (i ==699) { newOutput = newOutput + "\n"; }
            }else if(i<716) {
                newOutput = newOutput + mainTextList.get(i) + " ";
                if (i ==715) { newOutput = newOutput + "\n"; }
            }else if(i<732) {
                newOutput = newOutput + mainTextList.get(i) + " ";
                if (i ==731) { newOutput = newOutput + "\n"; }
            }else if(i<745) {
                newOutput = newOutput + mainTextList.get(i) + " ";
                //if (i ==744) { newOutput = newOutput + "\n"; }
            }else if(i<773) {
//konec chnk3 zacetek cnhk 4
            }else if(i<776) {
                newOutput = newOutput + mainTextList.get(i) + " ";
                if (i ==775) { newOutput = newOutput + "\n"; }
            }else if(i<792) {
                newOutput = newOutput + mainTextList.get(i) + " ";
                if (i ==791) { newOutput = newOutput + "\n"; }
//16
            }else if(i<808) {
                newOutput = newOutput + mainTextList.get(i) + " ";
                if (i ==807) { newOutput = newOutput + "\n"; }
            }else if(i<824) {
                newOutput = newOutput + mainTextList.get(i) + " ";
                if (i ==823) { newOutput = newOutput + "\n"; }
            }else if(i<840) {
                newOutput = newOutput + mainTextList.get(i) + " ";
                if (i ==839) { newOutput = newOutput + "\n"; }
            }else if(i<856) {
                newOutput = newOutput + mainTextList.get(i) + " ";
                if (i ==855) { newOutput = newOutput + "\n"; }
            }else if(i<872) {
                newOutput = newOutput + mainTextList.get(i) + " ";
                if (i ==871) { newOutput = newOutput + "\n"; }
            }else if(i<888) {
                newOutput = newOutput + mainTextList.get(i) + " ";
                if (i ==887) { newOutput = newOutput + "\n"; }
            }else if(i<904) {
                newOutput = newOutput + mainTextList.get(i) + " ";
                if (i ==903) { newOutput = newOutput + "\n"; }
//16
            }else if(i==904) {
                newOutput = newOutput + mainTextList.get(i) + " "+ "\n";;

            }else if(i<910) {
                newOutput = newOutput + mainTextList.get(i) + " ";
                if (i ==909) { newOutput = newOutput + "\n"; }
            }else if(i<918) {
                newOutput = newOutput + mainTextList.get(i) + " ";
                if (i ==917) { newOutput = newOutput + "\n"; }
            }else if(i<926) {
                newOutput = newOutput + mainTextList.get(i) + " ";
                if (i ==925) { newOutput = newOutput + "\n"; }
            }else if(i<934) {
                newOutput = newOutput + mainTextList.get(i) + " ";
                if (i ==933) { newOutput = newOutput + "\n"; }
            }
            i++;
        }*/
        System.out.println(newOutput);
        Controller.addListItem(newOutput);
    }
        //spremeni tekst združen iz več kosov(CHNK) v enoten podatek brez prekinjanja // se uporablja za longText()
    public static List<String> ChnkRemoval(List<String> listForMe){
        System.out.println("TextAnalysis.ChnkRemoval()");
        List<String> ReturnList = new ArrayList<>();
        List<List<String>> CHNK = new ArrayList<>();
        //189 je velikost enega CHNK - kosa
        int j = 0;
        int chnkCount= listForMe.size()/189;
        while(j<chnkCount){
            CHNK.add(listForMe.subList(j*189,j*189+189));
            System.out.println("j="+j);
            j++;
        }
        System.out.println("CHNK list :"+CHNK);

        //Iz teksta odstrani--> #  C  H  N  K <00>   S  T  R  T  1  0  0  0  0  #    in   #  C  H  N  K <00>   S  T  P  #
        int k=0;
        while(k<CHNK.size()){
            if(k==0){//prvi element
                ReturnList.addAll(CHNK.get(k).subList(0,178));
                //System.out.println("To remove:"+CHNK.get(k).subList(0,178));
            }else if(k == CHNK.size()-1){//zadnji element
                ReturnList.addAll(CHNK.get(k).subList(17,189));
                //System.out.println("To remove:"+CHNK.get(k).subList(17,189));
            }else{//vsi ostali
                ReturnList.addAll(CHNK.get(k).subList(17,178));
                //System.out.println("To remove:"+CHNK.get(k).subList(17,178));
            }
            //System.out.println("k="+k);
            k++;
        }
        System.out.println(ReturnList.size() + " je velikost. Vsebina: "+ReturnList);
        return ReturnList;
    }
        //spremeni prejeti tekst v List po dva znaka v en element
    public static List<String> textToList(String text){
        System.out.println("TextAnalysis.textToList()");
        List<String> list = new ArrayList<String>();

        String mssg[]= text.split("(?<=\\G..)");            //po 2(število pik) znaka na enkrat da v eno celico tabele

        int i =0;
        while (i<mssg.length){
            list.add(mssg[i]);
            i++;
        }

        return list;
    }

        //umakne znake   ]  [  ,   iz list.toString()
    public static String NicerListString(String list){
        System.out.println("TextAnalysis.NicerListString()");
        String niceList=list.replace(",", "").replace("[", "").replace("]", "");
        return niceList;
    }

}
