/**
 * JUnit tests for the Course class
 * 
 * @author Collin Wong
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
	
	@Test
	public void testGetDescription()
	{
        Course c = new Course("Test", "Course");
        assertEquals("Course", c.getDescription());
    }
	
	@Test
    public void testSetDescription()
    {
        Course c = new Course("", "");
        c.setDescription("test");
        assertEquals("test", c.getDescription());
    }
	
	@Test
    public void testSetName()
    {
        Course c = new Course("", "");
        c.setName("test");
        assertEquals("test", c.getName());
    }

}