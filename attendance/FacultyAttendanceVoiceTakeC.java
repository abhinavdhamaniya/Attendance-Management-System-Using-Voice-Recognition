package attendance;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FacultyAttendanceVoiceTakeC implements Initializable {

    private static String courseCodeForFacultyVoiceAttendance, dateForFacultyVoiceAttendance;
    public static void setValues(String s, String d)
    {
        dateForFacultyVoiceAttendance= d;
        courseCodeForFacultyVoiceAttendance=s;
    }

    @FXML private TableView<AttendanceSchema> atten;
    @FXML private TableColumn<AttendanceSchema, String> sno;
    @FXML private TableColumn<AttendanceSchema,String> regno;
    @FXML private TableColumn<AttendanceSchema, String> name;
    @FXML private TableColumn<AttendanceSchema, String> cocode;
    @FXML private TableColumn<AttendanceSchema, String> date;
    @FXML private TableColumn<AttendanceSchema, Object> action;

    @FXML private Label ccode,cname,cfac, instruct;
    private int snoval;
    private ObservableList<AttendanceSchema> mAtten= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        snoval=1;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:1234/Attendance", "root", "");

            String query= "SELECT * FROM courses WHERE code='"+courseCodeForFacultyVoiceAttendance+"'";
            Statement st= con.createStatement();
            ResultSet rs= st.executeQuery(query);
            if(!rs.next())
            {
                ccode.setText("Course Not found");
                cname.setText("Course not found");
                cfac.setText("Faculty not found");
            }
            else
            {
                ccode.setText(courseCodeForFacultyVoiceAttendance);
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


    }

    public void submitAttendance(ActionEvent event)
    {
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Attendance submitted.");
        alert.show();

        alert.setOnCloseRequest(event2->
        {

            Set<String> presentStudents= new HashSet<>();
            Set<String> allStudents = new HashSet<>();
            Set<String> absentStudents = new HashSet<>();

            for (AttendanceSchema o : atten.getItems()) {

                String presence= String.valueOf(action.getCellData(o));
                if(presence.equals("Present"))
                {
                    presentStudents.add(regno.getCellData(o));
                }
            }

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:1234/Attendance", "root", "");

                for (String i : presentStudents) {

                    String query = "INSERT INTO attendance (courseCode, regNumber, DATE, presence) VALUES('" + courseCodeForFacultyVoiceAttendance + "','" + i + "','" + dateForFacultyVoiceAttendance + "','1')";
                    PreparedStatement ps = con.prepareStatement(query);
                    int count = ps.executeUpdate(query);
                    if (count != 1) System.out.println("Error occurred while submitting attendance.");
                }


                String query2="SELECT * FROM classes WHERE courseCode='"+courseCodeForFacultyVoiceAttendance+"'";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query2);
                while (rs.next())
                {
                    allStudents.add(rs.getString(2));
                }

                for(String i: allStudents)
                {
                    if(!presentStudents.contains(i))
                    {
                        absentStudents.add(i);
                    }
                }

                for (String i : absentStudents) {

                    String query33 = "INSERT INTO attendance (courseCode, regNumber, DATE, presence) VALUES('" + courseCodeForFacultyVoiceAttendance + "','" + i + "','" + dateForFacultyVoiceAttendance + "','0')";
                    PreparedStatement ps33 = con.prepareStatement(query33);
                    int count33 = ps33.executeUpdate(query33);
                    if (count33 != 1) System.out.println("Error occurred while submitting attendance.");
                }


                con.close();
            } catch (Exception exc) {
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

    public void startVoiceToText(ActionEvent e)
    {

        try{

            Process p = Runtime.getRuntime().exec("C:\\Users\\Abhinav\\AppData\\Local\\Programs\\Python\\Python37-32\\python.exe C:\\Users\\Abhinav\\IdeaProjects\\Attendance-Management-System\\src\\attendance\\VoiceToText.py");
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String voiceName= in.readLine().toUpperCase();

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:1234/Attendance", "root", "");

            String query= "SELECT classes.regNumber FROM classes,student WHERE (classes.regNumber=student.regno AND student.fname='"+ voiceName +"' AND classes.courseCode='"+courseCodeForFacultyVoiceAttendance+"')";
            Statement st= con.createStatement();
            ResultSet rs= st.executeQuery(query);

            if(rs.next())
            {
                String fetchedRegNumber=rs.getString(1);
                instruct.setText(voiceName+" found in classs");

                String query1= "SELECT * from student WHERE regNo='"+fetchedRegNumber+"'";
                Statement st1= con.createStatement();
                ResultSet rs1= st1.executeQuery(query1);
                rs1.next();

                mAtten.addAll(new AttendanceSchema(new SimpleStringProperty(String.valueOf(snoval)), new SimpleStringProperty(rs1.getString(1)), new SimpleStringProperty(rs1.getString(2)+" "+rs1.getString(3)), new SimpleStringProperty(courseCodeForFacultyVoiceAttendance), "Present",dateForFacultyVoiceAttendance));
                snoval++;

                atten.setItems(mAtten);
            }
            else
            {
                if(voiceName.equals("NOT RECOGNIZED")) instruct.setText(voiceName);
                else instruct.setText(voiceName+" not found in classs");
            }

            con.close();

        }
        catch(Exception exc){
            System.out.println(exc);
        }
    }

    public void goBack(ActionEvent event) throws IOException
    {
        SwitchScene s= new SwitchScene();
        s.changeSceneTo(event, "FacultyAttendanceVoiceHome.fxml");
    }

}
