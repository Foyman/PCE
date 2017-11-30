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

	private static String string1 = "THIS IS MY REVIEW";
	private static String string2 = "Course";
    private static StudentReview r = new StudentReview(4.5, 2.5, 3.5, "A", string1, string2);

	
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
        assertEquals(string1, r.getReview());
    }
      
    @Test
    public void testConstructor5()
    {
        assertEquals(string2, r.getCourse());
    }
    

    @Test
    public void testEmptyConstructor()
    {
        StudentReview r = new StudentReview();
        assertTrue(r instanceof StudentReview);
    }

    @Test
    public void testGetCriteriaWithInvalidCriteriaNumber()
    {
        StudentReview r = new StudentReview(4.5, 2.5, 3.5, "A", "THIS IS MY REVIEW", "Course");
        assertEquals(0.0, r.getCriteria(0), .0);
    }

    @Test
    public void testToString()
    {
        StudentReview r = new StudentReview(4.5, 2.5, 3.5, "A", "THIS IS MY REVIEW", "Course");
        assertEquals("4.5 2.5 3.5 A THIS IS MY REVIEW", r.toString());
    }

}
