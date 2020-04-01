package attendance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentLoginC {

    @FXML
    private TextField regno,spassword;

    public void doLogin(ActionEvent event) throws  Exception{

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:1234/Attendance", "root", "");

        String query= "SELECT * FROM student WHERE regno='"+regno.getText()+"' AND password='"+spassword.getText()+"'";
        Statement st= con.createStatement();
        ResultSet rs= st.executeQuery(query);
        if(rs.next())
        {
            System.out.println("Student login succesfull");


            LoggedInStudent.regno= regno.getText();
            LoggedInStudent.fname= rs.getString(2);
            LoggedInStudent.lname= rs.getString(3);
            LoggedInStudent.email= rs.getString(4);
            LoggedInStudent.phone= rs.getString(5);
            LoggedInStudent.address= rs.getString(6);
            LoggedInStudent.branch= rs.getString(7);
            LoggedInStudent.password= rs.getString(8);


            SwitchScene s= new SwitchScene();
            s.changeSceneTo(event,"StudentHome.fxml");
        }
        else
        {
            Alert a= new Alert(Alert.AlertType.ERROR);
            a.setContentText("Invalid Student Registration Number/Password.");
            a.show();
            System.out.println("Student Login Failed.");
        }

        con.close();

    }

    public void openSignUp(ActionEvent event) throws IOException
    {
        SwitchScene s= new SwitchScene();
        s.changeSceneTo(event,"StudentSignUp.fxml");
    }

    public void back(ActionEvent event)throws IOException
    {

        SwitchScene s=new SwitchScene();
        s.changeSceneTo(event,"Homepage.fxml");
    }
}
