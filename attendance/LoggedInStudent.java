package attendance;

public class LoggedInStudent {

    public static String regno = "";
    public static String fname = "";
    public static String lname = "";
    public static String email = "";
    public static String phone = "";
    public static String address = "";
    public static String branch = "";
    public static String password = "";

    public static void updateLoggedInStudent(String regno1, String fname1, String lname1, String email1, String phone1, String addr, String branch1, String password1) {
        regno = regno1;
        fname = fname1;
        lname = lname1;
        email = email1;
        phone = phone1;
        address=addr;
        branch= branch1;
        password = password1;
    }

    public static void logout()
    {
        regno = "";
        fname = "";
        lname = "";
        email = "";
        phone = "";
        password = "";
        address = "";
        branch = "";
    }
}
