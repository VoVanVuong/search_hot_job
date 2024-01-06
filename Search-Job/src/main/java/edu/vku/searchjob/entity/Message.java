package edu.vku.searchjob.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "categorymessage_id")
    private CategoryMessage categoryMessage;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at ",columnDefinition = "Datetime")
    private String createdAt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at ",columnDefinition = "Datetime")
    private String updateAt;

    @Column(name = "delete_flag")
    private String deleteFlag;

    public Message() {
    }

    public Message(int id, String name, CategoryMessage categoryMessage, String createdAt, String updateAt, String deleteFlag,Account account) {
        this.id = id;
        this.name = name;
        this.categoryMessage = categoryMessage;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.deleteFlag = deleteFlag;
        this.account=account;
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

    public CategoryMessage getCategoryMessage() {
        return categoryMessage;
    }

    public void setCategoryMessage(CategoryMessage categoryMessage) {
        this.categoryMessage = categoryMessage;
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

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
