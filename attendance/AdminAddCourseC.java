package attendance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminAddCourseC implements Initializable {

    @FXML private TextField ccode, cname;
    @FXML private TextArea cdesc;
    @FXML private ChoiceBox<String> credits, ctype;

    public void addCourse(ActionEvent event)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:1234/Attendance", "root", "");

            String query= "INSERT INTO courses VALUES('"+ ccode.getText()+"','"+ cname.getText() +"', '"+ cdesc.getText()+"' , '"+ credits.getValue()+"','"+ctype.getValue()+"', '')";
            PreparedStatement ps= con.prepareStatement(query);
            int count = ps.executeUpdate(query);

            if(count!=1) System.out.println("Error adding the course.");
            else System.out.println("New Course added.");

            Alert a= new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Course Added");
            a.show();
            a.setOnCloseRequest(e->
            {
                try
                {
                    SwitchScene s= new SwitchScene();
                    s.changeSceneTo(event,"AdminManageCourses.fxml");
                }
                catch (Exception exc)
                {
                    System.out.println(exc);
                }
            });
            con.close();
        }
        catch (Exception exc)
        {
            Alert a= new Alert(Alert.AlertType.ERROR);
            a.setContentText("Error Occured");
            a.show();
        }





    }

    public void goBack(ActionEvent event) throws IOException
    {
        SwitchScene sw= new SwitchScene();
        sw.changeSceneTo(event, "AdminManageCourses.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> ob1= FXCollections.observableArrayList("1", "2", "3", "4");
        ObservableList<String> ob2= FXCollections.observableArrayList("Theory Only", "Lab Only", "Embedded");

        credits.setItems(ob1);
        credits.setValue("1");

        ctype.setItems(ob2);
        ctype.setValue("Theory Only");
    }
}
