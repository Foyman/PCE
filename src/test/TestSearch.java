/**
 * JUnit tests for the Search class
 * 
 * @author Connor Witzeman
 */

package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import logic.Search;

class TestSearch
{

    @Test
    void testResetCourses()
    {
        Search.resetCourses();
        assertEquals(null, Search.getCourses());
    }
    
    @Test
    void testSearchReadCoursesCalledOnce()
    {
        Search.resetCourses();
        Search.readCourses();
        assertTrue(Search.getCourses().size() > 0);
    }

    @Test
    void testSearchReadCourseCalledTwice()
    {
        Search.resetCourses();
        Search.readCourses();
        Search.readCourses();
        assertTrue(Search.getCourses().size() > 0);
    }
}
