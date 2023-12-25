//package example.springProject.batch.verify;
//
//import example.springProject.models.User;
//import example.springProject.repository.UserRepository;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Component
//public class UserVerificationReader implements ItemReader<User> {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private int nextIndex = 0;
//
//    @Override
//    public User read() {
//    	try {
//			List<User> unverifiedUsers = userRepository.findByEnabled(false);
//			
//			 if (nextIndex < unverifiedUsers.size()) {
//		            return unverifiedUsers.get(nextIndex++);
//		        } else {
//		            return null;
//		        }
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	return null;
//    }
//}
