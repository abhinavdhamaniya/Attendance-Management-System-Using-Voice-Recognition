package attendance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StudentAttendanceHomeC {

    @FXML private TextField cocode;

    public void openViewAttendance(ActionEvent event) throws IOException
    {
        String cocoderes= cocode.getText();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:1234/Attendance", "root", "");

            String query= "SELECT * FROM classes WHERE courseCode='"+cocoderes+"' AND regNumber='"+LoggedInStudent.regno+"'";
            Statement st= con.createStatement();
            ResultSet rs= st.executeQuery(query);

            if(!rs.next())
            {
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Course not found.\nOr course is not registered by you");
                alert.show();
            }
            else
            {
                StudentViewAttendanceC.setValues(cocoderes);

                SwitchScene s= new SwitchScene();
                s.changeSceneTo(event, "StudentViewAttendance.fxml");
            }
        }
        catch (Exception exc)
        {
            System.out.println(exc);
        }
    }

    public void goBack(ActionEvent event)throws IOException
    {

        SwitchScene s=new SwitchScene();
        s.changeSceneTo(event,"StudentHome.fxml");
    }
}
