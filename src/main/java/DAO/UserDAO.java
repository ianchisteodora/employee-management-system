package DAO;

import Model.User;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

public class UserDAO {

    public static void save(User user) {

        String query = "insert into user(name, email, password, role, status) values('" + user.getName() + "', '" + user.getEmail() + "','" + user.getPassword() + "', '" + user.getRole() + "', 'false')";
        DbOperations.setDataOrDelete(query, "you have successfully registered!! (Wait for admin approval)");
    }

    public static User login(String email, String password){
        User user = null;
        try {
            Connection con = ConnectionDAO.getCon();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM user WHERE email = ? AND password = ?");
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setIduser(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
                user.setStatus(resultSet.getString("status"));
            }

            con.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return user;
    }

    public static ArrayList<User> getAllRecords(String email){
        ArrayList<User> arrayList = new ArrayList<>();
        try{
            ResultSet rs = DbOperations.getData("select * from user where email like '%"+email+"%'");
            while(rs.next()){
                User user = new User();
                user.setIduser(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getString("status"));
                arrayList.add(user);

            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }

    public static void changeStatus(String email, String status){
        String query = "update user set status='"+status+"' where email='"+email+"'";
        DbOperations.setDataOrDelete(query, "Status changed");


    }


}
