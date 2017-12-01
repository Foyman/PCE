package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import logic.Course;
import logic.Search;

class TestCourseAndSearch
{

    @Test
    void testSearchGetCoursesReturnsCourses()
    {
        Search.readCourses();
        List<Course> courses = Search.getCourses();
        boolean allCoursesHaveTypeCourse = true;
        for (Course c : courses)
        {
            boolean hasType = c instanceof Course;
            boolean hasName = c.getName() instanceof String;
            boolean hasDescription = c.getDescription() instanceof String;
            boolean hasDistance = c.getDistance() >= 0;
            boolean isCourse = hasType && hasName && hasDescription && hasDistance;
            allCoursesHaveTypeCourse &= isCourse;
        }
        assertTrue(allCoursesHaveTypeCourse);
    }
}
