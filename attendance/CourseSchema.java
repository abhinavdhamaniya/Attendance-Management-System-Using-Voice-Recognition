package attendance;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class CourseSchema {

    private SimpleStringProperty sno, code, name, desc, credits, type, subscribedBy;
    private Object action;

    public CourseSchema(SimpleStringProperty sno, SimpleStringProperty code, SimpleStringProperty name, SimpleStringProperty desc, SimpleStringProperty credits, SimpleStringProperty type, SimpleStringProperty subscribedBy, Object action) {
        this.sno = sno;
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.credits = credits;
        this.type = type;
        this.subscribedBy = subscribedBy;
        this.action = action;
    }

    public CourseSchema(SimpleStringProperty sno, SimpleStringProperty code, SimpleStringProperty name, SimpleStringProperty desc, SimpleStringProperty credits, SimpleStringProperty type, SimpleStringProperty subscribedBy) {
        this.sno = sno;
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.credits = credits;
        this.type = type;
        this.subscribedBy = subscribedBy;
    }

    public String getSno() {
        return sno.get();
    }

    public SimpleStringProperty snoProperty() {
        return sno;
    }

    public String getCode() {
        return code.get();
    }

    public SimpleStringProperty codeProperty() {
        return code;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getDesc() {
        return desc.get();
    }

    public SimpleStringProperty descProperty() {
        return desc;
    }

    public String getCredits() {
        return credits.get();
    }

    public SimpleStringProperty creditsProperty() {
        return credits;
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public String getSubscribedBy() {
        return subscribedBy.get();
    }

    public SimpleStringProperty subscribedByProperty() {
        return subscribedBy;
    }

    public Object getAction() {
        return action;
    }

    public void setSno(String sno) {
        this.sno.set(sno);
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setDesc(String desc) {
        this.desc.set(desc);
    }

    public void setCredits(String credits) {
        this.credits.set(credits);
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public void setSubscribedBy(String subscribedBy) {
        this.subscribedBy.set(subscribedBy);
    }

    public void setAction(Object action) {
        this.action=action;
    }
}
