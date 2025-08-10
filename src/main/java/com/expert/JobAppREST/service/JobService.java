package com.expert.JobAppREST.service;

import com.expert.JobAppREST.model.JobPost;
import com.expert.JobAppREST.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepo repo;

    public List<JobPost> getAllJobs() {
        return repo.findAll();
    }

    public JobPost getJob(int id) {
        return repo.findById(id).orElse(null);
    }

    public void addJob(JobPost job) {
        repo.save(job);
    }

    public void deleteJob(int id) {
        repo.deleteById(id);
    }

    public void updateJob(JobPost job) {
        repo.save(job);
    }

    public String load() {

        List<JobPost> jobs = new ArrayList<>(
        Arrays.asList(
                new JobPost(1, "Java Developer", "Must have good experience in core Java and advanced Java", 2,
                        List.of("Core Java", "J2EE", "Spring Boot", "Hibernate")),

                new JobPost(2, "Frontend Developer", "Experience in building responsive web applications using React", 3,
                        List.of("HTML", "CSS", "JavaScript", "React")),

                new JobPost(3, "Data Scientist", "Strong background in machine learning and data analysis", 4,
                        List.of("Python", "Machine Learning", "Data Analysis")),

                new JobPost(4, "Network Engineer", "Design and implement computer networks for efficient data communication", 5,
                        List.of("Networking", "Cisco", "Routing", "Switching")),

                new JobPost(5, "Mobile App Developer", "Experience in mobile app development for iOS and Android", 3,
                        List.of("iOS Development", "Android Development", "Mobile App"))
            )
        );

        repo.saveAll(jobs);

        return "Loaded";
    }

    public List<JobPost> searchJobs(String keyword) {
//        return repo.findByPostProfileContainingOrPostDescContaining(keyword, keyword);
        return repo.searchIgnoreCase(keyword, keyword);
    }
}
