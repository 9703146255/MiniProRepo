package in.thiru.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public boolean sendEmail(String subject,String body,String to)
	{
		boolean isSent=false;
		
		try {
			
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			
			MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);
			
			javaMailSender.send(mimeMessage);
			isSent=true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		return isSent;
	}

}

/*
 @Autowired
	private JavaMailSender mailSender;
	
	public boolean sendEmail(String subject,String to,String body)
	{
		boolean isSent=false;
		
		try {
			MimeMessage mime = mailSender.createMimeMessage();

			MimeMessageHelper helper=new MimeMessageHelper(mime);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);
			
			mailSender.send(mime);
			isSent=true;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return isSent;
	}
	
	*/	
