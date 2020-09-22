package sample;


import java.sql.*;
import java.util.ArrayList;

//https://www.tutorialspoint.com/sqlite/sqlite_java.htm
public class DatabaseHandler {
    static Connection c = null;
    public static void connect() {

        try {
            Class.forName("org.sqlite.JDBC");
            //jar file --->https://stackoverflow.com/questions/16742085/adding-jar-files-to-intellijidea-classpath
            c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\jakob\\OneDrive\\Desktop\\Jakob\\Programiranje\\Databases\\Projekt_Energetika.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public static void disconnect(){
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertIntoDatabase(String table, String columns, String values){
        try {
            connect();

            Statement stmt = c.createStatement();
            String sql = "INSERT INTO "+table+" ("+columns+") " + "VALUES ("+values+");";
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    public static String [] selectFromDatabase(String table, String columns ,String columnWhere, String columnEquals){//String columns-->ločeni z vejico brez presledkov; String column --> podan column ali null kar pomeni da ni where v stolpcu; String whereEquals --> čemu se primerja podatek iz stolpca
        ArrayList<ArrayList<String>> outputArrayList = new ArrayList<>();
        String out []={};

        try {
            connect();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            Statement stmt = c.createStatement();
            ResultSet rs;


            if(columnWhere==null){
                rs = stmt.executeQuery( "SELECT "+columns+" FROM "+table+";" );
                System.out.println("column=null");
            }else{
                rs = stmt.executeQuery( "SELECT * FROM "+table+" where "+columnWhere+" = "+columnEquals+";" );
                System.out.println("column!=null");
            }
            String [] Columns = columns.split(",");

            int j =0;
            while ( rs.next() ) {
                ArrayList<String> oneRow = new ArrayList<>();
                int i =0;
                while(i<Columns.length){
                    oneRow.add(rs.getString(Columns[i]));
                    i++;
                }
                outputArrayList.add(oneRow);
                //System.out.println(oneRow);
                j++;

            }
            //System.out.println(outputArrayList);
            /*int n=0;
            while (n<j+1){
                outputArrayList.get
            }*/

            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");

        return out;
    }
}

