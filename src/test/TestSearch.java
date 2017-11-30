/**
 * JUnit tests for the Search class
 * 
 * @author Connor Witzeman
 */

package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
        assertTrue(Search.getCourses().isEmpty());
    }

    @Test
    void testSearchReadCourseCalledTwice()
    {
        Search.resetCourses();
        Search.readCourses();
        Search.readCourses();
        assertTrue(Search.getCourses().isEmpty());
    }
}
