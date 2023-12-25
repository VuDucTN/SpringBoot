//package example.springProject.batch.verify;
//
//
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
////@EnableScheduling
//public class UserVerificationJobLauncher {
//
//    @Autowired
//    private JobLauncher jobLauncher;
//
//    @Autowired
//    private Job userVerificationJob; // Đây là Job bạn đã định nghĩa trong UserVerificationBatchConfig
//
////    @Scheduled(fixedDelay = 30000)
//    public void runJob() {
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addLong("time", System.currentTimeMillis())
//                .toJobParameters();
//
//        try {
//            JobExecution jobExecution = jobLauncher.run(userVerificationJob, jobParameters);
//
//            // Kiểm tra trạng thái của JobExecution
//            if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
//                // Thực hiện các hành động khi Job chạy thành công
//            } else {
//                // Thực hiện các hành động khi Job chạy không thành công
//            }
//        } catch (Exception e) {
//            // Xử lý exception nếu cần thiết
//        }
//    }
//}
