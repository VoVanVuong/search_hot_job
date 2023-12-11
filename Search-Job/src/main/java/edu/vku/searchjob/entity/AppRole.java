//package edu.vku.searchjob.entity;
//
//import jakarta.persistence.*;
//
//import java.util.List;
//
//@Entity
//@Table(name = "role")
//public class AppRole {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;
//    @Column(name = "name")
//    private String name;
//
//    @OneToMany(mappedBy = "role")
//    private List<AccountRole> accountRoles;
//    public AppRole() {
//    }
//
//    public AppRole(int id, String name, List<AccountRole> accountRoles) {
//        this.id = id;
//        this.name = name;
//        this.accountRoles = accountRoles;
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
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<AccountRole> getAccountRoles() {
//        return accountRoles;
//    }
//
//    public void setAccountRoles(List<AccountRole> accountRoles) {
//        this.accountRoles = accountRoles;
//    }
//}
