package attendance;

import javafx.event.ActionEvent;

import java.io.IOException;

public class FacultyManageCoursesC {

    public void openSubscribe(ActionEvent event) throws IOException
    {
        SwitchScene s= new SwitchScene();
        s.changeSceneTo(event,"FacultySubscribeCourses.fxml");
    }

    public void openUnsubscribe(ActionEvent event) throws IOException
    {
        SwitchScene s= new SwitchScene();
        s.changeSceneTo(event,"FacultyUnsubscribeCourses.fxml");
    }

    public void openSubscribedByMe(ActionEvent event) throws Exception
    {
        SwitchScene s= new SwitchScene();
        s.changeSceneTo(event,"FacultySubscribedByMe.fxml");
    }
    public void back(ActionEvent event)throws IOException
    {

        SwitchScene s=new SwitchScene();
        s.changeSceneTo(event,"FacultyHome.fxml");
    }


}
