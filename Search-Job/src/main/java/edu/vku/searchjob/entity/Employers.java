//Caan them lien ket voi user
package edu.vku.searchjob.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employers")
public class Employers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    //nhieu nha tuyen dung tuyen trong 1 thanh pho
    @ManyToOne
    @JoinColumn(name = "cities_id")
    private Cities cities;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "business_paper")
    private String businessPaper;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "decription")
    private String decription;
    @Column(name = "total_job")
    private String totalJob;
    @Column(name = "delete_flag")
    private Boolean deleteFlag;

    @Column(name = "created_at",columnDefinition = "Datetime")
    private String createdAt;
    @Column(name = "UpdatedAt",columnDefinition = "Datetime")
    private String updatedAt;

    public Employers() {
    }

    public Employers(int id, String name, String address, Cities cities, String phoneNumber, String businessPaper, String avatar, String decription, String totalJob, String createdAt, String updatedAt,Boolean deleteFlag) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cities = cities;
        this.phoneNumber = phoneNumber;
        this.businessPaper = businessPaper;
        this.avatar = avatar;
        this.decription = decription;
        this.totalJob = totalJob;
        this.deleteFlag=deleteFlag;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Cities getCities() {
        return cities;
    }

    public void setCities(Cities cities) {
        this.cities = cities;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBusinessPaper() {
        return businessPaper;
    }

    public void setBusinessPaper(String businessPaper) {
        this.businessPaper = businessPaper;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getTotalJob() {
        return totalJob;
    }

    public void setTotalJob(String totalJob) {
        this.totalJob = totalJob;
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

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
