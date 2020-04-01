package attendance;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditStudentProfileC implements Initializable {

    @FXML private TableView<FacultyEditProfileSchema> editProfile;
    @FXML private TableColumn<FacultyEditProfileSchema,String> property;
    @FXML private TableColumn<FacultyEditProfileSchema,TextField> value;

    @FXML private TextField regno, fnameS, lnameS, emailS, phoneS, addressS, branchS, passS, passCS;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        property.setCellValueFactory(new PropertyValueFactory<FacultyEditProfileSchema,String>("property"));
        value.setCellValueFactory(new PropertyValueFactory<FacultyEditProfileSchema,TextField>("value"));

        regno= new TextField();
        fnameS= new TextField();
        lnameS= new TextField();
        emailS= new TextField();
        passS= new TextField();
        passCS= new TextField();
        phoneS= new TextField();
        addressS= new TextField();
        branchS= new TextField();

        regno.setText(LoggedInStudent.regno);
        regno.setEditable(false);
        editProfile.setItems(getProfile());
    }

    public void updateProfile(ActionEvent event)
    {
        String errors= "";

        String r= regno.getText();
        String f= fnameS.getText();
        String l= lnameS.getText();
        String e= emailS.getText();
        String p= passS.getText();
        String pc= passCS.getText();
        String ph= phoneS.getText();
        String addr= addressS.getText();
        String b= branchS.getText();


        Pattern alphabetsP= Pattern.compile("[A-Za-z]*");
        Pattern emailP= Pattern.compile("[A-Za-z0-9]*@[A-Za-z]*.com");
        Pattern phoneP= Pattern.compile("[0-9]{10}");



        if(r.equals("") || f.equals("") || l.equals("") || e.equals("") || p.equals("") || pc.equals("") || ph.equals("") || addr.equals("") || b.equals(""))
        {
            errors+="Please fill all the fields.\n";
        }

        Matcher alphabetsM= alphabetsP.matcher(f);
        if(!alphabetsM.matches()) errors+="First name should only contains alphabets.\n";

        alphabetsM= alphabetsP.matcher(l);
        if(!alphabetsM.matches()) errors+="Last name should only contains alphabets.\n";

        Matcher emailM= emailP.matcher(e);
        if(!emailM.matches()) errors+="Invalid email.\n";

        if(!p.equals(pc)) errors+="Passwords do not match.\n";

        Matcher phoneM= phoneP.matcher(ph);
        if(!phoneM.matches()) errors+="Invalid phone number.\n";



        if(!errors.equals(""))
        {
            Alert a= new Alert(Alert.AlertType.ERROR);
            a.setContentText(errors);
            a.show();
        }

        else {

            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://localhost:1234/Attendance", "root", "");

                String query= "UPDATE student SET fname='"+ f +"',lname='"+ l+ "',email='"+ e + "',password='"+p +"',phone='" + ph+"',address='"+addr+"' ,Branch='"+ b +"' WHERE regno='"+r+"'";
                PreparedStatement ps= con.prepareStatement(query);
                int count= ps.executeUpdate(query);
                if(count!=1)
                {
                    Alert alert= new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Profile Updation Failed.");
                    alert.show();
                    System.out.println("Profile Updation Failed.");
                }
                else {

                    LoggedInStudent.updateLoggedInStudent(r,f,l,e,ph,addr,b,p);
                    Alert a= new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Profile Updation Successful.");
                    a.show();
                    System.out.println("Profile Updation Successful.");

                    a.setOnCloseRequest(event2->{
                        try {
                            SwitchScene sw=new SwitchScene();
                            sw.changeSceneTo(event, "StudentProfile.fxml");
                        }
                        catch (Exception exc)
                        {
                            System.out.println(exc);
                        }
                    });
                }
                con.close();
            }
            catch (Exception exc)
            {
                System.out.println(exc);
            }

        }
    }

    public ObservableList<FacultyEditProfileSchema> getProfile()
    {
        ObservableList<FacultyEditProfileSchema> mProfile= FXCollections.observableArrayList();
        mProfile.add(new FacultyEditProfileSchema(new SimpleStringProperty("Registration Number"),regno));
        mProfile.add(new FacultyEditProfileSchema(new SimpleStringProperty("First Name"),fnameS));
        mProfile.add(new FacultyEditProfileSchema(new SimpleStringProperty("Last Name"),lnameS));
        mProfile.add(new FacultyEditProfileSchema(new SimpleStringProperty("Email"),emailS));
        mProfile.add(new FacultyEditProfileSchema(new SimpleStringProperty("Phone"),phoneS));
        mProfile.add(new FacultyEditProfileSchema(new SimpleStringProperty("Password"),passS));
        mProfile.add(new FacultyEditProfileSchema(new SimpleStringProperty("Confirm Password"),passCS));
        mProfile.add(new FacultyEditProfileSchema(new SimpleStringProperty("Branch"),branchS));
        mProfile.add(new FacultyEditProfileSchema(new SimpleStringProperty("Address"),addressS));


        return mProfile;
    }

    public void back(ActionEvent event)throws IOException
    {
        SwitchScene s=new SwitchScene();
        s.changeSceneTo(event,"StudentProfile.fxml");
    }
}
