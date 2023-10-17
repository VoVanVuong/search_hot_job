package edu.vku.searchjob.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "revenue")
public class Revenue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date",columnDefinition ="Date")
    private String date;
    @Column(name = "revenue")
    private double revenue;
    @Column(name = "created_time",columnDefinition = "Datetime")
    private String createdTime;
    @Column(name = "updated_time",columnDefinition = "Datetime")
    private String updatedTime;

    public Revenue() {
    }

    public Revenue(int id, String date, double revenue, String createdTime, String updatedTime) {
        this.id = id;
        this.date = date;
        this.revenue = revenue;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }
}
