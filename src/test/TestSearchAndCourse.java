/**
 * Integration tests for Search and Course classes
 * 
 * @author Connor Witzeman
 */

package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import logic.Search;
import logic.Course;
import java.util.List;

import org.junit.jupiter.api.Test;


class TestSearchAndCourse
{

    @Test
    void testCourseEqualsWithSearch()
    {
        Search.resetCourses();
        Search.readCourses();
        
        List<Course> courses = Search.getCourses();
        Course c1 = courses.get(0);
        Course c2 = courses.get(1);
        assertFalse(c1.equals(c2));
    }
    
    @Test
    void testSearchAndChangeCourseDescription()
    {
        Search.resetCourses();
        Search.readCourses();
        
        List<Course> courses = Search.getCourses();
        courses.get(0).setDescription("changed");
        assertEquals(courses.get(0).getDescription(), "changed");
    }
}
