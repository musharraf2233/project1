package com.servicepack;

import java.net.PasswordAuthentication;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demo.MailConfiguration;
import com.model.Accounts;
import com.repopack.AccountDAO;

public interface AccountService {

	public void createUser(Accounts account);
	
	public List<Accounts> findAll();
	
	public boolean isNameIsUnique(Integer id,String name);
	
	public Accounts findAccountByName(String username);
	
	public List<Accounts> findAccountByPass(String password);
	
	public Accounts findByusernameAndpassword(String username,String password);
	
	public void sendEmail(String mail,String name);
}
@Service
class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountDAO repo;
	
	@Autowired
	private MailConfiguration sendmail;
	

	public AccountDAO getRepo() {
		return repo;
	}



	public void setRepo(AccountDAO repo) {
		this.repo = repo;
	}

	@Transactional
	@Override
	public void createUser(Accounts account) {
		repo.saveEmployee(account);
		
	}
	
	@Transactional
	@Override
	public List<Accounts> findAll() {
		return repo.findAllEmployees();
	}
	@Override
	@Transactional
	public boolean isNameIsUnique(Integer id, String username) {
		Accounts account=repo.findAccountByName(username);
		return ( account == null || ((id != null) && (account.getId() == id)));
	}
	@Override
	@Transactional
	public Accounts findAccountByName(String username) {
		return repo.findAccountByName(username);
	}
	
	@Override
	@Transactional
	public List<Accounts> findAccountByPass(String password) {
		return repo.findAccountByPass(password);
	}
	@Override
	@Transactional
	public Accounts findByusernameAndpassword(String username, String password) {
		return repo.findusernameAndpassword(username, password);
	}
	
	@Override
	public void sendEmail(String mail,String name) {
		final String from="mohamedmusharaf.mm@gmail.com";
		final String password="ogzyohkhhhjkwsee";
		Session session = Session.getDefaultInstance(sendmail.getProperties(),    
		         new javax.mail.Authenticator() {    
		         protected javax.mail.PasswordAuthentication getPasswordAuthentication() {    
		         return new javax.mail.PasswordAuthentication(from,password);  
		         }    
		        });  
		
		MimeMessage mimeMessage = new MimeMessage(session);
		 
        try {
        	mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
        	mimeMessage.setSubject(name);
        	mimeMessage.setText("welcome "+name+", thanks for using shoppingcart");
        	Transport.send(mimeMessage);
        	System.out.println("message send successfully");
          
 
        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}
}
