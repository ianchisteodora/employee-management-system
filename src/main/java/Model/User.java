package Model;

import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private String email;
    private String password;
    private String role;
    private int iduser;
    private String name;
    private String status;

    public User(String email, String password, String name, String role){
        this.email = email;
        this.password = password;
        this.role = role;
        this.name = name;
    }

    public User() {

    }

/*    public static String encryptPassword(String password) {
        String encryptedPassword = ""; // initialize empty string
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            encryptedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        // add this print statement to check if the password is encrypted or not
        System.out.println("Encrypted password: " + encryptedPassword);

        return encryptedPassword;
    }
    public static boolean verifyPassword(String password, String hashedPassword) {
        String encryptedPassword = encryptPassword(password);
        return encryptedPassword.equals(hashedPassword);
    }*/

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", iduser=" + iduser +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
