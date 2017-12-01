package test;

import static org.junit.Assert.*;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

import logic.Course;
import logic.CourseListPage;
import logic.Home;
import logic.JComponentWithLayout;
import logic.Search;

import org.junit.Test;

public class TestCourseListPageWithJComponentWithLayout {

    private JComponent component;
    //private final String borderLayout;
	
	@Test
	public void TestCourseListPageWithJComponentWithLayout() {
        Search.readCourses();
		JComponentWithLayout JCWL = new JComponentWithLayout(component, "North");		
		JComponentWithLayout JCWL2 = CourseListPage.createFrame(Search.getCourses()).get(0);

	
		assertEquals(JCWL.getBorderLayout(), JCWL2.getBorderLayout());
	
	}

}
