package View;

import DAO.UserDAO;
import Model.User;
import View.Admin.AdminPage;
import View.Employee.EmployeePage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class LoginPage extends JFrame{
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JTextField UserTextField;
    private JButton registerButton;
    private JButton loginButton;
    private JPasswordField passwordField1;
    private JButton clearButton1;
    private  JFrame loginframe;
    public String emailPattern ="^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";


    public static void main(String[] args) {
        new LoginPage();


    }

    public LoginPage(){
        loginframe = new JFrame("Login page");
        loginframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
        loginframe.setSize(400, 400);
        loginframe.add(panel2);
        loginframe.setLocationRelativeTo(null);
        loginframe.setVisible(true);
        loginButton.setEnabled(false);





        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginframe.setVisible(false);
                RegistrationForm regForm = new RegistrationForm();




            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = UserTextField.getText();
                String password = passwordField1.getText();
                User user = null;
                user = UserDAO.login(email, password);


                if (user == null) {
                    JOptionPane.showMessageDialog(null, "Incorrect username or password");
                } else {
                    if(user.getStatus().equals("false")){
                        JOptionPane.showMessageDialog(null, "wait for approval");
                    }
                    if(user.getStatus().equals("true")){
                        if( user.getRole() != null && user.getRole().equals("admin")){
                            loginframe.setVisible(false);
                            AdminPage adminPage = new AdminPage();

                        }
                        if(user.getRole() != null && user.getRole().equals("employee")){
                            loginframe.setVisible(false);
                            EmployeePage employeePage = new EmployeePage(user.getEmail());

                        }


                    }


                }

            }
        });

        passwordField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginButton.doClick();
                }
            }
        });



        UserTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                validators();
            }
        });
        passwordField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                validators();
            }
        });
        clearButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserTextField.setText("");
                passwordField1.setText("");
            }
        });
    }
    public void validators(){
        String email = UserTextField.getText();
        String password = passwordField1.getText();

        loginButton.setEnabled(!email.equals(" ") && email.matches(emailPattern) && !password.equals("") );


    }



}