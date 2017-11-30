package test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;
import logic.Home;
public class TestHomeMore {

	
	private static final List<String> deps = Home.getDepartments();	
	
	@Test
	public void testGetDepartmentsAERO() {
		assertEquals(deps.get(1), "AERO");
	}
	
	
	@Test
	public void testGetDepartmentsAGB() {
		assertEquals(deps.get(2), "AGB");
	}
	
	@Test
	public void testGetDepartmentsAEPS() {
		assertEquals(deps.get(3), "AEPS");
	}
	
	@Test
	public void testGetDepartmentsAGC() {
		assertEquals(deps.get(4), "AGC");
	}
	
	@Test
	public void testGetDepartmentsAGED() {
		assertEquals(deps.get(5), "AGED");
	}
	
	@Test
	public void testGetDepartmentsBLANK() {
		assertEquals(deps.get(0), "");
	}

}
