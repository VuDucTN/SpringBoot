package example.springProject.service;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//
//import example.springProject.batch.verify.UserVerificationJobLauncher;
//import example.springProject.batch.verify.UserVerificationScheduler;
import example.springProject.models.User;
import example.springProject.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import net.bytebuddy.utility.RandomString;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;
  
  @Autowired
  private JavaMailSender mailSender;
  
//  @Autowired
//  private UserVerificationJobLauncher userVerificationJobLauncher;
//  
//  @Autowired
//  private UserVerificationScheduler userVerificationScheduler;
 

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    return UserDetailsImpl.build(user);
  }
  
  @Transactional
  public void register(User user, String siteURL)
	        throws UnsupportedEncodingException, MessagingException {
	    String randomCode = RandomString.make(64);
	    user.setVerificationCode(randomCode);
	    user.setEnabled(false);
	     
	    userRepository.save(user);
	     
	    sendVerificationEmail(user, siteURL);
	}
  
  	@Transactional
  	private void sendVerificationEmail(User user, String siteURL)
	        throws MessagingException, UnsupportedEncodingException {
	    String toAddress = user.getEmail();
	    String fromAddress = "thienthanxa20@gmail.com";
	    String senderName = "Vu Duc";
	    String subject = "Please verify your registration";
	    String content = "Dear [[name]],<br>"
	            + "Please click the link below to verify your registration:<br>"
	            + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
	            + "Thank you,<br>"
	            + "Vu Duc.";
	     
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom(fromAddress, senderName);
	    helper.setTo(toAddress);
	    helper.setSubject(subject);
	     
	    content = content.replace("[[name]]", user.getUsername());
	    String verifyURL = siteURL + "/api/auth/verify?code=" + user.getVerificationCode();
	     
	    content = content.replace("[[URL]]", verifyURL);
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);
	     
	}
  	
  	
  	public boolean verify(String code) {
  	    User user = userRepository.findByVerificationCode(code);
  	     
  	    if (user == null || user.isEnabled()) {
  	        return false;
  	    } else {
  	        user.setVerificationCode(null);
  	        user.setEnabled(true);
  	        userRepository.save(user);
  	         
  	        return true;
  	    }
  	     
  	}
  
}
