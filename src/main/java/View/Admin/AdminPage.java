package View.Admin;

import DAO.TaskDAO;
import Model.Task;
import View.LoginPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AdminPage extends JFrame {
    private JPanel panel1;
    private JButton ebutton1;
    private JButton logoutButton;
    private JButton verifyUsersButton;
    private JButton assignTasksButton;
    private JButton viewTasksButton;
    private JButton reportsButton;
    private JFrame frame;


    public AdminPage(){
        frame = new JFrame("Admin page");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.add(panel1);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        ebutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(null, "Do you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
                if(a == JOptionPane.YES_OPTION) {
                    frame.setVisible(false);
                    new LoginPage();
                }
            }
        });

        verifyUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new VerifyUsers();

            }
        });
        assignTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new AddTask();


            }
        });
        viewTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new ViewTasks();

            }
        });
        reportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CompletedTasksToFile();

            }
        });
    }
    public void CompletedTasksToFile(){
        ArrayList<Task> tasks = TaskDAO.getAllTasks();
        String filename = "completed_tasks.txt";
        try {
            FileWriter writer = new FileWriter(filename);
            for (Task task : tasks) {
                if (task.getCompleted().equals("true")) {
                    writer.write("Task ID: " + task.getId() + "\n");
                    writer.write("Name: " + task.getName() + "\n");
                    writer.write("Description: " + task.getDescription() + "\n");
                    writer.write("Employee: " + task.getEmployee() + "\n");
                    writer.write("Due Date: " + task.getDueDate() + "\n");
                    writer.write("\n");
                }
            }
            writer.close();
            System.out.println("Completed tasks written to file: " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while writing the file: " + e.getMessage());
        }

    }

}
