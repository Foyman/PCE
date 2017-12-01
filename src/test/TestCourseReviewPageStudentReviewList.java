/**
 * JUnit tests for the CourseReviewPage's overall scores functions
 * 
 * @author Jack Foy
 */

package test;

import static org.junit.Assert.*;
import java.util.*;
import logic.CourseReviewPage;
import logic.StudentReview;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCourseReviewPageStudentReviewList
{
    private static StudentReview sra = new StudentReview(4.5, 2.5, 3.5, "A", "Review", "Course");
    private static StudentReview srb = new StudentReview(1.1, 2.2, 4.0, "B", "Review", "Course");
    private static StudentReview src = new StudentReview(2.9, 3.1, 3.0, "C", "Review", "Course");
    private static List<StudentReview> list = new ArrayList<StudentReview>();
    private static List<StudentReview> empty = new ArrayList<StudentReview>();

    @BeforeEach
    public void initList()
    {
        list.add(sra);
        list.add(srb);
        list.add(src);
    }

    @Test
    public void testAverageCriteria1()
    {
        assertEquals(CourseReviewPage.averageCriteria(1, list), 2.83, .01);
    }

    @Test
    public void testAverageCriteria2()
    {
        assertEquals(CourseReviewPage.averageCriteria(2, list), 2.6, .01);
    }

    @Test
    public void testAverageCriteria3()
    {
        assertEquals(CourseReviewPage.averageCriteria(3, list), 3.5, .01);
    }

    @Test
    public void testAverageCriteriaEmtpy()
    {
        assertEquals(CourseReviewPage.averageCriteria(1, empty), 0, .01);
    }

    @Test
    public void testOverallGrade()
    {
        assertEquals(CourseReviewPage.calculateOverallGrade(list), "B");
    }

    @Test
    public void testOverallGradeEmpty()
    {
        assertEquals(CourseReviewPage.calculateOverallGrade(empty), "No grade yet");
    }
}
