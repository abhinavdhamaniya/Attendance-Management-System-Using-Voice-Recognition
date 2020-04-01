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

public class AdminHomeC {


    public void manageCourses(ActionEvent event) throws IOException
    {
        SwitchScene s= new SwitchScene();
        s.changeSceneTo(event,"AdminManageCourses.fxml");
    }

    public void logout(ActionEvent event) throws IOException
    {
        LoggedInFaculty.logout();

        SwitchScene s=new SwitchScene();
        s.changeSceneTo(event,"Homepage.fxml");
    }


}
