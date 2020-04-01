package attendance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import javax.xml.transform.Result;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class FacultyAttendanceUIHomeC{

    @FXML private TextField courseCode;
    @FXML private DatePicker date;

    public void takeUIAttendance(ActionEvent event) throws IOException
    {
        String code= courseCode.getText();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy");
        LocalDate dateres= date.getValue();
        String datef= dtf.format(dateres);

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:1234/Attendance", "root", "");

            String query = "SELECT * FROM courses WHERE code='"+code+"'";
            Statement st = con.createStatement();
            ResultSet rs= st.executeQuery(query);

            if(rs.next())
            {
                if(!rs.getString(6).equals(LoggedInFaculty.username))
                {
                    Alert al= new Alert(Alert.AlertType.ERROR);
                    al.setContentText("You are not subscribed to this course");
                    al.show();
                }

                else
                {
                    String query2 = "SELECT * FROM attendance WHERE courseCode='"+code+"' AND DATE='"+datef+"'";
                    Statement st2 = con.createStatement();
                    ResultSet rs2= st2.executeQuery(query2);

                    if(rs2.next())
                    {
                        Alert a= new Alert(Alert.AlertType.ERROR);
                        a.setContentText("Attendance already submitted for course: "+code+"\nand date: "+ datef);
                        a.show();
                    }

                    else
                    {
                        SwitchScene s= new SwitchScene();

                        System.out.println(courseCode.getText());
                        FacultyAttendanceUITakeC.setValues(code,datef);
                        s.changeSceneTo(event, "FacultyAttendanceUITake.fxml");
                    }
                }

            }
            else
            {
                Alert a= new Alert(Alert.AlertType.ERROR);
                a.setContentText("Course not found");
                a.show();
            }
            con.close();
        }
        catch (Exception exc)
        {
            System.out.println(exc);
        }


    }

    public void goBack(ActionEvent event) throws IOException
    {
        SwitchScene s= new SwitchScene();
        s.changeSceneTo(event, "FacultyAttendanceHome.fxml");
    }
}
