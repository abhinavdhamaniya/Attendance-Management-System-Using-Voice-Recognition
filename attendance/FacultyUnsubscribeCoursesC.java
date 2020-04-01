package attendance;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class FacultyUnsubscribeCoursesC implements Initializable {

    @FXML
    private TableView<CourseSchema> subCourses;
    @FXML private TableColumn<CourseSchema,String> sno;
    @FXML private TableColumn<CourseSchema,String> code;
    @FXML private TableColumn<CourseSchema,String> name;
    @FXML private TableColumn<CourseSchema,String> desc;
    @FXML private TableColumn<CourseSchema,String> credits;
    @FXML private TableColumn<CourseSchema,String> type;
    @FXML private TableColumn<CourseSchema,String> subscribedBy;
    @FXML private TableColumn<CourseSchema, Button> action;
    private EventHandler<ActionEvent> event;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
         event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Alert alert= new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to unsubscribe from this course?", ButtonType.YES, ButtonType.NO);

                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.YES) {

                    CourseSchema person = subCourses.getSelectionModel().getSelectedItem();

                    CourseSchema selectedItem = subCourses.getSelectionModel().getSelectedItem();
                    subCourses.getItems().remove(selectedItem);

                    String code = person.getCode();
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:1234/Attendance", "root", "");

                        String query = "UPDATE courses SET facUsername='' WHERE code='" + code + "'";
                        PreparedStatement ps = con.prepareStatement(query);
                        int count = ps.executeUpdate(query);
                        if (count != 1) System.out.println("Unsubscribe Failed");

                        con.close();
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
        };


        sno.setCellValueFactory(new PropertyValueFactory<CourseSchema,String>("sno"));
        code.setCellValueFactory(new PropertyValueFactory<CourseSchema,String>("code"));
        name.setCellValueFactory(new PropertyValueFactory<CourseSchema,String>("name"));
        desc.setCellValueFactory(new PropertyValueFactory<CourseSchema,String>("desc"));
        credits.setCellValueFactory(new PropertyValueFactory<CourseSchema,String>("credits"));
        type.setCellValueFactory(new PropertyValueFactory<CourseSchema,String>("type"));
        subscribedBy.setCellValueFactory(new PropertyValueFactory<CourseSchema,String>("subscribedBy"));
        action.setCellValueFactory(new PropertyValueFactory<CourseSchema,Button>("action"));

        try {
            subCourses.setItems(getCourses());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public ObservableList<CourseSchema> getCourses() throws Exception
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:1234/Attendance", "root", "");

        String query= "SELECT * FROM courses WHERE facUsername='"+LoggedInFaculty.username+"'";
        Statement st= con.createStatement();
        ResultSet rs= st.executeQuery(query);
        ObservableList<CourseSchema> courses = FXCollections.observableArrayList();
        int sno=1;
        while (rs.next()) {

            String ssno= String.valueOf(sno);
            String code= rs.getString(1);
            String name= rs.getString(2);
            String desc= rs.getString(3);
            String credits= rs.getString(4);
            String type= rs.getString(5);
            String faculty= rs.getString(6);

            Button b1= new Button("Unsubscribe");
            b1.setOnAction(event);
            courses.add(new CourseSchema(new SimpleStringProperty(ssno), new SimpleStringProperty(code), new SimpleStringProperty(name), new SimpleStringProperty(desc), new SimpleStringProperty(credits), new SimpleStringProperty(type), new SimpleStringProperty(faculty), b1));
            sno++;
        }
        con.close();;
        return courses;
    }

    public void back(ActionEvent event)throws IOException
    {

        SwitchScene s=new SwitchScene();
        s.changeSceneTo(event,"FacultyManageCourses.fxml");
    }

}
