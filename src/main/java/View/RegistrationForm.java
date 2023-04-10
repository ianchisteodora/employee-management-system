package View;

import javax.swing.*;
import java.awt.event.*;

import Model.User;
import DAO.UserDAO;
import DAO.EmailSender;

import static DAO.EmailSender.sendEmail;


public class RegistrationForm extends JFrame {
    private JTextField EmailTextField1;
    private JPanel panelReg;
    private JPasswordField passwordField1;
    private JButton registerButton;
    private JTextField RegTextField;
    private JTextField nametextField1;
    private JButton backButton1;
    private JButton clearButton1;
    private JFrame regFrame;

    public String emailPattern ="^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";



    public RegistrationForm(){
        regFrame = new JFrame("Registration page");
        regFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        regFrame.setSize(450, 450);
        regFrame.setLocationRelativeTo(null);
        regFrame.add(panelReg);
        regFrame.setVisible(true);
        registerButton.setEnabled(false);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setName(nametextField1.getText());
                user.setEmail(EmailTextField1.getText());
                user.setPassword(passwordField1.getText());
                user.setRole(RegTextField.getText());

                UserDAO.save(user);

/*                String recipientEmail = EmailTextField1.getText();
                String subject = "Registration Confirmation";
                String content = "Thank you for registering!";
                sendEmail(recipientEmail, subject, content);*/



                clear();

            }
        });
        nametextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                validators();
            }
        });


        EmailTextField1.addKeyListener(new KeyAdapter() {
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
        RegTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                validators();
            }
        });
        backButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regFrame.setVisible(false);
                new LoginPage();
            }
        });
        clearButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();

            }
        });

    }
    public void validators(){
        String name = nametextField1.getText();
        String email = EmailTextField1.getText();
        String password = passwordField1.getText();
        String role = RegTextField.getText();

        registerButton.setEnabled(!name.equals("") && !email.equals(" ") && email.matches(emailPattern) && !password.equals("") && !role.equals(""));

    }
    public void clear(){
        EmailTextField1.setText("");
        nametextField1.setText("");
        passwordField1.setText("");
        RegTextField.setText("");
    }


}
