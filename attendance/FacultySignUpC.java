package attendance;
import javafx.css.Match;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FacultySignUpC {

    @FXML private TextField username, fname, lname, email, pass, passc, phone;

    public void doSignUp(ActionEvent event)
    {

        String errors= "";
        boolean duplicateUsername=false;
        String u=username.getText();
        String f= fname.getText();
        String l= lname.getText();
        String e= email.getText();
        String p= pass.getText();
        String pc= passc.getText();
        String ph= phone.getText();

        Pattern alphabetsP= Pattern.compile("[A-Za-z]*");
        Pattern emailP= Pattern.compile("[A-Za-z0-9]*@[A-Za-z]*.com");
        Pattern phoneP= Pattern.compile("[0-9]{10}");

        Matcher alphabetsM= alphabetsP.matcher(f);

        if(u.equals("") || f.equals("") || l.equals("") || e.equals("") || p.equals("") || pc.equals("") || ph.equals(""))
        {
            errors+="Please fill all the fields.\n";
        }

        if(!alphabetsM.matches()) errors+="First name should only contains alphabets.\n";

        alphabetsM= alphabetsP.matcher(l);
        if(!alphabetsM.matches()) errors+="Last name should only contains alphabets.\n";

        Matcher emailM= emailP.matcher(e);
        if(!emailM.matches()) errors+="Invalid email.\n";

        if(!p.equals(pc)) errors+="Passwords do not match.\n";

        Matcher phoneM= phoneP.matcher(ph);
        if(!phoneM.matches()) errors+="Invalid phone number.\n";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:1234/Attendance", "root", "");

            String query= "SELECT * FROM faculty WHERE username='"+ u+"'";
            Statement st= con.createStatement();
            ResultSet rs= st.executeQuery(query);
            if(rs.next())
            {
                duplicateUsername=true;
            }
        }
        catch (Exception exc)
        {
            System.out.println(exc);
        }

        if(!errors.equals(""))
        {
            Alert a= new Alert(Alert.AlertType.ERROR);
            a.setContentText(errors);
            a.show();
        }

        else if(duplicateUsername)
        {
            Alert a= new Alert(Alert.AlertType.ERROR);
            a.setContentText("Username already exists.");
            a.show();
        }

        else
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://localhost:1234/Attendance", "root", "");

                String query= "INSERT INTO faculty VALUES('"+ username.getText()+"','"+ fname.getText() +"','"+ lname.getText()+ "','"+ email.getText() + "','"+pass.getText() +"','"+phone.getText()+"')";
                PreparedStatement ps= con.prepareStatement(query);
                int count= ps.executeUpdate(query);
                System.out.println("Faculty Sign Up Successful.");

                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Faculty Sign Up Successful.");
                a.show();

                a.setOnCloseRequest(event2->{
                    try {
                        SwitchScene sw= new SwitchScene();
                        sw.changeSceneTo(event, "FacultyLogin.fxml");
                    }
                    catch (IOException io)
                    {
                        System.out.println(io);
                    }

                });
                con.close();
            }
            catch (ClassNotFoundException exc)
            {
                Alert err= new Alert(Alert.AlertType.ERROR);
                err.setContentText("Driver error.");
                err.show();
            }
            catch (SQLException exc)
            {
                Alert err= new Alert(Alert.AlertType.ERROR);
                err.setContentText("Database error occurred.");
                err.show();
            }
        }

    }

    public void back(ActionEvent event)throws IOException
    {

        SwitchScene s=new SwitchScene();
        s.changeSceneTo(event,"FacultyLogin.fxml");
    }


}
