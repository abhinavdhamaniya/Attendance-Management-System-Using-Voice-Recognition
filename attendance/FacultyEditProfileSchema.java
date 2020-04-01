package attendance;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;

public class FacultyEditProfileSchema {

    private SimpleStringProperty property;
    private TextField value;

    public FacultyEditProfileSchema(SimpleStringProperty property, TextField value) {
        this.property = property;
        this.value = value;
    }

    public void setProperty(String property) {
        this.property.set(property);
    }

    public void setValue(TextField value) {
        this.value = value;
    }

    public String getProperty() {
        return property.get();
    }

    public SimpleStringProperty propertyProperty() {
        return property;
    }

    public TextField getValue() {
        return value;
    }
}
