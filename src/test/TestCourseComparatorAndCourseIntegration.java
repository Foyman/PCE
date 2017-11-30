/**
 * Integration tests for the Course and CourseComparator classes
 * 
 * @author Collin Wong
 */

package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import logic.Course;
import logic.CourseComparator;

public class TestCourseComparatorAndCourseIntegration
{
	
	public static final String FUND_STR = "Fundamentals of Computer Science";
    @Test
    public void testCourseSort()
    {
        Course c1 = new Course("FIRST", FUND_STR);
        c1.setDistance(100);
        Course c2 = new Course("THIRD", FUND_STR);
        c2.setDistance(300);
        Course c3 = new Course("SECOND", FUND_STR);
        c3.setDistance(200);
        
        List<Course> list = new ArrayList<Course>(3);
        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.sort(new CourseComparator());
        
        assertEquals(c3.getName(), list.get(1).getName());
    }
}
