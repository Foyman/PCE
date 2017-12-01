/**
 * JUnit tests for the CourseReviewPage's get functions
 * 
 * @author Jack Foy
 */

package test;

import static org.junit.Assert.*;
import java.util.*;
import javax.swing.ImageIcon;
import logic.CourseReviewPage;
import logic.StudentReview;

import org.junit.jupiter.api.Test;

public class TestCourseReviewPageGetFunctions
{
    @Test
    public void testGetReview()
    {
        assertEquals(CourseReviewPage.getReviews(), new ArrayList<StudentReview>());
    }
    
    @Test
    public void testGetStars1()
    {
        assertEquals(CourseReviewPage.getStars(1.5, 0.5, 1.9).getImage(), new ImageIcon("images/1_star.png").getImage());
    }
    
    @Test
    public void testGetStars2()
    {
        assertEquals(CourseReviewPage.getStars(2.1, 3.0, 3.1).getImage(), new ImageIcon("images/2_star.png").getImage());
    }
    
    @Test
    public void testGetStars3()
    {
        assertEquals(CourseReviewPage.getStars(2.9, 3.7, 3.65).getImage(), new ImageIcon("images/3_star.png").getImage());
    }
    
    @Test
    public void testGetStars4()
    {
        assertEquals(CourseReviewPage.getStars(4.0, 4.0, 4.0).getImage(), new ImageIcon("images/4_star.png").getImage());
    }
    
    @Test
    public void testGetStars0NoAverage()
    {
        assertEquals(CourseReviewPage.getStars(0, 0, 0).getImage(), new ImageIcon("images/0_star.png").getImage());
    }
    
    @Test
    public void testGetStarsHighAverage()
    {
        assertEquals(CourseReviewPage.getStars(5.1, 23.0, 10.5).getImage(), new ImageIcon("images/0_star.png").getImage());
    }
    
    @Test
    public void testGetStarsNegative()
    {
        assertEquals(CourseReviewPage.getStars(-2.1, -3.0, -3.1).getImage(), new ImageIcon("images/0_star.png").getImage());
    }
}
