package model;

import java.sql.SQLException;

public class CRUD{
    public CRUD() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url =  "jdbc:mysql://localhost:3306/foodly";
        String user = "root";
        String mdp ="";

    }
    public static void main(String[] args){
        try{
            CRUD crud = new CRUD();
            System.out.println("I'm done!");
        }catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
