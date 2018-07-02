package com.valuequo.buckswise.mail;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.valuequo.buckswise.web.rest.vm.ManagedUserVM;

@Component
public class SendGMailTLS {
	
	@Autowired
	private ManagedUserVM managedUserVM;
	
	

   public String gmail(ManagedUserVM managedUserVM) {


      // Recipient's email ID needs to be mentioned.
      String to = managedUserVM.getEmail();//change accordingly

      // Sender's email ID needs to be mentioned
      String from = "noreply@buckswise.com";//change accordingly
      
      

      final String username = "pratikbalivq@gmail.com";//change accordingly
      final String password = "pratik96";//change accordingly

      // GMail's SMTP server
      String host = "smtp.gmail.com";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "587");
      props.put("mail.smtp.ssl.trust", host);

      // Get the Session object.
      Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
         }
      });
      int randomPin   =(int)(Math.random()*9000)+1000;
  	  String otp  =String.valueOf(randomPin);

      try {
         // Create a default MimeMessage object.
         Message message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO,
         InternetAddress.parse(to));

         // Set Subject: header field
         message.setSubject("Buckswise OTP");

         // Now set the actual message
         message.setText("Hello, thank you for registering with Buckswise \n"
            + "Here is Your OTP :  " + otp);

         // Send message
         Transport.send(message);

         System.out.println("Sent message successfully.... from GMAIL");

      } catch (MessagingException e) {
            throw new RuntimeException(e);
      }
      return otp;
   }
}
