package edu.vku.searchjob.entity;

import jakarta.persistence.*;

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
    @ManyToOne
    @JoinColumn(name = "acount_id")
    private Account account;
    //nhieu cong viec trong 1 loai
    @ManyToOne
    @JoinColumn(name = "categoty_id")
    private Categories categories;

    //1 nhaf tuyeern dung cos the tuy nhieu vi tri
    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employers employers;
    @Column(name = "total_candidate")
    private int totalCandidate;
    @Column(name = "delete_flag")
    private Double deleteFlag;
    @Column(name = "created_at",columnDefinition = "Datetime")
    private String createdAt;
    @Column(name = "updated_at",columnDefinition = "Datetime")
    private String updatedAt;

    public Jobs() {
    }

    public Jobs(int id, String name, Account account, String address, Categories categories, Employers employers, int totalCandidate, Double deleteFlag, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
//        this.slug = slug;
        this.account=account;
        this.address = address;
        this.categories = categories;
        this.employers = employers;
        this.totalCandidate = totalCandidate;
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

//    public String getSlug() {
//        return slug;
//    }
//
//    public void setSlug(String slug) {
//        this.slug = slug;
//    }

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

    public Employers getEmployers() {
        return employers;
    }

    public void setEmployers(Employers employers) {
        this.employers = employers;
    }

    public int getTotalCandidate() {
        return totalCandidate;
    }

    public void setTotalCandidate(int totalCandidate) {
        this.totalCandidate = totalCandidate;
    }

    public Double getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Double deleteFlag) {
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
