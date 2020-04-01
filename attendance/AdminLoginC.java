package attendance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminLoginC {

    @FXML private TextField ausername, apassword;

    public void doLogin(ActionEvent e) throws IOException
    {
        if(ausername.getText().equals("admin") && apassword.getText().equals("123"))
        {
            SwitchScene sw= new SwitchScene();
            sw.changeSceneTo(e,"AdminHome.fxml");
        }
        else
        {
            Alert a= new Alert(Alert.AlertType.ERROR);
            a.setContentText("Invalid Admin Username/Password");
            a.show();
            System.out.println("Admin Login Failed.");
        }
    }

    public void back(ActionEvent e)throws IOException
    {
        SwitchScene sw= new SwitchScene();
        sw.changeSceneTo(e,"Homepage.fxml");
    }

}
