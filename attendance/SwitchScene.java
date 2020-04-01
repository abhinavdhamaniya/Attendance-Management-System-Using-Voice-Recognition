package attendance;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class SwitchScene {

    public void changeSceneTo(ActionEvent event, String file) throws IOException
    {
        Parent facProfile = FXMLLoader.load(getClass().getResource(file));
        Stage window= (Stage)((Node) event.getSource()).getScene().getWindow();
        window.getScene().setRoot((facProfile));
        window.show();
    }

}
