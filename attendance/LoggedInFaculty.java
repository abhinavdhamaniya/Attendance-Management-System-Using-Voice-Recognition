package attendance;

public class LoggedInFaculty {

    public static String username = "";
    public static String fname = "";
    public static String lname = "";
    public static String email = "";
    public static String phone = "";
    public static String password = "";

    public static void updateLoggedInFaculty(String username1, String fname1, String lname1, String email1, String phone1, String password1) {
        username = username1;
        fname = fname1;
        lname = lname1;
        email = email1;
        phone = phone1;
        password = password1;
    }
    public static void logout()
    {
        username = "";
        fname = "";
        lname = "";
        email = "";
        phone = "";
        password = "";
    }

}
