//package example.springProject.batch.verify;
//
//import example.springProject.models.User;
//import example.springProject.repository.UserRepository;
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import jakarta.transaction.Transactional;
//
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//
//import java.io.UnsupportedEncodingException;
//import java.time.LocalDateTime;
//
//@Component
//public class UserVerificationProcessor implements ItemProcessor<User, User> {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Override
//    public User process(User user) throws Exception {
//            sendVerificationExpiredNotification(user);
//            userRepository.delete(user);
//            return null;
//    }
//
//    @Transactional
//  	private void sendVerificationExpiredNotification(User user)
//	        throws MessagingException, UnsupportedEncodingException {
//	    String toAddress = user.getEmail();
//	    String fromAddress = "thienthanxa20@gmail.com";
//	    String senderName = "Vu Duc";
//	    String subject = "Please verify your registration";
//	    String content = "Dear [[name]],<br>"
//	            + "Mã xác thực đã hết hạn:<br>"
//	            + "Thank you,<br>"
//	            + "Vu Duc.";
//	     
//	    MimeMessage message = mailSender.createMimeMessage();
//	    MimeMessageHelper helper = new MimeMessageHelper(message);
//	     
//	    helper.setFrom(fromAddress, senderName);
//	    helper.setTo(toAddress);
//	    helper.setSubject(subject);
//	     
//	    content = content.replace("[[name]]", user.getUsername());
//	    helper.setText(content, true);    
//	    mailSender.send(message);
//	     
//	}
//}
