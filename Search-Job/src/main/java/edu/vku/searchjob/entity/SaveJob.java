package edu.vku.searchjob.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "save_jobs")
public class SaveJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Cadidates candidate;
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Jobs job;
//    @Column(name = "is_status")
//    private boolean isStatus;
@Column(name = "delete_flag")
private Boolean deleteFlag;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at ",columnDefinition = "Datetime")
    private String createdAt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at ",columnDefinition = "Datetime")
    private String updateAt;

    public SaveJob() {
    }

    public SaveJob(int id, Cadidates candidate, Jobs job, boolean deleteFlag, String createdAt, String updateAt) {
        this.id = id;
        this.candidate = candidate;
        this.job = job;
        this.deleteFlag=deleteFlag;
//        this.isStatus = isStatus;
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

//    public boolean isStatus() {
//        return isStatus;
//    }
//
//    public void setStatus(boolean status) {
//        isStatus = status;
//    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
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
}
