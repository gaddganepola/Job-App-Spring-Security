package com.expert.JobAppREST.repo;

import com.expert.JobAppREST.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<JobPost, Integer> {

    List<JobPost> findByPostProfileContainingOrPostDescContaining(String profile, String desc);

    @Query("SELECT j FROM JobPost j WHERE LOWER(j.postProfile) LIKE LOWER(CONCAT('%', :profile, '%')) OR LOWER(j.postDesc) LIKE LOWER(CONCAT('%', :desc, '%'))")
    List<JobPost> searchIgnoreCase(@Param("profile") String profile, @Param("desc") String desc);

}

//List<JobPost> jobs = new ArrayList<>(
//        Arrays.asList(
//
//                new JobPost(1, "Java Developer", "Must have good experience in core Java and advanced Java", 2,
//                        List.of("Core Java", "J2EE", "Spring Boot", "Hibernate")),
//
//
//                new JobPost(2, "Frontend Developer", "Experience in building responsive web applications using React", 3,
//                        List.of("HTML", "CSS", "JavaScript", "React")),
//
//
//                new JobPost(3, "Data Scientist", "Strong background in machine learning and data analysis", 4,
//                        List.of("Python", "Machine Learning", "Data Analysis")),
//
//
//                new JobPost(4, "Network Engineer", "Design and implement computer networks for efficient data communication", 5,
//                        List.of("Networking", "Cisco", "Routing", "Switching")),
//
//
//                new JobPost(5, "Mobile App Developer", "Experience in mobile app development for iOS and Android", 3,
//                        List.of("iOS Development", "Android Development", "Mobile App"))
//        )
//);
//public List<JobPost> getAllJobs() {
//    return jobs;
//}
//
//public JobPost getJob(int id) {
//
//    for(JobPost job : jobs) {
//        if(job.getPostId() == id) {
//            return job;
//        }
//    }
//    return null;
//}
//
//public void addJob(JobPost job) {
//    jobs.add(job);
//}
//
//public void deleteJob(int id) {
//    jobs.removeIf(job -> job.getPostId() == id);
//}
//
//public void updateJob(JobPost job) {
//
//    for(JobPost j : jobs) {
//        if(j.getPostId() == job.getPostId()) {
//            j.setPostProfile(job.getPostProfile());
//            j.setPostDesc(job.getPostDesc());
//            j.setReqExperience(job.getReqExperience());
//            j.setPostTechStack(job.getPostTechStack());
//        }
//    }
//}
