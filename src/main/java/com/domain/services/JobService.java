package com.domain.services;

import com.domain.models.entities.Job;
import com.domain.models.repos.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    @Autowired
    private JobRepo jobRepo;

    public Job getJobById(Long id) {
        return jobRepo.findById(id).orElse(null);
    }
}