package com.expert.JobAppREST.controller;

import com.expert.JobAppREST.model.JobPost;
import com.expert.JobAppREST.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin
public class JobRestController {

    @Autowired
    private JobService service;

    @GetMapping(path = "jobs", produces = {"application/json"})
//    @ResponseBody
    public List<JobPost> getAllJobs() {
        return service.getAllJobs();
    }

    @GetMapping("job/{id}")
    public JobPost getJob(@PathVariable("id") int id) {
        return service.getJob(id);
    }

    @PostMapping(path = "job", consumes = {"application/json"})
    public JobPost addJob(@RequestBody JobPost job) {
        service.addJob(job);
        return service.getJob(job.getPostId());
    }

    @PutMapping("job")
    public JobPost updateJob(@RequestBody JobPost job) {
        service.updateJob(job);
        return service.getJob(job.getPostId());
    }

    @DeleteMapping("job/{id}")
    public String deleteJob(@PathVariable("id") int id) {
        service.deleteJob(id);
        return "Deleted job with id: " + id;
    }

    @GetMapping("load")
    public String load() {
        return service.load();
    }

    @GetMapping("jobs/keyword/{keyword}")
    public List<JobPost> searchJobs(@PathVariable String keyword) {
        return service.searchJobs(keyword);
    }


}
