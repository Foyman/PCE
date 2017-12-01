package test;

import static org.junit.Assert.*;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import javax.swing.JComponent;
import javax.swing.JFrame;

import logic.Home;
import logic.JComponentWithLayout;



public class TestHomeWithJComponentWithLayoutIntegration {

    private JComponent component;
    //private final String borderLayout;
    private JFrame frame; 
    
	@Test
	public void testJComponentCreationBorder() {
        frame = new JFrame("PolyRatings Course Edition");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JComponentWithLayout JCWL = new JComponentWithLayout(component, "North");
		
		JComponentWithLayout JCWL2 = Home.createFrame(frame).get(0);
		
		assertEquals(JCWL.getBorderLayout(), JCWL2.getBorderLayout());
		
	}

}
