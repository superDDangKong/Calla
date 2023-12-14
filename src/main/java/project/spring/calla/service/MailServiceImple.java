package project.spring.calla.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImple implements MailService{

	@Autowired   //context-mail에서 빈 등록했기 때문에 주입받을 수 있다. Spring에서 제공하는 MailSender. 
    private JavaMailSenderImpl mailSender;
	
	
	public String sendMail(String mail) throws MessagingException {
		String emailCheckCode = String.valueOf((int)(Math.random() * (999999)));
		MimeMessage mailMessage = mailSender.createMimeMessage();
		String mailContent = "인증번호 : " + emailCheckCode; // 보낼 이메일 내용
		mailMessage.setSubject("Goott mall 회원가입 이메일 인증", "UTF-8"); // 메일 제목
		mailMessage.setText(mailContent, "UTF-8");
		mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));  
		mailSender.send(mailMessage);
		return emailCheckCode;
	}
}
