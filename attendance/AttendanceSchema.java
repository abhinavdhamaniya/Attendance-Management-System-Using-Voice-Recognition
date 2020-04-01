package attendance;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import java.util.Date;

public class AttendanceSchema {

    private SimpleStringProperty sno, regno, name, ccode;
    private Object action;
    private String date;

    public AttendanceSchema(SimpleStringProperty sno, SimpleStringProperty regno, SimpleStringProperty name, SimpleStringProperty ccode, Object action, String date) {
        this.sno = sno;
        this.regno = regno;
        this.name = name;
        this.ccode = ccode;
        this.action = action;
        this.date = date;
    }

    public String getSno() {
        return sno.get();
    }

    public SimpleStringProperty snoProperty() {
        return sno;
    }

    public String getRegno() {
        return regno.get();
    }

    public SimpleStringProperty regnoProperty() {
        return regno;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getCcode() {
        return ccode.get();
    }

    public SimpleStringProperty ccodeProperty() {
        return ccode;
    }

    public Object getAction() {
        return action;
    }

    public String getDate() {
        return date;
    }

    public void setSno(String sno) {
        this.sno.set(sno);
    }

    public void setRegno(String regno) {
        this.regno.set(regno);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setCcode(String ccode) {
        this.ccode.set(ccode);
    }

    public void setAction(Object action) {
        this.action=action;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
