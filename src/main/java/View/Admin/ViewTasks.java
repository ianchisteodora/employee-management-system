package View.Admin;

import DAO.TaskDAO;
import DAO.UserDAO;
import Model.Task;
import Model.User;
import View.LoginPage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewTasks {

    String [] col = new  String[]{"id", "Name", "Description", "Employee","dueDate", "Status"};
    private JPanel panel1;
    private JTable table1;
    private JButton backButton;
    private JButton logoutButton;
    private JButton exitButton;
    private JFrame frame;


    public ViewTasks() {
        frame = new JFrame("Tasks page");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.add(panel1);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        DefaultTableModel dtm = new DefaultTableModel(col, 0);
        table1.setModel(dtm);

        getTasks();


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new AdminPage();


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
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void getTasks(){
        ArrayList<Task> list = TaskDAO.getAllTasks();
        Object[][] data = new Object[list.size()][6];
        for (int i = 0; i < list.size(); i++) {
            Task taskObj = list.get(i);

                data[i][0] = taskObj.getId();
                data[i][1] = taskObj.getName();
                data[i][2] = taskObj.getDescription();
                data[i][3] = taskObj.getEmployee();
                data[i][4] = taskObj.getDueDate();
                data[i][5] = taskObj.getCompleted();


        }
        // Set the data for the table
        DefaultTableModel dtm = (DefaultTableModel) table1.getModel();
        dtm.setRowCount(0);
        for (Object[] row : data) {
            dtm.addRow(row);
        }
    }
}
