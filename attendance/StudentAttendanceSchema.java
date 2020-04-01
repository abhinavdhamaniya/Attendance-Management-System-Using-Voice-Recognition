package attendance;

import javafx.beans.property.SimpleStringProperty;

public class StudentAttendanceSchema {


    private SimpleStringProperty sno,course,date,atten;

    public StudentAttendanceSchema(SimpleStringProperty sno, SimpleStringProperty course, SimpleStringProperty date, SimpleStringProperty atten) {
        this.sno = sno;
        this.course = course;
        this.date = date;
        this.atten = atten;
    }

    public void setSno(String sno) {
        this.sno.set(sno);
    }

    public void setCourse(String course) {
        this.course.set(course);
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public void setAtten(String atten) {
        this.atten.set(atten);
    }

    public String getSno() {
        return sno.get();
    }

    public SimpleStringProperty snoProperty() {
        return sno;
    }

    public String getCourse() {
        return course.get();
    }

    public SimpleStringProperty courseProperty() {
        return course;
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public String getAtten() {
        return atten.get();
    }

    public SimpleStringProperty attenProperty() {
        return atten;
    }
}
