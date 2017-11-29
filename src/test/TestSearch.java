/**
 * JUnit tests for the Search class
 * 
 * @author Connor Witzeman
 */

package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import logic.Search;

class TestSearch
{

    @Test
    void testSearchReadCoursesCalledOnce()
    {
        Search.resetCourses();
        assertEquals(null, Search.getCourses());
        Search.readCourses();
        assertTrue(Search.getCourses().size() > 0);
    }

    @Test
    void testSearchReadCourseCalledTwice()
    {
        Search.resetCourses();
        assertEquals(null, Search.getCourses());
        Search.readCourses();
        Search.readCourses();
        assertTrue(Search.getCourses().size() > 0);
    }
}
