/**
 * JUnit tests for the JComponentWithLayout class
 * 
 * @author Tim Stoddard
 */

package test;

import static org.junit.Assert.assertEquals;
import java.awt.*;
import javax.swing.*;

import org.junit.Test;

import logic.JComponentWithLayout;

class TestJComponentWithLayout
{

    class TestComponent extends JComponent
    {
        private TestComponent()
        {

        }
    }

    @Test
    void testConstructor()
    {
        JComponent comp = new TestComponent();
        String borderLayout = BorderLayout.NORTH;
        JComponentWithLayout compWithLayout = new JComponentWithLayout(comp, borderLayout);
        assertEquals(borderLayout, compWithLayout.getBorderLayout());
    }

    @Test
    void testAddToFrame()
    {
        JComponent comp = new TestComponent();
        String borderLayout = BorderLayout.NORTH;
        JComponentWithLayout compWithLayout = new JComponentWithLayout(comp, borderLayout);
        JFrame frame = new JFrame();
        compWithLayout.addToFrame(frame);
        assertEquals(1, frame.getContentPane().getComponentCount());
    }

}
