package attendance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class FacultyLoginC {

    @FXML private TextField fusername,fpassword;

    public void doLogin(ActionEvent event) throws  Exception{

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:1234/Attendance", "root", "");

        String query= "SELECT * FROM faculty WHERE username='"+fusername.getText()+"' AND password='"+fpassword.getText()+"'";
        Statement st= con.createStatement();
        ResultSet rs= st.executeQuery(query);
        if(rs.next())
        {
            System.out.println("Faculy login succesfull");


            LoggedInFaculty.username= fusername.getText();
            LoggedInFaculty.fname= rs.getString(2);
            LoggedInFaculty.lname= rs.getString(3);
            LoggedInFaculty.email= rs.getString(4);
            LoggedInFaculty.password= rs.getString(5);
            LoggedInFaculty.phone= rs.getString(6);

            SwitchScene s= new SwitchScene();
            s.changeSceneTo(event,"FacultyHome.fxml");
        }
        else
        {
            Alert a= new Alert(Alert.AlertType.ERROR);
            a.setContentText("Invalid Faculty Username/Password.");
            a.show();
            System.out.println("Faculty Login Failed.");
        }

        con.close();

    }

    public void openSignUp(ActionEvent event) throws IOException
    {
        SwitchScene s= new SwitchScene();
        s.changeSceneTo(event,"FacultySignUp.fxml");
    }

    public void back(ActionEvent event)throws IOException
    {

        SwitchScene s=new SwitchScene();
        s.changeSceneTo(event,"Homepage.fxml");
    }

}
