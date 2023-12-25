package example.springProject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import example.springProject.models.User;
import java.time.LocalDateTime;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
  
  @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
  User findByVerificationCode(String verificationCode);
  
  List<User> findByEnabled(boolean enabled);
}