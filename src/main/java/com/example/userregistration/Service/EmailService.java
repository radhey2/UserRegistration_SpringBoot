package com.example.userregistration.Service;

import com.example.userregistration.DTO.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import com.example.userregistration.Modal.Email;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    private IEmailService emailService;
    @Override
    public ResponseEntity<ResponseDTO> sendMail(Email email) {
        final String fromEmail = "radheshyamb9@gmail.com";
        final String fromPwd = "quamztpjjepxovlk";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // TODO Auto-generated method stub
                return new PasswordAuthentication(fromEmail, fromPwd);
            }
        };
        Session session = Session.getInstance(properties, authenticator);

        MimeMessage mail = new MimeMessage(session);

        try {
            mail.addHeader("Content-type", "text/HTML; charset=UTF-8");
            mail.addHeader("format", "flowed");
            mail.addHeader("Content-Transfer-Encoding", "8bit");

            mail.setFrom(new InternetAddress(fromEmail));
            mail.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
            mail.setText(email.getBody(), "UTF-8");
            mail.setSubject(email.getSubject(), "UTF-8");

            Transport.send(mail);

            ResponseDTO responseDTO = new ResponseDTO(" Sent email ", mail, null);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        ResponseDTO responseDTO = new ResponseDTO(" ERROR: Couldn't send email", null, null);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public String getLink(String token) {
        return "http://localhost:8081/UserRegistration/verifyUser/" + token;
    }
}
