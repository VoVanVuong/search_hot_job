package edu.vku.searchjob.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "articles")
public class Articles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

    @Column(name = "decription")
    private String decription;
    @ManyToOne
    @JoinColumn(name = "account_id ")
    private Account account;

    @Column(name = "images")
    private String images;
    @Column(name = "status")
    private String status;
    @Column(name = "views")
    private String views;
    @Column(name = "created_at", columnDefinition = "Datetime")
    private String createdAt;
    @Column(name = "update_at", columnDefinition = "Datetime")
    private String updateAt;
    @Column(name = "delete_flag")
    private boolean deleteFlag;

    public Articles() {
    }

    public Articles(int id, String name, String decription, Account account, String images, String status, String views, String createdAt, String updateAt, boolean deleteFlag) {
        this.id = id;
        this.name = name;
        this.decription = decription;
        this.account = account;
        this.images = images;
        this.status = status;
        this.views = views;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.deleteFlag = deleteFlag;
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

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
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
