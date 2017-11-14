/**
 * JUnit tests for the EditDistance class
 * 
 * @author Collin Wong
 * @version NGA-JUnit
 */
package test;

import logic.Course;
import logic.EditDistance;
import logic.Search;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestEditDistance 
{

	//@Test
	public void testSortList() 
	{
		String search = "hi";
		Course c1 = new Course("HI");
		Course c2 = new Course("HIT");
		Course c3 = new Course("HIT!");
		Search.courses = new ArrayList<Course>(3);
		Search.courses.add(c3);
		Search.courses.add(c1);
		Search.courses.add(c2);
		EditDistance.sortList(search);
		assertEquals(c3, Search.courses.get(2));
	}
	
	// Each loop (1-3) executed typical number of times
	@Test
	public void testGetDistance_Normal()
	{
		String s1 = "ExpOnential";
		s1 = s1.toLowerCase();
		String s2 = "PolynoMial";
		s2 = s2.toLowerCase();
		assertEquals(6, EditDistance.getDistance(s1, s2));
	}
	
	
	@Test
	public void testGetDistance_OneEmptyString()
	{
		String s1 = "";
		String s2 = "hi";
		assertEquals(2, EditDistance.getDistance(s1,s2));
	}
	
	// Loop Test for Loop 3 -- Don't run
	// Loop Test n = 1 for Loop 1 and Loop 2
	@Test
	public void testGetDistance_BothEmptyStrings()
	{
		String s1 = "";
		String s2 = "";
		assertEquals(0, EditDistance.getDistance(s1,s2));
	}
	
	// Loop Test n = 2 for Loop 1 and Loop 2
	@Test
	public void testGetDistance_OneLetter()
	{
		String s1 = "l";
		String s2 = "m";
		assertEquals(1, EditDistance.getDistance(s1,s2));
	}
	
	@Test
	public void testGetDistance_Equal()
	{
		String s1 = "hraeghieorighiauwhroigejrgoijaeriogj";
		String s2 = "hraeghieorighiauwhroigejrgoijaeriogj";
		assertEquals(0, EditDistance.getDistance(s1,s2));
	}
	

}