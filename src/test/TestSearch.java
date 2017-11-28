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
        try
        {
            Search.readCourses();
        } catch (FileNotFoundException e)
        {
            fail("Course list file was not found.");
        }
        assertTrue(Search.getCourses().size() > 0);
    }

    @Test
    void testSearchReadCourseCalledTwice()
    {
        Search.resetCourses();
        assertEquals(null, Search.getCourses());
        try
        {
            Search.readCourses();
        } catch (FileNotFoundException e)
        {
            fail("Course list file was not found (1st try).");
        }
        try
        {
            Search.readCourses();
        } catch (FileNotFoundException e)
        {
            fail("Course list file was not found (2nd try).");
        }
        assertTrue(Search.getCourses().size() > 0);
    }

}
