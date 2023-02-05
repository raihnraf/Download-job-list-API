package com.domain.controllers.joblist;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;

@Controller
public class JobListController {

private final String API_URL = "http://dev3.dansmultipro.co.id/api/recruitment/positions.json";

public void getJobListAndConvertToCSV() {
HttpClient httpClient = HttpClientBuilder.create().build();
HttpGet request = new HttpGet(API_URL);
request.addHeader("Accept", "application/json");

try {
    HttpResponse response = httpClient.execute(request);
    
    ObjectMapper objectMapper = new ObjectMapper();
    List<Job> jobList = objectMapper.readValue(response.getEntity().getContent(), objectMapper.getTypeFactory().constructCollectionType(List.class, Job.class));
    
    File file = new File("job_list.csv");
    FileWriter outputfile = new FileWriter(file);
    CSVWriter writer = new CSVWriter(outputfile);
    
    String[] header = {"ID", "Job Title", "Company", "Location", "Salary"};
    writer.writeNext(header);
    
    for (Job job : jobList) {
      String[] data = {String.valueOf(job.getId()), job.getTitle(), job.getCompany(), job.getLocation(), job.getSalary()};
      writer.writeNext(data);
    }
    
    writer.close();
    
  } catch (ClientProtocolException e) {
    e.printStackTrace();
  } catch (IOException e) {
    e.printStackTrace();
  }
}

private class Job {
    private int id;
    private String title;
    private String company;
    private String location;
    private String salary;
    
    public int getId() {
    return id;
    }
    
    public void setId(int id) {
    this.id = id;
    }
    
    public String getTitle() {
    return title;
    }
    
    public void setTitle(String title) {
    this.title = title;
    }
    
    public String getCompany() {
    return company;
    }
    
    public void setCompany(String company) {
    this.company = company;
    }
    
    public String getLocation() {
    return location;
    }
    
    public void setLocation(String location) {
    this.location = location;
    }
    
    public String getSalary() {
    return salary;
    }
    
    public void setSalary(String salary) {
    this.salary = salary;
    }
    }}