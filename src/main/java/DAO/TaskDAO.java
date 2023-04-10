package DAO;

import Model.Task;
import Model.User;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TaskDAO {

    public static void save(Task task) {

        String query = "insert into task(name, description, employee, dueDate, status) values('" + task.getName() + "', '" + task.getDescription() + "','" + task.getEmployee() + "', '" + task.getDueDate() + "', 'false')";
        DbOperations.setDataOrDelete(query, "Task added!! ");
    }

    public static ArrayList<Task> getAllTasks(){
        ArrayList<Task> arrayList = new ArrayList<>();
        try{
            ResultSet rs = DbOperations.getData("select * from task");
            while(rs.next()){
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setName(rs.getString("name"));
                task.setDescription(rs.getString("description"));
                task.setEmployee(rs.getString("employee"));
                task.setDueDate(rs.getString("dueDate"));
                task.setCompleted(rs.getString("status"));
                arrayList.add(task);

            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }

    public static void changeStatus(String email, String status){
        String query = "update task set status='"+status+"' where id='"+email+"'";
        DbOperations.setDataOrDelete(query, "task completed");


    }
    public static ArrayList<Task> getTasksForEmployee(String employee){
        ArrayList<Task> arrayList = new ArrayList<>();
        try{
            ResultSet rs = DbOperations.getData("select * from task where employee='" + employee + "'");
            while(rs.next()){
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setName(rs.getString("name"));
                task.setDescription(rs.getString("description"));
                task.setEmployee(rs.getString("employee"));
                task.setDueDate(rs.getString("dueDate"));
                task.setCompleted(rs.getString("status"));
                arrayList.add(task);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
}
