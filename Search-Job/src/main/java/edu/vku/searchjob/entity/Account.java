package edu.vku.searchjob.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "account")
public class Account {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;
        @Column(name = "name")
        private String name;
        @Column(name = "email")
        private String email;
//        @Column(name = "slug")
//        private String slug;
        @Column(name = "privacy")
        private String privacy;
        @Column(name = "coin")
        private Double coin;
        @Column(name = "enabled_flag")
        private Boolean enabled_flag;
        @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
        private List<AccountRole> accountRoles;

        public Account() {
        }

        public Account(int id, String name, String email,  String privacy, Double coin, Boolean enabled_flag, List<AccountRole> accountRoles) {
                this.id = id;
                this.name = name;
                this.email = email;
//                this.slug = slug;
                this.privacy = privacy;
                this.coin = coin;
                this.enabled_flag = enabled_flag;
                this.accountRoles = accountRoles;
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

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

//        public String getSlug() {
//                return slug;
//        }
//
//        public void setSlug(String slug) {
//                this.slug = slug;
//        }

        public String getPrivacy() {
                return privacy;
        }

        public void setPrivacy(String privacy) {
                this.privacy = privacy;
        }

        public Double getCoin() {
                return coin;
        }

        public void setCoin(Double coin) {
                this.coin = coin;
        }

        public Boolean getEnabled_flag() {
                return enabled_flag;
        }

        public void setEnabled_flag(Boolean enabled_flag) {
                this.enabled_flag = enabled_flag;
        }

        public List<AccountRole> getAccountRoles() {
                return accountRoles;
        }

        public void setAccountRoles(List<AccountRole> accountRoles) {
                this.accountRoles = accountRoles;
        }
}
