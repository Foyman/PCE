/**
 * JUnit tests for the Course class
 * 
 * @author Jake Veazey
 */

package test;

import static org.junit.Assert.*;
import logic.StudentReview;

import org.junit.jupiter.api.Test;


public class TestStudentReview
{

	private static final String STRING_1 = "THIS IS MY REVIEW";
	private static final String STRING_2 = "Course";
    private static StudentReview r = new StudentReview(4.5, 2.5, 3.5, "A", STRING_1, STRING_2);

	
    @Test
    public void testConstructor0()
    {
        assertEquals(4.5, r.getCriteria(1), .0);
    }
    
    @Test
    public void testConstructor1()
    {
        assertEquals(2.5, r.getCriteria(2), .0);
    }
    @Test
    public void testConstructor2()
    {
        assertEquals(3.5, r.getCriteria(3), .0);
    }
    @Test
    public void testConstructor3()
    {
        assertEquals("A", r.getGrade());
    }
    @Test
    public void testConstructor4()
    {
        assertEquals(STRING_1, r.getReview());
    }
      
    @Test
    public void testConstructor5()
    {
        assertEquals(STRING_2, r.getCourse());
    }
    

    @Test
    public void testEmptyConstructor()
    {
        StudentReview review = new StudentReview();
        assertTrue(review instanceof StudentReview);
    }

    @Test
    public void testGetCriteriaWithInvalidCriteriaNumber()
    {
        StudentReview review = new StudentReview(4.5, 2.5, 3.5, "A", STRING_1, STRING_2);
        assertEquals(0.0, review.getCriteria(0), .0);
    }

    @Test
    public void testToString()
    {
        StudentReview review = new StudentReview(4.5, 2.5, 3.5, "A", STRING_1, STRING_2);
        assertEquals("4.5 2.5 3.5 A THIS IS MY REVIEW", review.toString());
    }

}
