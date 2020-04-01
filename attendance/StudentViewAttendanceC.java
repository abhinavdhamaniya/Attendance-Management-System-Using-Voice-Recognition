package attendance;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class StudentViewAttendanceC implements Initializable {

    private static String studentAttendanceCourseCode;


    @FXML private TableView<StudentAttendanceSchema> studAttendance;
    @FXML private TableColumn<StudentAttendanceSchema, String> sno;
    @FXML private TableColumn<StudentAttendanceSchema,String> course;
    @FXML private TableColumn<StudentAttendanceSchema, String> date;
    @FXML private TableColumn<StudentAttendanceSchema, String> atten;

    @FXML private Label name, attended, avg;

    private int totClasses;
    private int attendedClasses;
    private double avgAtten;

    public static void setValues(String code)
    {
        studentAttendanceCourseCode=code;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        totClasses=0;
        attendedClasses=0;

        name.setText(LoggedInStudent.fname+" "+LoggedInStudent.lname);

        sno.setCellValueFactory(new PropertyValueFactory<StudentAttendanceSchema,String>("sno"));
        course.setCellValueFactory(new PropertyValueFactory<StudentAttendanceSchema,String>("course"));
        date.setCellValueFactory(new PropertyValueFactory<StudentAttendanceSchema,String>("date"));
        atten.setCellValueFactory(new PropertyValueFactory<StudentAttendanceSchema,String>("atten"));

        studAttendance.setItems(getAtten());

        avgAtten=((double) attendedClasses/(double)totClasses)*100;

        attended.setText(String.valueOf(attendedClasses)+"/"+String.valueOf(totClasses));
        avg.setText(String.valueOf(avgAtten));
    }

    public ObservableList<StudentAttendanceSchema> getAtten()
    {

        ObservableList<StudentAttendanceSchema> mAtten= FXCollections.observableArrayList();


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:1234/Attendance", "root", "");

            String query= "SELECT * from attendance WHERE regNumber='"+LoggedInStudent.regno+"' AND courseCode='"+studentAttendanceCourseCode+"';";
            Statement st= con.createStatement();
            ResultSet rs= st.executeQuery(query);
            int sno=1;


            while (rs.next()) {

                totClasses++;

                String presence= rs.getString(5);
                if(presence.equals("1"))
                {
                    attendedClasses++;
                    presence="PRESENT";
                }
                else
                {
                    presence="ABSENT";
                }
                mAtten.add(new StudentAttendanceSchema(new SimpleStringProperty(String.valueOf(sno)), new SimpleStringProperty(rs.getString(2)), new SimpleStringProperty(rs.getString(4)), new SimpleStringProperty(presence)));
                sno++;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return mAtten;
    }

    public void goBack(ActionEvent event)throws IOException
    {

        SwitchScene s=new SwitchScene();
        s.changeSceneTo(event,"StudentAttendanceHome.fxml");
    }
}
