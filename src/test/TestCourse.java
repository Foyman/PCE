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
	public void testConstructorName() 
	{
		Course c = new Course("Collin", "Hey");
		assertEquals("Collin", c.getName());
	}
	
	@Test
	public void testConstructorDistance()
	{
		Course c = new Course(null, null);
		assertEquals(Integer.MAX_VALUE, c.getDistance());
	}

}