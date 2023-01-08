package DB;



import java.sql.Connection;
import java.sql.DriverManager;

public class Singleton1 {

       private static Connection instance;

    Singleton1() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            instance = DriverManager.getConnection("jdbc:mysql://localhost:3306/securitydb?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "root");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("database can't connecte !!!!!!!!");
        }
    }
    public static Connection getInstance ()
    {
        if (instance==null)
            new Singleton1 ();
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(Singleton1.getInstance());
    }
    
}
