package DAO;

import javax.swing.*;

public class Table {
    public static void main(String [] args){
        try{
            //String userTable = "create table user(id int AUTO_INCREMENT primary key,name varchar(200),email varchar(200),password varchar(200),role varchar(100),status varchar(20),UNIQUE (email)) ";
            //String admin = "insert into user(name, email, password, role, status) values ('Admin', 'admin@gmail.com', 'admin', 'admin', 'true')";
            //DbOperations.setDataOrDelete(userTable, "User Table Created Successfully");
            //DbOperations.setDataOrDelete(admin, "Admin details added");
            String taskTable =  "create table task(id int AUTO_INCREMENT primary key,name varchar(200),description varchar(200),employee varchar(200),dueDate date,status varchar(20)) ";
            DbOperations.setDataOrDelete(taskTable, "Task Table Created Successfully");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }

    }

}
