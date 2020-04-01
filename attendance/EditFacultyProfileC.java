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
import java.sql.*;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditFacultyProfileC implements Initializable {

    @FXML private TableView<FacultyEditProfileSchema> editProfile;
    @FXML private TableColumn<FacultyEditProfileSchema,String> property;
    @FXML private TableColumn<FacultyEditProfileSchema,TextField> value;

    @FXML private TextField usernameF, fnameF, lnameF, emailF, passF,passCF, phoneF;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        property.setCellValueFactory(new PropertyValueFactory<FacultyEditProfileSchema,String>("property"));
        value.setCellValueFactory(new PropertyValueFactory<FacultyEditProfileSchema,TextField>("value"));

        usernameF= new TextField();
        fnameF= new TextField();
        lnameF= new TextField();
        emailF= new TextField();
        passF= new TextField();
        passCF= new TextField();
        phoneF= new TextField();

        usernameF.setText(LoggedInFaculty.username);
        usernameF.setEditable(false);
        editProfile.setItems(getProfile());
    }

    public void updateProfile(ActionEvent event) throws Exception
    {
        String errors= "";

        String u= usernameF.getText();
        String f= fnameF.getText();
        String l= lnameF.getText();
        String e= emailF.getText();
        String p= passF.getText();
        String pc= passCF.getText();
        String ph= phoneF.getText();

        Pattern alphabetsP= Pattern.compile("[A-Za-z]*");
        Pattern emailP= Pattern.compile("[A-Za-z0-9]*@[A-Za-z]*.com");
        Pattern phoneP= Pattern.compile("[0-9]{10}");

        Matcher alphabetsM= alphabetsP.matcher(f);

        if(u.equals("") || f.equals("") || l.equals("") || e.equals("") || p.equals("") || pc.equals("") || ph.equals(""))
        {
            errors+="Please fill all the fields.\n";
        }

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

        else
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:1234/Attendance", "root", "");

            String query= "UPDATE faculty SET fname='"+ f +"',lname='"+ l+ "',email='"+ e + "',password='"+p +"',phone='"+ph+"' WHERE username='"+u+"'";
            PreparedStatement ps= con.prepareStatement(query);
            int count= ps.executeUpdate(query);
            if(count!=1)
            {
                Alert a= new Alert(Alert.AlertType.ERROR);
                a.setContentText("Profile Updation Failed.");
                a.show();
                System.out.println("Profile Updation Failed.");
            }
            else
            {
                LoggedInFaculty.updateLoggedInFaculty(u,f,l,e,ph,p);
                Alert a= new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Profile Updation Successful.");
                a.show();

                System.out.println("Profile Updation Successful.");

                a.setOnCloseRequest(event2->{
                    try {
                    SwitchScene sw=new SwitchScene();
                    sw.changeSceneTo(event, "FacultyProfile.fxml");
                    }
                    catch (Exception exc)
                    {
                        System.out.println(exc);
                    }
                });
            }
            con.close();
        }
    }

    public ObservableList<FacultyEditProfileSchema> getProfile()
    {
        ObservableList<FacultyEditProfileSchema> mProfile= FXCollections.observableArrayList();
        mProfile.add(new FacultyEditProfileSchema(new SimpleStringProperty("Username"),usernameF));
        mProfile.add(new FacultyEditProfileSchema(new SimpleStringProperty("First Name"),fnameF));
        mProfile.add(new FacultyEditProfileSchema(new SimpleStringProperty("Last Name"),lnameF));
        mProfile.add(new FacultyEditProfileSchema(new SimpleStringProperty("Email"),emailF));
        mProfile.add(new FacultyEditProfileSchema(new SimpleStringProperty("Password"),passF));
        mProfile.add(new FacultyEditProfileSchema(new SimpleStringProperty("Confirm Password"),passCF));
        mProfile.add(new FacultyEditProfileSchema(new SimpleStringProperty("Phone"),phoneF));

        return mProfile;
    }

    public void back(ActionEvent event)throws IOException
    {
        SwitchScene s=new SwitchScene();
        s.changeSceneTo(event,"FacultyProfile.fxml");
    }
}
