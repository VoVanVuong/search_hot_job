package edu.vku.searchjob.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "content")
    private String content;

    //nhieu conment co the duoc comment oi 1 tai khoan
    //nhieu comment trong 1 bai bao
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Articles article;

    // parent_id ang like
    @Column(name = "delete_flag")
    private Boolean deleteFlag;
    @Column(name = "created_at",columnDefinition = "Datetime")
    private String createdAt;
    @Column(name = "updated_at",columnDefinition = "Datetime")
    private String updatedAt;

    public Comments() {
    }

    public Comments(int id, String content,  Articles article, Boolean deleteFlag, String createdAt, String updatedAt) {
        this.id = id;
        this.content = content;
        this.article = article;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Articles getArticle() {
        return article;
    }

    public void setArticle(Articles article) {
        this.article = article;
    }

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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
