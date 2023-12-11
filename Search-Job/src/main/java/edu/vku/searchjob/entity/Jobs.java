package edu.vku.searchjob.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "jobs")
public class Jobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
//    @Column(name = "slug")
//    private String slug;
    @Column(name = "address")
    private String address;
    //nhieu cong viec co the dang boi 1 nguoi khach hang

    //fix1
//    @ManyToOne
//    @JoinColumn(name = "acount_id")
//    private Account account;
    //fix2
    @Column(name = "acount_id")
    private int accountId;
    //nhieu cong viec trong 1 loai
    @ManyToOne
    @JoinColumn(name = "categoty_id")
    private Categories categories;

    //1 nhaf tuyeern dung cos the tuy nhieu vi tri
//    @ManyToOne
//    @JoinColumn(name = "employer_id")
//    private Employers employers;
    @Column(name = "total_candidate")
    private int totalCandidate;
    @Column(name = "request_time")
    private String requestTime;

    @Column(name = "benefit")
    private String benefit;
    @Column(name = "deadline",columnDefinition = "Datetime")
    private String deadline;
    @Column(name = "salary")
    private String salary;
    @Column(name = "delete_flag")
    private Boolean deleteFlag;
    @Column(name = "desription")
    private String desription;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at",columnDefinition = "Datetime")
    private String createdAt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at",columnDefinition = "Datetime")
    private String updatedAt;

    public Jobs() {
    }

    public Jobs(int id, String name, String address,int accountId, Categories categories,String requestTime, int totalCandidate,String desription,String benefit,String deadline,String salary, Boolean deleteFlag, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.accountId=accountId;
//        this.account = account;
        this.categories = categories;
        this.requestTime=requestTime;
        this.totalCandidate = totalCandidate;
        this.desription=desription;
        this.benefit=benefit;
        this.salary=salary;
        this.deadline=deadline;
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

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }
    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    public int getTotalCandidate() {
        return totalCandidate;
    }

    public void setTotalCandidate(int totalCandidate) {
        this.totalCandidate = totalCandidate;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
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

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDesription() {
        return desription;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    //    public Account getAccount() {
//        return account;
//    }
//
//    public void setAccount(Account account) {
//        this.account = account;
//    }

}
