/**
 * Integration tests for the Course and CourseComparator classes
 * 
 * @author Collin Wong
 */

package test;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import logic.Search;

public class TestSearchAndCourseIntegration
{

    @Test
    public void testSearchCourses()
    {
        Search.readCourses();
        assertTrue(Search.getCourses().get(0).getName() instanceof String);
    }
}
