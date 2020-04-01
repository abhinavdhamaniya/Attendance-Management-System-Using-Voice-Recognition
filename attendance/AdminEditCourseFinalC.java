package attendance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AdminEditCourseFinalC implements Initializable {

    @FXML private TextField ccode, cname;
    @FXML private TextArea cdesc;
    @FXML private ChoiceBox<String> credits, ctype;
    private static String selectedCourseCodeForEditing;

    public static void setCode(String s)
    {
        selectedCourseCodeForEditing=s;
    }

    public void addCourse(ActionEvent event)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:1234/Attendance", "root", "");

            String query = "UPDATE courses SET name='" + cname.getText() + "', description='" + cdesc.getText() + "' , credits='" + credits.getValue() + "', type='" + ctype.getValue() + "' WHERE code='" + selectedCourseCodeForEditing + "'";
            PreparedStatement ps = con.prepareStatement(query);
            int count = ps.executeUpdate(query);

            if (count != 1) {
                Alert a= new Alert(Alert.AlertType.ERROR);
                a.setContentText("Error Occured");
                a.show();
                System.out.println("Error updating the course. Course code provided may be incorrect.");
            }
            else
            {

                Alert a= new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Course Updated.");
                a.show();


                    a.setOnCloseRequest(e->
                    {
                        try {
                        SwitchScene s= new SwitchScene();
                        s.changeSceneTo(event, "AdminManageCourses.fxml");
                        }
                        catch (Exception ioe)
                        {
                            System.out.println(ioe);
                        }
                    });


                System.out.println("Course Updated.");
            }

            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

    public void goBack(ActionEvent event) throws IOException
    {
        SwitchScene sw= new SwitchScene();
        sw.changeSceneTo(event, "AdminEditCourse.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ccode.setText(selectedCourseCodeForEditing);
        ccode.setEditable(false);

        ObservableList<String> ob1= FXCollections.observableArrayList("1", "2", "3", "4");
        ObservableList<String> ob2= FXCollections.observableArrayList("Theory Only", "Lab Only", "Embedded");

        credits.setItems(ob1);
        credits.setValue("1");

        ctype.setItems(ob2);
        ctype.setValue("Theory Only");
    }
}
