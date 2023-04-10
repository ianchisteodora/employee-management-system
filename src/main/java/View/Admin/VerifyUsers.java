package View.Admin;

import DAO.UserDAO;
import Model.User;
import View.LoginPage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.*;
import java.util.ArrayList;


public class VerifyUsers extends JFrame{
    private JPanel panel1;
    private JTextField searchField;
    private JTable table1;
    private JButton exitButton;
    private JButton logoutButton;
    private JButton backButton;
    private JFrame frame;
    private JScrollPane scroll;

    String [] col = new  String[]{"id", "Name", "Email", "Role", "Status"};

    public  VerifyUsers(){
        frame = new JFrame("verify page");
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.add(panel1);
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        DefaultTableModel dtm = new DefaultTableModel(col, 0);
        table1.setModel(dtm);

        getAllRecords("");




        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String email = searchField.getText();
                getAllRecords(email);
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = table1.getSelectedRow();
                TableModel model = table1.getModel();
                String email = (String) model.getValueAt(index, 2);
                String status = (String) model.getValueAt(index, 4);
                if(status.equals("true")){
                    status= "false";
                }
                else{
                    status = "true";
                }
            int a = JOptionPane.showConfirmDialog(null, "Do you want to change the status?", "Select", JOptionPane.YES_NO_OPTION);
                if(a==0){
                    UserDAO.changeStatus(email, status);
                    frame.setVisible(false);
                    JFrame frame1 = new VerifyUsers();
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
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new AdminPage();
            }
        });
    }

    public  void getAllRecords(String email){
        ArrayList<User> list = UserDAO.getAllRecords(email);
        Object[][] data = new Object[list.size()][5];
        for (int i = 0; i < list.size(); i++) {
            User userObj = list.get(i);
            if (!userObj.getEmail().equals("admin@gmail.com")) {
                data[i][0] = userObj.getIduser();
                data[i][1] = userObj.getName();
                data[i][2] = userObj.getEmail();
                data[i][3] = userObj.getRole();
                data[i][4] = userObj.getStatus();
            }
        }

        // Set the data for the table
        DefaultTableModel dtm = (DefaultTableModel) table1.getModel();
        dtm.setRowCount(0);
        for (Object[] row : data) {
            dtm.addRow(row);
        }
    }

   private void formComponentShown(ComponentEvent evt){
        getAllRecords("");
    }



}
