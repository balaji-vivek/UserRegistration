package com.example.UserRegistration.Entity;


import jakarta.persistence.*;

@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name="user_id",length=50)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userid;

    @Column(name="user_name",length = 100)
    private String username;

    @Column(name = "email",length = 200)
    private String email;

    @Column(name = "password",length = 100)
    private String password;
    
    @Column(name = "reset_token") // Added column for reset token
    private String resetToken;

    
    public User() {
    }


    public User(int userid, String username, String email, String password) {
        this.userid = userid;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(int userid) {
        this.userid = userid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", resetToken='" + resetToken + '\'' +
                '}';
    }
}
