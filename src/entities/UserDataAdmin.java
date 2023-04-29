package entities;


public class UserDataAdmin {


    private String fullname;
    private  String email;

    private String cin,address;
    private String password;
    private String  roles;

    private String description;
    private String image;
    private String cv;
    private String specialite;

    private Integer isverified;

    public UserDataAdmin(String fullname, String email, String cin, String address, String roles, int isBlocked, String specialite) {

        this.fullname = fullname;
        this.email = email;
        this.cin = cin;
        this.address = address;
        this.roles = roles;
        this.isBlocked = isBlocked;

        this.specialite=specialite;

    }

    public Integer getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Integer isBlocked) {
        this.isBlocked = isBlocked;
    }

    private Integer isBlocked;



    public UserDataAdmin(String fullname, String email, String cin, String address, String roles, Integer isverified, Integer isBlocked, String password, String specialite, String cv, String image, String description) {
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

    public UserDataAdmin() {

    }

    public UserDataAdmin(String fullname, String email, String cin, String address, String roles, Integer isverified, String specialite, String cv, String image) {

        this.fullname = fullname;
        this.email = email;
        this.cin = cin;
        this.address = address;
        this.roles = roles;
        this.isverified = isverified;
        this.specialite=specialite;
        this.cv=cv;
        this.image=image;

    }

    public UserDataAdmin(String fullname, String email, String cin, String address, String roles, int isverified, String password, String description, String cv, String image, String specialite) {
        this.fullname = fullname;
        this.email = email;
        this.cin = cin;
        this.address = address;
        this.roles = roles;
        this.isverified = isverified;
        this.specialite=specialite;
        this.cv=cv;
        this.image=image;
        this.description=description;
        this.password=password;


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

    public  String getDescription() {
        return description;
    }

    public  void setDescription(String description) {
        UserData.description = description;
    }

    public  String getImage() {
        return image;
    }

    public  void setImage(String image) {
        UserData.image = image;
    }

    public  String getCv() {
        return cv;
    }

    public  void setCv(String cv) {
        UserData.cv = cv;
    }

    public  String getSpecialite() {
        return specialite;
    }

    public  void setSpecialite(String specialite) {
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

    public  String getPassword() {
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

