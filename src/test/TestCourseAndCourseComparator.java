/**
 * JUnit tests for the Course and CourseComparator classes
 * 
 * @author Tim Stoddard
 */

package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import logic.Course;
import logic.CourseComparator;

class TestCourseAndCourseComparator
{

    @Test
    void testCompareFirstIsLessThanSecond()
    {
        CourseComparator comparator = new CourseComparator();
        Course course1 = new Course("", "");
        course1.setDistance(10);
        Course course2 = new Course("", "");
        course2.setDistance(20);
        assertEquals(-1, comparator.compare(course1, course2));
    }

    @Test
    void testCompareFirstIsGreaterThanSecond()
    {
        CourseComparator comparator = new CourseComparator();
        Course course1 = new Course("", "");
        course1.setDistance(20);
        Course course2 = new Course("", "");
        course2.setDistance(10);
        assertEquals(1, comparator.compare(course1, course2));
    }

    @Test
    void testCompareCoursesAreEqual()
    {
        CourseComparator comparator = new CourseComparator();
        Course course1 = new Course("", "");
        course1.setDistance(10);
        Course course2 = new Course("", "");
        course2.setDistance(10);
        assertEquals(0, comparator.compare(course1, course2));
    }

}
