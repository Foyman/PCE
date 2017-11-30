package logic;

import java.sql.*;
import java.util.List;
import java.util.logging.Logger;

public class DBConnect
{
    private static Connection conn;
	 private static final Logger LOGGER = Logger.getLogger( DBConnect.class.getName() );

	 //For SonarQube
	 private DBConnect()
	 {
		 
	 }
	 
    public static void buildCourse() throws SQLException
    {
        Search.readCourses();
        int i = 1;
        List<Course> courses = Search.getCourses();
        for (Course course : courses)
        {
            String courseShort = course.getName();
            String dept = courseShort.split("\\s")[0].toUpperCase();
            String courseNum = courseShort.split("\\s")[1].toUpperCase();
            String courseName = course.getDescription().toUpperCase();
            String query = String.format("INSERT INTO Course VALUES (%d, \"%s\", %s, \"%s\");", i, dept, courseNum,
                    courseName);
            processUpdateQuery(query);
            i++;
        }

    }

    public static void processUpdateQuery(String query) throws SQLException
    {
        Statement s = conn.createStatement();
        s.executeUpdate(query);
    }

    public static ResultSet processGeneralQuery(String query) throws SQLException
    {
        Statement s = conn.createStatement();
        return s.executeQuery(query);
    }

    public static void connectToDB()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").getConstructor().newInstance();
        } catch (Exception ex)
        {
      	  		String temp = "Driver not found" + ex.getMessage();
            LOGGER.info(temp);
        }

        String uId = "root";
        String pass = null;
        String dataB = "PCE";
        String url = "jdbc:mysql://localhost:3306/" + dataB + "?";
        conn = null;

        try
        {
            conn = DriverManager.getConnection(url, uId, pass);
        } catch (Exception ex)
        {
      	  		String temp = "Could not open connection" + ex.getMessage();
           LOGGER.info(temp);
        }
        if (conn != null) {
      	  LOGGER.info("Connected");           
        }
    }
}
