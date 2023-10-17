package edu.vku.searchjob.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cities")
public class Cities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "created_at",columnDefinition = "Datetime")
    private String createdAt;
    @Column(name = "updated_at",columnDefinition = "Datetime")
    private String updatedAt;

    public Cities() {
    }

    public Cities(int id, String createdAt, String updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
