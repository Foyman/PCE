/**
 * JUnit tests for the EditDistance class
 * 
 * @author Collin Wong
 * @version NGA-JUnit
 */
package test;

import logic.Course;
import logic.EditDistance;
import logic.Type;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestEditDistance 
{

	//@Test
	public void testSortList() 
	{
		String search = "hi";
		Course c1 = new Course("HI", null);
		Course c2 = new Course("HIT", null);
		Course c3 = new Course("HIT!", null);
		List<Course> courses = new ArrayList<Course>(3);
		courses.add(c3);
		courses.add(c1);
		courses.add(c2);
		EditDistance.sortList(search, Type.NAME);
		assertEquals(c3, courses.get(2));
	}
	
	// Each loop (1-3) executed typical number of times
	@Test
	public void testGetDistanceNormal()
	{
		String s1 = "ExpOnential";
		s1 = s1.toLowerCase();
		String s2 = "PolynoMial";
		s2 = s2.toLowerCase();
		assertEquals(6, EditDistance.getDistance(s1, s2));
	}
	
	
	@Test
	public void testGetDistanceOneEmptyString()
	{
		String s1 = "";
		String s2 = "hi";
		assertEquals(2, EditDistance.getDistance(s1,s2));
	}
	
	// Loop Test for Loop 3 -- Don't run
	// Loop Test n = 1 for Loop 1 and Loop 2
	@Test
	public void testGetDistanceBothEmptyStrings()
	{
		String s1 = "";
		String s2 = "";
		assertEquals(0, EditDistance.getDistance(s1,s2));
	}
	
	// Loop Test n = 2 for Loop 1 and Loop 2
	@Test
	public void testGetDistanceOneLetter()
	{
		String s1 = "l";
		String s2 = "m";
		assertEquals(1, EditDistance.getDistance(s1,s2));
	}
	
	@Test
	public void testGetDistanceEqual()
	{
		String s1 = "hraeghieorighiauwhroigejrgoijaeriogj";
		String s2 = "hraeghieorighiauwhroigejrgoijaeriogj";
		assertEquals(0, EditDistance.getDistance(s1,s2));
	}
	

}