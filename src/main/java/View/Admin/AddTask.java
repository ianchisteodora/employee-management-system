package View.Admin;

import DAO.TaskDAO;
import Model.Task;
import View.LoginPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddTask extends JFrame {


    private JPanel panel1;
    private JButton addTaskButton;
    private JTextField taskTextField1;
    private JTextField descrTextField1;
    private JTextField employeeTextField1;
    private JTextField dateTextField1;
    private JButton backButton;
    private JButton exitButton;
    private JButton logoutButton;
    private  JFrame frame;
    private String employeePattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
   // private String datePattern = "^\\d{4}-\\d{2}-\\d{2}$\n";



    public AddTask() {

        frame = new JFrame("task page");
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.add(panel1);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        addTaskButton.setEnabled(false);



        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task task = new Task();
                task.setName(taskTextField1.getText());
                task.setDescription(descrTextField1.getText());
                task.setEmployee(employeeTextField1.getText());
                task.setDueDate(dateTextField1.getText());

                TaskDAO.save(task);

                clear();

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new AdminPage();
            }
        });


        taskTextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                validators();
            }
        });
        descrTextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                validators();
            }
        });
        employeeTextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                validators();
            }
        });
        dateTextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                validators();
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

    public void validators(){
        String employee = employeeTextField1.getText();
        String name = taskTextField1.getText();
        String description = descrTextField1.getText();
        String dueDate = dateTextField1.getText();

        addTaskButton.setEnabled(!name.equals("")  &&!employee.equals(" ") && employee.matches(employeePattern) && !description.equals("") && !dueDate.equals("") );

    }
    public void clear(){
        employeeTextField1.setText("");
        taskTextField1.setText("");
        descrTextField1.setText("");
        dateTextField1.setText("");
    }
}
