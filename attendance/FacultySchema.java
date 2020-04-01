package attendance;

import javafx.beans.property.SimpleStringProperty;

public class FacultySchema {

    private SimpleStringProperty username,fname,lname,email,pass,phone;

    public FacultySchema(String username, String fname, String lname, String email, String pass, String phone) {
        this.username = new SimpleStringProperty(username);
        this.fname = new SimpleStringProperty(fname);
        this.lname = new SimpleStringProperty(lname);
        this.email = new SimpleStringProperty(email);
        this.pass = new SimpleStringProperty(pass);
        this.phone = new SimpleStringProperty(phone);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public String getFname() {
        return fname.get();
    }

    public SimpleStringProperty fnameProperty() {
        return fname;
    }

    public String getLname() {
        return lname.get();
    }

    public SimpleStringProperty lnameProperty() {
        return lname;
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public String getPass() {
        return pass.get();
    }

    public SimpleStringProperty passProperty() {
        return pass;
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public void setFname(String fname) {
        this.fname.set(fname);
    }

    public void setLname(String lname) {
        this.lname.set(lname);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setPass(String pass) {
        this.pass.set(pass);
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }
}
