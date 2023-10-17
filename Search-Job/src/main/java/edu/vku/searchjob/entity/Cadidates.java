package edu.vku.searchjob.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cadidates")
public class Cadidates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "cadidated_name")
    private String cadidatedName;
    @Column(name = "address")
    private String address;
    @Column(name = "gender")
    private Boolean gender;
    @Column(name = "delete_flag")
    private boolean deleteFlag;
    @Column(name = "date_of_birth" ,columnDefinition = "Date")
    private String dateOfBirth;
    @Column(name = "avartar")
    private String avartar;
    @Column(name = "candidate_cv")
    private String candidateCV;
    @Column(name = "created_at", columnDefinition = "Datetime")
    private String createdAt;
    @Column(name = "updated_at", columnDefinition = "Datetime")
    private String updatedAt;

    public Cadidates() {
    }
    public Cadidates(int id, String cadidatedName, String address, Boolean gender, boolean deleteFlag, String dateOfBirth, String avartar, String candidateCV, String createdAt, String updatedAt) {
        this.id = id;
        this.cadidatedName = cadidatedName;
        this.address = address;
        this.gender = gender;
        this.deleteFlag = deleteFlag;
        this.dateOfBirth = dateOfBirth;
        this.avartar = avartar;
        this.candidateCV = candidateCV;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCadidatedName() {
        return cadidatedName;
    }

    public void setCadidatedName(String cadidatedName) {
        this.cadidatedName = cadidatedName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAvartar() {
        return avartar;
    }

    public void setAvartar(String avartar) {
        this.avartar = avartar;
    }

    public String getCandidateCV() {
        return candidateCV;
    }

    public void setCandidateCV(String candidateCV) {
        this.candidateCV = candidateCV;
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
