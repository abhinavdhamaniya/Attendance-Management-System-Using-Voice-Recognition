package attendance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class HomeC
{

    public void facultyLogin(javafx.event.ActionEvent event) throws  IOException{
        SwitchScene s= new SwitchScene();
        s.changeSceneTo(event,"FacultyLogin.fxml");
    }
    public void studentLogin(javafx.event.ActionEvent event) throws  IOException{
        SwitchScene s= new SwitchScene();
        s.changeSceneTo(event,"StudentLogin.fxml");
    }

    public void adminLogin(javafx.event.ActionEvent event) throws  IOException{
        SwitchScene s= new SwitchScene();
        s.changeSceneTo(event,"AdminLogin.fxml");
    }

}