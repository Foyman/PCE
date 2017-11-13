/**
 * JUnit tests for the Course class
 * 
 * @author Collin Wong
 * @version NGA-JUnit
 */
package test;

import static org.junit.Assert.*;
import logic.Course;
import org.junit.Test;

public class TestCourse {

	@Test
	public void testConstructor_Name() 
	{
		Course c = new Course("Collin");
		assertEquals("Collin", c.name);
	}
	
	@Test
	public void testConstructor_Distance()
	{
		Course c = new Course(null);
		assertEquals(Integer.MAX_VALUE, c.distance);
	}

}