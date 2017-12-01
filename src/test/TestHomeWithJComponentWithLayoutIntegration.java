/**
 * @author Jake Veazey
 */

package test;

import static org.junit.Assert.*;

import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;

import org.junit.jupiter.api.Test;

import logic.Home;
import logic.JComponentWithLayout;



public class TestHomeWithJComponentWithLayoutIntegration {

    private JComponent component;
    
	@Test
	public void testJComponentCreationBorder() {
        JFrame frame = new JFrame("PolyRatings Course Edition");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JComponentWithLayout jcwl = new JComponentWithLayout(component, "North");
		
		JComponentWithLayout jcwl2 = Home.createFrame(frame).get(0);
		
		assertEquals(jcwl.getBorderLayout(), jcwl2.getBorderLayout());
		
	}

}
