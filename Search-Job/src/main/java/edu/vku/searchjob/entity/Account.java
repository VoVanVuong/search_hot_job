package edu.vku.searchjob.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
//        @Column(name = "role")
//        private String role;
        @Column(name = "is_enabled")
        private Boolean isEnabled;
        @Column(name = "password")
        private String password;
        @Column(name = "role")
        private String role;
//        @Lob
//        @Column(name = "avatar_data", columnDefinition = "MEDIUMBLOB")
//        private byte[] avatarData;
        @Column(name = "avatar")
        private String avatar;
        @CreationTimestamp
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "created_at", columnDefinition = "Datetime")
        private String createdAt;
        @UpdateTimestamp
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "updated_at", columnDefinition = "Datetime")
        private String updatedAt;
//        @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
//        private List<AccountRole> accountRoles;


        public Account() {
        }

        public Account(int id, String name,String role, String email,String avatar,  String privacy, Double coin, Boolean isEnabled,String password, String createdAt,String updatedAt) {
                this.id = id;
                this.name = name;
                this.email = email;
//                this.slug = slug;
                this.avatar=avatar;
                this.role=role;
                this.privacy = privacy;
                this.coin = coin;
                this.isEnabled = isEnabled;
                this.password=password;
                this.createdAt=createdAt;
                this.updatedAt=updatedAt;
              //  this.accountRoles = accountRoles;
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

        public String getAvatar() {
                return avatar;
        }

        public void setAvatar(String avatar) {
                this.avatar = avatar;
        }
        //        public byte[] getAvatarData() {
//                return avatarData;
//        }
//
//        public void setAvatarData(byte[] avatarData) {
//                this.avatarData = avatarData;
//        }
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
//isEnabled

        public String getRole() {
                return role;
        }

        public void setRole(String role) {
                this.role = role;
        }

        public Boolean getEnabled() {
                return isEnabled;
        }

        public void setEnabled(Boolean enabled) {
                isEnabled = enabled;
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

//        public List<AccountRole> getAccountRoles() {
//                return accountRoles;
//        }
//
//        public void setAccountRoles(List<AccountRole> accountRoles) {
//                this.accountRoles = accountRoles;
//        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

}
