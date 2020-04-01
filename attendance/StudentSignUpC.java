package attendance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentSignUpC {

    @FXML private TextField regno, fname, lname, email, pass, passc, phone, branch;
    @FXML private TextArea addr;
    public void doSignUp(ActionEvent event)
    {
        String errors= "";
        boolean duplicateUsername=false;

        String u=regno.getText();
        String f= fname.getText();
        String l= lname.getText();
        String e= email.getText();
        String p= pass.getText();
        String pc= passc.getText();
        String ph= phone.getText();
        String b= branch.getText();
        String a= addr.getText();

        Pattern alphabetsP= Pattern.compile("[A-Za-z]*");
        Pattern emailP= Pattern.compile("[A-Za-z0-9]*@[A-Za-z]*.com");
        Pattern phoneP= Pattern.compile("[0-9]{10}");



        if(u.equals("") || f.equals("") || l.equals("") || e.equals("") || p.equals("") || pc.equals("") || ph.equals("") || b.equals("") || a.equals(""))
        {
            errors+="Please fill all the fields.\n";
        }

        Matcher alphabetsM= alphabetsP.matcher(f);
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

            String query= "SELECT * FROM student WHERE regno='"+ u+"'";
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
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setContentText(errors);
            alert.show();
        }

        else if(duplicateUsername)
        {
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Username already exists.");
            alert.show();
        }

        else{
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://localhost:1234/Attendance", "root", "");

                String query= "INSERT INTO student VALUES('"+ regno.getText()+"','"+ fname.getText().toUpperCase() +"','"+ lname.getText()+ "','"+ email.getText() + "','"+phone.getText() +"','"+addr.getText() + "','"+branch.getText() +"','"+pass.getText()+"')";
                PreparedStatement ps= con.prepareStatement(query);
                int count= ps.executeUpdate(query);
                if(count==1)System.out.println("Student Sign-Up Successful");

                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Student Sign Up Successful.");
                alert.show();

                alert.setOnCloseRequest(event2->{
                    try {
                        SwitchScene sw= new SwitchScene();
                        sw.changeSceneTo(event, "StudentLogin.fxml");
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
        s.changeSceneTo(event,"StudentLogin.fxml");
    }

}
