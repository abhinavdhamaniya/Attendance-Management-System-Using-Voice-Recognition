package attendance;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;

public class FacultyProfileSchema {

        private SimpleStringProperty property,value;

        public FacultyProfileSchema(String property, String value) {
            this.property = new SimpleStringProperty(property);
            this.value = new SimpleStringProperty(value);
        }

        public String getProperty() {
            return property.get();
        }

        public SimpleStringProperty propertyProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property.set(property);
        }

        public String getValue() {
            return value.get();
        }

        public SimpleStringProperty valueProperty() {
            return value;
        }

        public void setValue(String value) {
            this.value.set(value);
        }
    }
