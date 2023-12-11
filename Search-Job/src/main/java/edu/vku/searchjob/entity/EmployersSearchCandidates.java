package edu.vku.searchjob.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "employers_search_candidates")
public class EmployersSearchCandidates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @Column(name = "name")
//    private String name;
    @ManyToOne
    @JoinColumn(name = "cadidate_id")
    private Cadidates cadidates;
    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employers employers;

    @Column(name = "delete_flag")
    private Boolean deleteFlag;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "statusInterview")
    private Boolean statusInterview;
    @Column(name = "interview_time",columnDefinition = "Datetime")
    private String interviewTime;
    @Column(name = "interview_address")
    private String interviewAddress;
    @Column(name = "note")
    private String note;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at",columnDefinition = "Datetime")
    private String createdAt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UpdatedAt",columnDefinition = "Datetime")
    private String updatedAt;

    public EmployersSearchCandidates() {
    }

    public EmployersSearchCandidates(int id,  Cadidates cadidates,Employers employers,boolean status,String interviewTime,String interviewAddress,Boolean statusInterview,String note, Boolean deleteFlag, String createdAt, String updatedAt) {
        this.id = id;
//        this.name = name;
        this.cadidates = cadidates;
        this.employers=employers;
        this.statusInterview=statusInterview;
        this.status=status;
        this.interviewAddress=interviewAddress;
        this.interviewTime=interviewTime;
        this.note=note;
        this.deleteFlag = deleteFlag;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public Cadidates getCadidates() {
        return cadidates;
    }

    public void setCadidates(Cadidates cadidates) {
        this.cadidates = cadidates;
    }

    public Employers getEmployers() {
        return employers;
    }

    public void setEmployers(Employers employers) {
        this.employers = employers;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(String interviewTime) {
        this.interviewTime = interviewTime;
    }

    public String getInterviewAddress() {
        return interviewAddress;
    }

    public void setInterviewAddress(String interviewAddress) {
        this.interviewAddress = interviewAddress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Boolean getStatusInterview() {
        return statusInterview;
    }

    public void setStatusInterview(Boolean statusInterview) {
        this.statusInterview = statusInterview;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
