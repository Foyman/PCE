/**
 * @author Jake Veazey
 */

package test;

import static org.junit.Assert.*;

import javax.swing.JComponent;

import org.junit.jupiter.api.Test;

import logic.CourseListPage;
import logic.JComponentWithLayout;
import logic.Search;


public class TestCourseListPageWithJComponentWithLayout {

    private JComponent component;
	
	@Test
	public void testCourseListPageWithJComponent() {
        Search.readCourses();
		JComponentWithLayout jcwl = new JComponentWithLayout(component, "North");		
		JComponentWithLayout jcwl2 = CourseListPage.createFrame(Search.getCourses()).get(0);

	
		assertEquals(jcwl.getBorderLayout(), jcwl2.getBorderLayout());
	
	}

}
