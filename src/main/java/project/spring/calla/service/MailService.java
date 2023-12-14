package project.spring.calla.service;

import javax.mail.MessagingException;

public interface MailService {

	String sendMail(String mail) throws MessagingException;
}
