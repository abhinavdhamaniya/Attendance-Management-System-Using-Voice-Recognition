package attendance;

import javafx.event.ActionEvent;

import java.io.IOException;

public class StudentManageCoursesC {

    public void openSubscribe(ActionEvent event) throws IOException
    {
        SwitchScene s= new SwitchScene();
        s.changeSceneTo(event,"StudentRegisterCourses.fxml");
    }

    public void openUnsubscribe(ActionEvent event) throws IOException
    {
        SwitchScene s= new SwitchScene();
        s.changeSceneTo(event,"StudentDropCourses.fxml");
    }

    public void openSubscribedByMe(ActionEvent event) throws Exception
    {
        SwitchScene s= new SwitchScene();
        s.changeSceneTo(event,"StudentRegisteredByMe.fxml");
    }
    public void back(ActionEvent event)throws IOException
    {

        SwitchScene s=new SwitchScene();
        s.changeSceneTo(event,"StudentHome.fxml");
    }


}
