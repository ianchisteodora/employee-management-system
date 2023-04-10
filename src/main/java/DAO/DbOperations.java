package DAO;


import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbOperations {

    public static void setDataOrDelete(String query, String message){
        try{
            Connection con = ConnectionDAO.getCon();
            Statement st = con.createStatement();
            st.executeUpdate(query);
            if(!message.equals("")){
                JOptionPane.showMessageDialog(null, message);
            }

        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static ResultSet getData(String query){
        try{
            Connection con = ConnectionDAO.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            return rs;

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }


}
