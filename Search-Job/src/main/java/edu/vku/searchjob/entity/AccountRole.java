//package edu.vku.searchjob.entity;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "account_role")
//public class AccountRole {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column int id;
//
//    @ManyToOne
//    @JoinColumn(name = "account_id")
//    private Account account;
//
//    @ManyToOne
//    @JoinColumn(name = "role_id")
//    private AppRole role;
//
//    public AccountRole() {
//    }
//
//    public AccountRole(int id, Account account, AppRole role) {
//        this.id = id;
//        this.account = account;
//        this.role = role;
//    }
//    public AccountRole( Account account, AppRole role) {
//        this.id = id;
//        this.account = account;
//        this.role = role;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public Account getAccount() {
//        return account;
//    }
//
//    public void setAccount(Account account) {
//        this.account = account;
//    }
//
//    public AppRole getRole() {
//        return role;
//    }
//
//    public void setRole(AppRole role) {
//        this.role = role;
//    }
//}
