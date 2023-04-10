package DAO;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class EmailSender {
    public static void sendEmail(String recipient, String subject, String messageBody) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.mail.yahoo.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("yourGmailUsername", "yourGmailPassword");
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("your_email_address"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        message.setSubject(subject);
        message.setText(messageBody);


        Transport.send(message);




    }

}
