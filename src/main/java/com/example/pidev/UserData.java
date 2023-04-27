package com.example.pidev;

public class UserData {

private String fullname;
private String email,cin,address,roles;
private Integer id, isverified;


    public UserData(Integer id,String fullname, String email, String cin, String address, String roles, Integer isverified) {
        this.id=id;
        this.fullname = fullname;
        this.email = email;
        this.cin = cin;
        this.address = address;
        this.roles = roles;
        this.isverified = isverified;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Integer getIsverified() {
        return isverified;
    }

    public void setIsverified(Integer isverified) {
        this.isverified = isverified;
    }
    public String toString() {
        return "UserData{" +
                "fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", cin='" + cin + '\'' +
                ", address='" + address + '\'' +
                ", roles='" + roles + '\'' +
                ", isverified=" + isverified +
                '}';
    }
}
