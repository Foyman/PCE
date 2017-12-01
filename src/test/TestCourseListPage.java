/**
 * JUnit tests for the CourseListPage class
 * 
 * @author Bibek Shrestha
 */
package test;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import javax.swing.*;
import logic.*;

public class TestCourseListPage
{
    @Test
    public void testcreateFrame()
    {
        Search.readCourses();
        List<JComponentWithLayout> clPage = CourseListPage.createFrame(Search.getCourses());
        assertEquals(3, clPage.size());
    }
}
