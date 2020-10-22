package com.k2view.cdbms.usercode.common;

import java.util.*;
import java.sql.*;
import java.math.*;
import java.io.*;

import com.k2view.cdbms.shared.*;
import com.k2view.cdbms.sync.*;
import com.k2view.cdbms.lut.*;
import com.k2view.cdbms.shared.logging.LogEntry.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailSender {
    /**
     * sending a mail to the specified user
     *
     * @param recepient
     * @throws Exception
     */
    public static void sendMail(String recepient) throws Exception {
        System.out.println("preparing to send Email");
        //property setup
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        //initializing the email credentials
        String myAccountEmail = "zelalemgelgelo@gmail.com";
        String password = "**********";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        Message message = prepareMessage(session, myAccountEmail, recepient);
//send email to the specific user
        Transport.send(message);
        System.out.println("Message sent successfully!");
    }

    /**
     * preparing a message to the specific user
     * @param session
     * @param myAccountEmail
     * @param recepient
     * @return
     */
    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Your fabric storage reaches 50 %");
            message.setText("Hello here is your fabric storage detail. \n Your Fabric storage reaches 50 %");
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
