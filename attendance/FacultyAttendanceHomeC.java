package attendance;

import javafx.event.ActionEvent;

import java.io.IOException;

public class FacultyAttendanceHomeC {

    public void openUIAttendance(ActionEvent event) throws IOException
    {
        SwitchScene s= new SwitchScene();
        s.changeSceneTo(event, "FacultyAttendanceUIHome.fxml");
    }

    public void openVoiceAttendance(ActionEvent event) throws IOException
    {
        SwitchScene s= new SwitchScene();
        s.changeSceneTo(event, "FacultyAttendanceVoiceHome.fxml");
    }

    public void goBack(ActionEvent event) throws IOException
    {
        SwitchScene s= new SwitchScene();
        s.changeSceneTo(event, "FacultyHome.fxml");
    }
}
