/**
 * JUnit tests for the Course class
 * 
 * @author Jake Veazey
 */
package test;

import static org.junit.Assert.*;
import logic.StudentReview;
import org.junit.Test;

public class TestStudentReview {

	@Test
	public void testConstructor() 
	{
		StudentReview r = new StudentReview(4.5, 2.5, 3.5, 
            "A", "THIS IS MY REVIEW", "Course");
		assertEquals(4.5, r.getCriteria(1), .0);
		assertEquals(2.5, r.getCriteria(2), .0);
		assertEquals(3.5, r.getCriteria(3), .0);
		assertEquals("A", r.getGrade());
		assertEquals("THIS IS MY REVIEW", r.getReview());
		assertEquals("Course", r.getCourse());
		
	}

}