package logic;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.List;

public class DBConnect
{
    private static Connection conn;

    public static void buildCourse() throws SQLException, FileNotFoundException {
        Search.readCourses();
        int i = 1; 
        List<Course> courses = Search.getCourses();
        for (Course course: courses) {
            String courseShort = course.getName();
            String dept = courseShort.split("\\s")[0].toUpperCase();
            String courseNum = courseShort.split("\\s")[1].toUpperCase();
            String courseName = course.getDescription().toUpperCase();
            String query = String.format("INSERT INTO Course VALUES (%d, \"%s\", %s, \"%s\");",
                    i, dept, courseNum, courseName);
            //System.out.println(query);
            processUpdateQuery(query);
            i++;
        }

    }

    public static void processUpdateQuery(String query) throws SQLException {
        Statement s = conn.createStatement();
        s.executeUpdate(query);
    }

    public static ResultSet processGeneralQuery(String query) throws SQLException {
        Statement s = conn.createStatement();
        ResultSet result = s.executeQuery(query);
        return result;
    }

    public static void connectToDB() {
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }
        catch (Exception ex)
        {
            System.out.println("Driver not found");
            System.out.println(ex);
        };

        String uId = "root";
        String pass = null;
        String dataB = "PCE";

        String url = "jdbc:mysql://localhost:3306/";
        conn = null;
        url += dataB + "?";

        try { 
            conn = DriverManager.getConnection(url, uId, pass);
        }
        catch (Exception ex)
        {
            System.out.println("Could not open connection");
            System.out.println(ex);
        };
        System.out.println("Connected" );

    } 
}
