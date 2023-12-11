package edu.vku.searchjob.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
//    @Column(name = "slug")
//    private String slug;
//    @Column(name = "total_job")
//    private int totalJob;
//    @Column(name = "salary")
//    private Double salary;
    @Column(name = "dalete_flag")
    private Boolean deleteFlag;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at ",columnDefinition = "Datetime")
    private String createdAt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at ",columnDefinition = "Datetime")
    private String updateAt;

    public Categories() {
    }

    public Categories(int id, String name,Boolean deleteFlag, String createdAt, String updateAt) {
        this.id = id;
        this.name = name;
//        this.slug = slug;
////        this.totalJob = totalJob;
//        this.salary = salary;
        this.deleteFlag=deleteFlag;
        this.createdAt = createdAt;
        this.updateAt = updateAt;

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

//    public String getSlug() {
//        return slug;
//    }
//
//    public void setSlug(String slug) {
//        this.slug = slug;
//    }

//    public int getTotalJob() {
//        return totalJob;
//    }
//
//    public void setTotalJob(int totalJob) {
//        this.totalJob = totalJob;
//    }

//    public Double getSalary() {
//        return salary;
//    }
//
//    public void setSalary(Double salary) {
//        this.salary = salary;
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

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
