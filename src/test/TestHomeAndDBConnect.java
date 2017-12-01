/**
 * Integration tests for the Home and DBConnect classes
 * 
 * @author Bibek Shrestha
 */
package test;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import logic.Course;
import logic.DBConnect;
import logic.Home;
import logic.StudentReview;

public class TestHomeAndDBConnect
{
    @Test
    public void testmakeReviews()
    {
        DBConnect.connectToDB();
        String query = "SELECT * FROM Reviews ORDER BY ReviewId LIMIT 1;";
        ArrayList<StudentReview> rExpected = new ArrayList<StudentReview>();
        ArrayList<StudentReview> rActual = new ArrayList<StudentReview>();
        rExpected.add(new StudentReview(3.4, 3.0, 2.8, "A","This course is intense. It's worth it to get a taste of what you're going to experience in the industry", "CSC 307"));
        try
        {
            ResultSet rs = DBConnect.processGeneralQuery(query);
            rActual.addAll(Home.makeReviews(rs, "CSC 307"));
        } catch (SQLException e) {
            return;
        }

        assertEquals(rExpected, rActual);
    }

    @Test
    public void testmakeCourses()
    {
        DBConnect.connectToDB();
        String query = "SELECT * FROM Course WHERE dept = \"CSC\" AND courseNum = 308;";
        ArrayList<Course> cExpected = new ArrayList<Course>();
        ArrayList<Course> cActual = new ArrayList<Course>();
        cExpected.add((new Course ("CSC 308", "SOFTWARE ENGINEERING I")));
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
