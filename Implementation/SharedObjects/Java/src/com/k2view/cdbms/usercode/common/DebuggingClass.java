package com.k2view.cdbms.usercode.common;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

public class DebuggingClass {
    //////////////////////////////////exercise////////////////////////////////
    public static void fabricStorageFunction() throws Exception {
        String folderLocation= "C:\\Users\\ZelalemZergawGelgelo\\Documents\\K2View Fabric Studio\\FinalAssignment\\FabricHome\\storage";

        File folderDirectory = new File(folderLocation);

        if(!(folderDirectory.exists())){
            System.out.println("The directory doesn't exist");
        }


        Long storageTotalSpace=folderDirectory.getTotalSpace();

        Long storageFreeSpace=folderDirectory.getUsableSpace();

        if(storageFreeSpace>storageTotalSpace/2){
            //invoke the send email method
            JavaMailSender.sendMail("zola145103@gmail.com");
        }
    }
    //////////////////////////////exercise///////////////////////////////////

    public static void sendMail(String recepient) throws Exception {
        System.out.println("preparing to send Email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "zelalemgelgelo@gmail.com";
        String password = "0462250353";

        Session session =  Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(myAccountEmail, password);

            }
        });
        Message message= prepareMessage(session,myAccountEmail, recepient);

        Transport.send(message);
        System.out.println("Message sent successfully!");
    }
    private static Message prepareMessage(Session session,String myAccountEmail,String recepient) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
            message.setSubject("Your fabric storage reaches 50%");
            message.setText("Hello here is your fabric storage detail \n");
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) throws Exception {
        DebuggingClass.fabricStorageFunction();
    }
}
