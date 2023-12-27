package example.springProject.batch.verify;

import example.springProject.models.User;
import example.springProject.repository.UserRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@Component
public class UserVerificationReader implements ItemReader<User> {

	@Autowired
    private UserRepository userRepository;
    
    private Iterator<User> userIterator;

    @Override
    public User read() {
        if (userIterator == null || !userIterator.hasNext()) {
            // Load unverified users if iterator is null or exhausted
            loadUnverifiedUsers();
        }

        // Return the next user from the iterator
        return userIterator.hasNext() ? userIterator.next() : null;
    }

    private void loadUnverifiedUsers() {
        try {
            List<User> unverifiedUsers = userRepository.findByEnabled(false);
            userIterator = unverifiedUsers.iterator();
        } catch (Exception e) {
            // Log the error or throw it for handling in another layer
            e.printStackTrace();
        }
    }
}
