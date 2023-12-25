//package example.springProject.batch.verify;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//@Configuration
//@EnableScheduling
//public class UserVerificationScheduler {
//
//    private final UserVerificationJobLauncher userVerificationJobLauncher;
//
//    public UserVerificationScheduler(UserVerificationJobLauncher userVerificationJobLauncher) {
//        this.userVerificationJobLauncher = userVerificationJobLauncher;
//    }
//
//    @Scheduled(fixedRate = 30000) // 3 minutes
//    public void runUserVerificationJob() {
//        userVerificationJobLauncher.runJob();
//    }
//}

