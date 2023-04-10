package View.Employee;

import DAO.TaskDAO;
import DAO.UserDAO;
import Model.Task;
import Model.User;
import View.Admin.VerifyUsers;
import View.LoginPage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class EmployeePage extends JFrame {
    private JPanel panel1;
    private JButton exitButton1;
    private JTable table1;
    private JButton logoutButton;
    private JFrame frame;
    String [] col = new  String[]{"id", "Name", "Description", "Employee","dueDate", "Status"};
    private String loggedInEmployee;

    public EmployeePage(String loggedInEmployee) {
        this.loggedInEmployee = loggedInEmployee;
        frame = new JFrame("Employee page");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(450, 450);
        frame.add(panel1);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        DefaultTableModel dtm = new DefaultTableModel(col, 0);
        table1.setModel(dtm);

        getTasks(loggedInEmployee);





        exitButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = table1.getSelectedRow();
                TableModel model = table1.getModel();
                String taskId = model.getValueAt(index, 0).toString();
                String status = model.getValueAt(index, 5).toString();
                if(status.equals("true")){
                    status= "false";
                }
                else{
                    status = "true";
                }
                int a = JOptionPane.showConfirmDialog(null, "Do you want to change the status?", "Select", JOptionPane.YES_NO_OPTION);
                if(a==0){
                    TaskDAO.changeStatus(taskId, status);
                    frame.setVisible(false);
                    JFrame frame1 = new EmployeePage(loggedInEmployee);
                    frame1.setLocationRelativeTo(null);
                }



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
    }
    public void getTasks(String loggedInEmployee){
        ArrayList<Task> list = TaskDAO.getTasksForEmployee(loggedInEmployee);
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
