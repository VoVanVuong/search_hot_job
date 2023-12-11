//package edu.vku.searchjob.entity;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "job_details")
//public class JobDetails {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;
//    @ManyToOne
//    @JoinColumn(name = "job_id")
//    private Jobs jobs;
//    @Column(name = "desription")
//    private String desription;
////    @Column(name = "require")
////    private String require;
//    @Column(name = "benefit")
//    private String benefit;
//    @Column(name = "deadline",columnDefinition = "Datetime")
//    private String deadline;
//    @Column(name = "salary")
//    private String salary;
//    @Column(name = "delete_flag")
//    private Boolean deleteFlag;
//    @Column(name = "created_at")
//    @Temporal(TemporalType.TIMESTAMP)
//    private String createdAt;
//    @Column(name = "updated_at")
//    @Temporal(TemporalType.TIMESTAMP)
//    private String updated_at;
//
//    public JobDetails() {
//    }
//
//    public JobDetails(int id, Jobs jobs, String desription,  String benefit, String deadline, String salary, Boolean deleteFlag, String createdAt, String updated_at) {
//        this.id = id;
//        this.jobs = jobs;
//        this.desription = desription;
////        this.require = require;
//        this.benefit = benefit;
//        this.deadline = deadline;
//        this.salary = salary;
//        this.deleteFlag = deleteFlag;
//        this.createdAt = createdAt;
//        this.updated_at = updated_at;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public Jobs getJobs() {
//        return jobs;
//    }
//
//    public void setJobs(Jobs jobs) {
//        this.jobs = jobs;
//    }
//
//    public String getDesription() {
//        return desription;
//    }
//
//    public void setDesription(String desription) {
//        this.desription = desription;
//    }
//
////    public String getRequire() {
////        return require;
////    }
////
////    public void setRequire(String require) {
////        this.require = require;
////    }
//
//    public String getBenefit() {
//        return benefit;
//    }
//
//    public void setBenefit(String benefit) {
//        this.benefit = benefit;
//    }
//
//    public String getDeadline() {
//        return deadline;
//    }
//
//    public void setDeadline(String deadline) {
//        this.deadline = deadline;
//    }
//
//    public String getSalary() {
//        return salary;
//    }
//
//    public void setSalary(String salary) {
//        this.salary = salary;
//    }
//
//    public Boolean getDeleteFlag() {
//        return deleteFlag;
//    }
//
//    public void setDeleteFlag(Boolean deleteFlag) {
//        this.deleteFlag = deleteFlag;
//    }
//
//    public String getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(String createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public String getUpdated_at() {
//        return updated_at;
//    }
//
//    public void setUpdated_at(String updated_at) {
//        this.updated_at = updated_at;
//    }
//}
