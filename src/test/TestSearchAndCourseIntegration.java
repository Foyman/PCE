/**
 * Integration tests for the Course and CourseComparator classes
 * 
 * @author Collin Wong
 */

package test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;
import logic.Search;

public class TestSearchAndCourseIntegration
{

   
    @Test
    public void testSearchCourses() throws FileNotFoundException
    {
   	 		Search.readCourses();
        assertEquals("aero 121",Search.getCourses().get(0).getName());
    }
    
}
