/**
 * JUnit tests for the Home class
 * 
 * @author Bibek Shrestha
 */
package test;
import static org.junit.Assert.assertEquals;
import java.sql.*;
import java.util.ArrayList;

import org.junit.Test;

import logic.Course;
import logic.DBConnect;
import logic.Home;
import logic.StudentReview;

public class TestHome
{
    @Test
    public void testmakeReviews()
    {
        DBConnect.connectToDB();
        String query = "SELECT * FROM Reviews WHERE ReviewId = 5;";
        ArrayList<StudentReview> rExpected = new ArrayList<StudentReview>();
        ArrayList<StudentReview> rActual = new ArrayList<StudentReview>();
        rExpected.add(new StudentReview(2.9, 3.8, 3.4, "B",  "Eeeeeee", "ERSC 339"));
        try
        {
            ResultSet rs = DBConnect.processGeneralQuery(query);
            rActual.addAll(Home.makeReviews(rs, "ERSC 339"));
        } catch (SQLException e) {
            return;
        }
        
        assertEquals(rExpected, rActual);
    }
    
    @Test
    public void testmakeCourses()
    {
        DBConnect.connectToDB();
        String query = "SELECT * FROM Course WHERE CourseId = 1;";
        ArrayList<Course> cExpected = new ArrayList<Course>();
        ArrayList<Course> cActual = new ArrayList<Course>();
        cExpected.add((new Course ("AERO 121", "AEROSPACE FUNDAMENTALS")));
        try
        {
            ResultSet rs = DBConnect.processGeneralQuery(query);
            cActual.addAll(Home.makeCourses(rs));
        } catch (SQLException e) {
            return;
        }
        
        assertEquals(cExpected, cActual);
    }
}
