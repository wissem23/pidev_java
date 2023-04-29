package entities;

public class UserData {


    public static String fullname;
    public static String email;
    public static  String cin,address;
    public static String password;
    public static String  roles;

    public static String description;
    public static String image;
    public static String cv;
    public static String specialite;

    public static Integer isverified;

    public static Integer getIsBlocked() {
        return isBlocked;
    }

    public static void setIsBlocked(Integer isBlocked) {
        UserData.isBlocked = isBlocked;
    }

    public static Integer isBlocked;


    public UserData(String fullname, String email, String cin, String address, String roles, Integer isverified, Integer isBlocked, String password, String specialite, String cv, String image, String description) {
        this.fullname = fullname;
        this.email = email;
        this.cin = cin;
        this.address = address;
        this.roles = roles;
        this.isverified = isverified;
        this.isBlocked = isBlocked;

        this.password=password;
        this.specialite=specialite;
        this.cv=cv;
        this.image=image;
        this.description=description;


    }

    public UserData() {

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

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        UserData.description = description;
    }

    public static String getImage() {
        return image;
    }

    public static void setImage(String image) {
        UserData.image = image;
    }

    public static String getCv() {
        return cv;
    }

    public static void setCv(String cv) {
        UserData.cv = cv;
    }

    public static String getSpecialite() {
        return specialite;
    }

    public static void setSpecialite(String specialite) {
        UserData.specialite = specialite;
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

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UserData.password = password;
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
                ", isverified=" + isverified +'\'' +
                ", isBlocked=" + isBlocked +'\'' +
                ", password=" + password +'\'' +
                ", image=" + image +'\'' +
                ", specialite=" + specialite +'\'' +
                ", cv=" + cv +'\'' +
                ", description=" + description +


                '}';
    }
}
