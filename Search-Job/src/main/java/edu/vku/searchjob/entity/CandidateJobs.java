package edu.vku.searchjob.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "candidate_jobs")
public class CandidateJobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //Nhieefu unwsg vien 1 cong viec
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Cadidates candidate;

    //1 job nhieefu coong viec ung vien
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Jobs job;

    //ungs vien lam cong viec co the laf nhieu ung vien trong 1 lan thue(Sai bởi gì kiếm qua công việc)
//    @ManyToOne
//    @JoinColumn(name = "employer_id")
//    private Employers employer;
    @Column(name = "created_at ",columnDefinition = "Datetime")
    private String createdAt;
    @Column(name = "update_at ",columnDefinition = "Datetime")
    private String updateAt;

    public CandidateJobs() {
    }

    public CandidateJobs(int id, Cadidates candidate, Jobs job,  String createdAt, String updateAt) {
        this.id = id;
        this.candidate = candidate;
        this.job = job;
//        this.employer = employer;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cadidates getCandidate() {
        return candidate;
    }

    public void setCandidate(Cadidates candidate) {
        this.candidate = candidate;
    }

    public Jobs getJob() {
        return job;
    }

    public void setJob(Jobs job) {
        this.job = job;
    }

//    public Employers getEmployer() {
//        return employer;
//    }
//
//    public void setEmployer(Employers employer) {
//        this.employer = employer;
//    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
}
