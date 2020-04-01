package attendance;

import javafx.event.ActionEvent;

import java.io.IOException;

public class AdminManageCoursesC {

    public void openAdd(ActionEvent event) throws IOException
    {
        SwitchScene s= new SwitchScene();
        s.changeSceneTo(event,"AdminAddCourse.fxml");
    }

    public void openEdit(ActionEvent event) throws IOException
    {
        SwitchScene s= new SwitchScene();
        s.changeSceneTo(event,"AdminEditCourse.fxml");
    }

    public void openRemove(ActionEvent event) throws Exception
    {
        SwitchScene s= new SwitchScene();
        s.changeSceneTo(event,"AdminRemoveCourse.fxml");
    }
    public void back(ActionEvent event)throws IOException
    {

        SwitchScene s=new SwitchScene();
        s.changeSceneTo(event,"AdminHome.fxml");
    }


}
