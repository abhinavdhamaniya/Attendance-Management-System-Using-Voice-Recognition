package attendance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentHomeC implements Initializable {

    @FXML private Label greetName;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        greetName.setText("Welcome "+LoggedInStudent.fname+" "+LoggedInStudent.lname);
    }

    public void openStudProfile(ActionEvent event) throws IOException
    {
        SwitchScene s= new SwitchScene();
        s.changeSceneTo(event,"StudentProfile.fxml");
    }

    public void manageCourses(ActionEvent event) throws IOException
    {
        SwitchScene s= new SwitchScene();
        s.changeSceneTo(event,"StudentManageCourses.fxml");
    }

    public void takeAttendance(ActionEvent event) throws IOException
    {
        SwitchScene s= new SwitchScene();
        s.changeSceneTo(event,"StudentAttendanceHome.fxml");
    }

    public void logout(ActionEvent event) throws IOException
    {
        LoggedInStudent.logout();

        SwitchScene s=new SwitchScene();
        s.changeSceneTo(event,"Homepage.fxml");
    }


}
