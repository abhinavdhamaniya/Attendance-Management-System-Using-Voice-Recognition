package attendance;
import attendance.FacultyProfileSchema;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class FacultyProfileC implements Initializable {

    @FXML private TableView<FacultyProfileSchema> profile;
    @FXML private TableColumn<FacultyProfileSchema,String> property;
    @FXML private TableColumn<FacultyProfileSchema,String> value;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        property.setCellValueFactory(new PropertyValueFactory<FacultyProfileSchema,String>("property"));
        value.setCellValueFactory(new PropertyValueFactory<FacultyProfileSchema,String>("value"));

        profile.setItems(getProfile());
    }

    public ObservableList<FacultyProfileSchema> getProfile()
    {
        ObservableList<FacultyProfileSchema> mProfile= FXCollections.observableArrayList();
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:1234/Attendance", "root", "");

            String query = "SELECT * FROM faculty WHERE username='" + LoggedInFaculty.username + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();

            mProfile.add(new FacultyProfileSchema("Username", rs.getString(1)));
            mProfile.add(new FacultyProfileSchema("First Name", rs.getString(2)));
            mProfile.add(new FacultyProfileSchema("Last Name", rs.getString(3)));
            mProfile.add(new FacultyProfileSchema("Email", rs.getString(4)));
            mProfile.add(new FacultyProfileSchema("Password", rs.getString(5)));
            mProfile.add(new FacultyProfileSchema("Phone", rs.getString(6)));

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally {
            return mProfile;
        }

    }

    public void back(ActionEvent event)throws IOException
    {

        SwitchScene s=new SwitchScene();
        s.changeSceneTo(event,"FacultyHome.fxml");
    }


    public void openFacEditProfile(ActionEvent event) throws IOException
    {
        SwitchScene s= new SwitchScene();
        s.changeSceneTo(event,"EditFacultyProfile.fxml");
    }

    public void deleteFacProfile(ActionEvent event)
    {
            Alert a= new Alert(Alert.AlertType.WARNING, "Are you sure you want to delete your profile?",ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = a.showAndWait();

            if (result.get() == ButtonType.YES) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:1234/Attendance", "root", "");
                    String query= "DELETE FROM faculty WHERE username='"+ LoggedInFaculty.username+"'";
                    PreparedStatement ps = con.prepareStatement(query);
                    int count= ps.executeUpdate(query);

                    String query2= "UPDATE courses SET facUsername='' WHERE facUsername='"+LoggedInFaculty.username+"'";
                    PreparedStatement ps2 = con.prepareStatement(query2);
                    int count2= ps2.executeUpdate(query2);


                    if(count!=1)
                    {
                        Alert aa= new Alert(Alert.AlertType.ERROR);
                        aa.setContentText("Error deleting your profile.");
                        aa.show();
                    }
                    else {
                        Alert aa= new Alert(Alert.AlertType.INFORMATION);
                        aa.setContentText("Profile Deleted.");
                        aa.show();
                        LoggedInFaculty.logout();

                        aa.setOnCloseRequest(event2->
                        {
                            try {
                                SwitchScene sw= new SwitchScene();
                                sw.changeSceneTo(event, "FacultyLogin.fxml");
                            }
                            catch (Exception err)
                            {
                                System.out.println(err);
                            }
                        });
                    }

                }
                catch (Exception exc)
                {
                    System.out.println(exc);
                }

            }
    }

}
