/**
 * Integration test for CourseReviewPage and StudentReview classes
 * 
 * @author Jack Foy
 */

package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import logic.CourseReviewPage;
import logic.StudentReview;

public class TestCourseReviewPageAndStudentReviewIntegration
{
    private static List<StudentReview> list = new ArrayList<StudentReview>();
    private static final String REVIEW = "Review";
    private static final String COURSE = "Course";
    
    @Test
    public void testOverallGrade()
    {
        StudentReview sr1 = new StudentReview(4.5, 2.5, 3.5, "A", REVIEW, COURSE);
        StudentReview sr2 = new StudentReview(1.1, 2.2, 4.0, "B", REVIEW, COURSE);
        StudentReview sr3 = new StudentReview(2.9, 3.1, 3.0, "C", REVIEW, COURSE);
        StudentReview sr4 = new StudentReview(3.3, 2.1, 1.5, "D", REVIEW, COURSE);
        StudentReview sr5 = new StudentReview(0.5, 1.1, 0.9, "F", REVIEW, COURSE);
        StudentReview sr6 = new StudentReview(2.5, 2.4, 2.3, "C", REVIEW, COURSE);
        StudentReview sr7 = new StudentReview(3.7, 3.2, 3.8, "A", REVIEW, COURSE);
        list.add(sr1);
        list.add(sr2);
        list.add(sr3);
        list.add(sr4);
        list.add(sr5);
        list.add(sr6);
        list.add(sr7);
        assertEquals(CourseReviewPage.calculateOverallGrade(list), "C");
    }

    @Test
    public void testAverageCriteria()
    {
        assertEquals(CourseReviewPage.averageCriteria(1, list), 2.64, .01);
    }
}
