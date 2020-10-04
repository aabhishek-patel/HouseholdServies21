package com.formate.householdservies21.Model;

public class UserHelperClass {

    private String id;
    private String username;
    private String fullname;
    private String phoneNo;
    private String imageurl;
    private String email;
    private String password;

    public UserHelperClass(String id, String username, String fullname, String phoneNo, String imageurl, String email, String password) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.phoneNo = phoneNo;
        this.imageurl = imageurl;
        this.email = email;
        this.password = password;
    }

    public UserHelperClass(String fullname, String username, String email, String password, String phoneNo) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
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
}
