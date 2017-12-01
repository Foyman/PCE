/**
 * Integration tests for the Search and DBConnect classes
 * 
 * @author Bibek Shrestha
 */
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import logic.Course;
import logic.DBConnect;
import logic.Home;
import logic.Search;

public class TestSearchAndDBConnect
{
    @Test
    public void testbuildCourse()
    {
        DBConnect.connectToDB();
        Search.readCourses();
        String query2 = "SELECT * FROM Course WHERE CourseId >= 4067;";

        ArrayList<Course> cExpected = (ArrayList<Course>) Search.getCourses();
        ArrayList<Course> cActual = new ArrayList<Course>();
        try
        {
            ResultSet rs = DBConnect.processGeneralQuery(query2);
            cActual.addAll(Home.makeCourses(rs));
        } catch (SQLException e)
        {
            fail("SQL exception");
        }

        assertEquals(cExpected.subList(cExpected.size() - 5, cExpected.size()), cActual);
    }
}
