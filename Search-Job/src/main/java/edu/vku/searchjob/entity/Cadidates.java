package edu.vku.searchjob.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    private String gender;
    @Column(name = "delete_flag")
    private boolean deleteFlag;
    @Column(name = "date_of_birth" ,columnDefinition = "Date")
    private String dateOfBirth;
    @Column(name = "avartar")
    private String avartar;
    @Column(name = "candidate_cv")
    private String candidateCV;
    @Column(name = "phoneNumber")
    private int phoneNumber;
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @Column(name="skill")
    private String skill;
    @Column(name = "work")
    private String work;
    @Column(name = "salary_required")
    private String salaryRequired;
    @Column(name = "categoty_required")
    private String categotyRequired;
    @Column(name = "experience")
    private String experience;
//    @Column(name = " describe")
//    private String  describe;
     @CreationTimestamp
     @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", columnDefinition = "Datetime")
    private String createdAt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", columnDefinition = "Datetime")
    private String updatedAt;

    public Cadidates() {
    }
    public Cadidates(int id, String cadidatedName,String salaryRequired,String categotyRequired,int phoneNumber,String experience ,String work,String skill,String address, String gender, boolean deleteFlag, String dateOfBirth, String avartar, String candidateCV, Account account, String createdAt, String updatedAt) {
        this.id = id;
        this.cadidatedName = cadidatedName;
        this.phoneNumber=phoneNumber;
        this.skill=skill;
        this.work=work;
        this.experience=experience;
        this.address = address;
        this.categotyRequired=categotyRequired;
        this.salaryRequired=salaryRequired;
        this.gender = gender;
        this.deleteFlag = deleteFlag;
        this.dateOfBirth = dateOfBirth;
        this.avartar = avartar;
        this.candidateCV = candidateCV;
        this.account=account;
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

//    public Boolean getGender() {
//        return gender;
//    }
//
//    public void setGender(Boolean gender) {
//        this.gender = gender;
//    }

    public String getSalaryRequired() {
        return salaryRequired;
    }

    public void setSalaryRequired(String salaryRequired) {
        this.salaryRequired = salaryRequired;
    }

    public String getCategotyRequired() {
        return categotyRequired;
    }

    public void setCategotyRequired(String categotyRequired) {
        this.categotyRequired = categotyRequired;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
