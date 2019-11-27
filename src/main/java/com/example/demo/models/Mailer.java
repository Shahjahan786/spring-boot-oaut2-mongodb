package com.example.demo.models;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController()
public class Mailer {
	
	

	@Autowired
    private JavaMailSender javaMailSender;
	
	
	@GetMapping("/sms/send")
	public boolean sendSMS() {
		//AC80ff0bd2167b1e3827fde71d477b8129
		//da0c19a7ea67f9fec5f89b49034fecee
		
		//Twilio.init("ACec571a56154977f92e4d8609a3d403ae", "16f92354460c3e98e696da0b7e049bda");
		Twilio.init("AC80ff0bd2167b1e3827fde71d477b8129", "da0c19a7ea67f9fec5f89b49034fecee");
//(512) 237-7090
		
        Message message = Message.creator(
                new PhoneNumber("+923173093710"),
                new PhoneNumber("+923048405652"),
               // new PhoneNumber("+15005550006"),
                
                "Sample Twilio SMS using Java")
                .create();
        return message.getStatus() != Message.Status.FAILED;
    }
	
	
	@GetMapping("/mail/send")
	public boolean sendEmail() {
		boolean mailSent = false;
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("samoon.shahjahan@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");
        try {
        	javaMailSender.send(msg);
        	mailSent = true;
        }catch (Exception e) {
        	throw e;
        }

        return mailSent;
    }

    void sendEmailWithAttachment() throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("1@gmail.com");

        helper.setSubject("Testing from Spring Boot");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>Check attachment for image!</h1>", true);

        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

        javaMailSender.send(msg);

    }
	
}
