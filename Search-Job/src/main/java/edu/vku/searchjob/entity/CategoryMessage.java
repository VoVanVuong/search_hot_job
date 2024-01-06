package edu.vku.searchjob.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "categories_message")
public class CategoryMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Cadidates candidates;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employers employer;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at ",columnDefinition = "Datetime")
    private String createdAt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at ",columnDefinition = "Datetime")
    private String updateAt;

    @Column(name="delete_flag")
    private boolean deleteFlag;

    public CategoryMessage() {
    }

    public CategoryMessage(int id, Cadidates candidates, Employers employer, String createdAt, String updateAt,boolean deleteFlag) {
        this.id = id;
        this.candidates = candidates;
        this.employer = employer;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.deleteFlag=deleteFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cadidates getCandidates() {
        return candidates;
    }

    public void setCandidates(Cadidates candidates) {
        this.candidates = candidates;
    }

    public Employers getEmployer() {
        return employer;
    }

    public void setEmployer(Employers employer) {
        this.employer = employer;
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

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
