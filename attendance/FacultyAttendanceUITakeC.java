package attendance;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.DefaultStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;

public class FacultyAttendanceUITakeC implements Initializable {

    private static String selectedCourseCodeForAttendance, selectedDateForUIAttendance;
    @FXML private Label ccode, cname, cfac;
    @FXML private TableView<AttendanceSchema> atten;
    @FXML private TableColumn<AttendanceSchema, String> sno;
    @FXML private TableColumn<AttendanceSchema,String> regno;
    @FXML private TableColumn<AttendanceSchema, String> name;
    @FXML private TableColumn<AttendanceSchema, String> cocode;
    @FXML private TableColumn<AttendanceSchema, String> date;
    @FXML private TableColumn<AttendanceSchema, Object> action;


    public static void setValues(String c, String d)
    {
        selectedCourseCodeForAttendance=c;
        selectedDateForUIAttendance= d;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:1234/Attendance", "root", "");

            String query= "SELECT * FROM courses WHERE code='"+selectedCourseCodeForAttendance+"'";
            Statement st= con.createStatement();
            ResultSet rs= st.executeQuery(query);
            if(!rs.next())
            {
                ccode.setText("Course Code not found");
                cname.setText("Course Name not found");
                cfac.setText("Faculty not found");
            }
            else
            {
                ccode.setText(selectedCourseCodeForAttendance);
                cname.setText(rs.getString(2));
                cfac.setText(rs.getString(6));
            }
            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }


        sno.setCellValueFactory(new PropertyValueFactory<AttendanceSchema,String>("sno"));
        regno.setCellValueFactory(new PropertyValueFactory<AttendanceSchema,String>("regno"));
        name.setCellValueFactory(new PropertyValueFactory<AttendanceSchema,String>("name"));
        cocode.setCellValueFactory(new PropertyValueFactory<AttendanceSchema,String>("ccode"));
        date.setCellValueFactory(new PropertyValueFactory<AttendanceSchema,String>("date"));
        action.setCellValueFactory(new PropertyValueFactory<AttendanceSchema,Object>("action"));
        atten.setItems(getAtten());

    }

    public ObservableList<AttendanceSchema> getAtten()
    {

        ObservableList<AttendanceSchema> mAtten= FXCollections.observableArrayList();


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:1234/Attendance", "root", "");

            String query= "SELECT student.regno, student.fname, student.lname FROM student, classes WHERE (student.regno=classes.regNumber AND classes.courseCode='"+ selectedCourseCodeForAttendance+"')";
            Statement st= con.createStatement();
            ResultSet rs= st.executeQuery(query);
            int sno=1;
            while (rs.next()) {


                ChoiceBox choice= new ChoiceBox();
                choice.getItems().addAll("Present", "Absent");
                choice.setValue("Present");


                mAtten.add(new AttendanceSchema(new SimpleStringProperty(String.valueOf(sno)), new SimpleStringProperty(rs.getString(1)), new SimpleStringProperty(rs.getString(2)+" "+rs.getString(3)), new SimpleStringProperty(selectedCourseCodeForAttendance), choice, selectedDateForUIAttendance));
                sno++;
            }
            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return mAtten;
    }

    public void submitAttendance(ActionEvent event)
    {
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Attendance submitted.");
        alert.show();

        alert.setOnCloseRequest(event2->
        {
            List<Vector<String>> list= new ArrayList<>();

            for (AttendanceSchema o : atten.getItems()) {

                Vector<String> v= new Vector<>();

                v.add(cocode.getCellData(o));
                v.add(regno.getCellData(o));
                v.add(String.valueOf(date.getCellData(o)));
                v.add(String.valueOf(((ChoiceBox)action.getCellData(o)).getValue()));
                list.add(v);
            }

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:1234/Attendance", "root", "");
                for (Vector<String> vec : list) {


                    if(vec.get(3).equals("Present"))
                    {
                        String query= "INSERT INTO attendance (courseCode, regNumber, DATE, presence) VALUES('"+ vec.get(0) +"','"+  vec.get(1)+ "','"+  vec.get(2) + "','1')";
                        PreparedStatement ps= con.prepareStatement(query);
                        int count= ps.executeUpdate(query);
                    }
                    else {
                        String query= "INSERT INTO attendance (courseCode, regNumber, DATE, presence) VALUES('"+ vec.get(0) +"','"+  vec.get(1)+ "','"+  vec.get(2) + "','0')";
                        PreparedStatement ps= con.prepareStatement(query);
                        int count= ps.executeUpdate(query);
                    }

                }
                con.close();
            }
            catch (Exception exc)
            {
                System.out.println(exc);
            }
            System.out.println("Attendance Submitted");
            try
            {
                SwitchScene sw= new SwitchScene();
                sw.changeSceneTo(event, "FacultyHome.fxml");
            }
            catch (IOException io)
            {
                System.out.println(io);
            }
        });

    }

    public void goBack(ActionEvent event) throws IOException
    {
        SwitchScene s= new SwitchScene();
        s.changeSceneTo(event, "FacultyAttendanceUIHome.fxml");
    }
}
