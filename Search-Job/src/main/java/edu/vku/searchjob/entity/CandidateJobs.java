package edu.vku.searchjob.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "candidate_jobs")
public class CandidateJobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //Nhieefu unwsg vien 1 cong viec
//    @ManyToOne
//    @JoinColumn(name = "candidate_id")
//    private Cadidates candidate;

//    @ManyToOne
//    @JoinColumn(name = "account_id")
//    private Account account;
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Cadidates candidate;
    //1 job nhieefu coong viec ung vien
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Jobs job;
    @Column(name = "is_status")
    private boolean isStatus;
    @Column(name = "cart_job")
    private boolean cartJob;
    // ungs vien lam cong viec co the laf nhieu ung vien trong 1 lan thue(Sai bởi vì kiếm qua công việc)
//    @ManyToOne
//    @JoinColumn(name = "employer_id")
//    private Employers employer;
    @Column(name = "name_candidate")
    private String nameCandidate;
    @Column(name = "email_candidate")
    private String emailUserApply;
    @Column(name = "phone")
    private String phone;
//    @Lob
//    @Column(name = "avatar_data", columnDefinition = "MEDIUMBLOB")
//    private byte[] avatarData;
    @Column(name = "cv")
    private String cv;
    @Column(name = "note")
    private String note;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at ",columnDefinition = "Datetime")
    private String createdAt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at ",columnDefinition = "Datetime")
    private String updateAt;

    public CandidateJobs() {
    }

    public CandidateJobs(int id,Cadidates candidate,String phone,String note,String nameCandidate,String emailUserApply, String cv, Jobs job,boolean isStatus, boolean cartJob,  String createdAt, String updateAt) {
        this.id = id;
        this.candidate = candidate;
      //  this.account=account;
this.nameCandidate=nameCandidate;
this.emailUserApply=emailUserApply;
this.cv=cv;
this.note=note;
this.phone=phone;
        this.job = job;
//        this.employer = employer;
        this.isStatus=isStatus;
        this.cartJob=cartJob;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public Cadidates getCandidate() {
//        return candidate;
//    }
//
//    public void setCandidate(Cadidates candidate) {
//        this.candidate = candidate;
//    }


//    public Account getAccount() {
//        return account;
//    }
//
//    public void setAccount(Account account) {
//        this.account = account;
//    }

    public String getNameCandidate() {
        return nameCandidate;
    }

    public void setNameCandidate(String nameCandidate) {
        this.nameCandidate = nameCandidate;
    }

    public String getEmailUserApply() {
        return emailUserApply;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setEmailUserApply(String emailUserApply) {
        this.emailUserApply = emailUserApply;
    }

//    public byte[] getAvatarData() {
//        return avatarData;
//    }
//
//    public void setAvatarData(byte[] avatarData) {
//        this.avatarData = avatarData;
//    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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

    public boolean isStatus() {
        return isStatus;
    }

    public void setStatus(boolean status) {
        isStatus = status;
    }

    public Cadidates getCandidate() {
        return candidate;
    }

    public boolean isCartJob() {
        return cartJob;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public void setCartJob(boolean cartJob) {
        this.cartJob = cartJob;
    }

    public void setCandidate(Cadidates candidate) {
        this.candidate = candidate;
    }
}
