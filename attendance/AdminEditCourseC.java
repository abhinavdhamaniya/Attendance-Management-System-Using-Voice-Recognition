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

public class AdminEditCourseC {

    @FXML private TextField ccode;

    public void goBack(ActionEvent event) throws IOException
    {
        SwitchScene sw= new SwitchScene();
        sw.changeSceneTo(event, "AdminManageCourses.fxml");
    }

    public void openEdit(ActionEvent event) throws IOException
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:1234/Attendance", "root", "");

            String query = "SELECT * FROM courses WHERE code='"+ccode.getText()+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            if(rs.next())
            {
                AdminEditCourseFinalC.setCode(ccode.getText());
                SwitchScene sw= new SwitchScene();
                sw.changeSceneTo(event, "AdminEditCourseFinal.fxml");
            }

            else
            {
                Alert a= new Alert(Alert.AlertType.ERROR);
                a.setContentText("Course Not Found.");
                a.show();
            }


        }
        catch (Exception err)
        {
            Alert a= new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Error Occurred.");
            a.show();
            System.out.println(err);
        }

    }

}
