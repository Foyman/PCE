package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.awt.*;
import javax.swing.*;
import logic.JComponentWithLayout;

class TestJComponentWithLayout
{

    class TestComponent extends JComponent
    {
        public TestComponent()
        {

        }
    }

    @Test
    void testConstructor()
    {
        JComponent comp = new TestComponent();
        String borderLayout = BorderLayout.NORTH;
        JComponentWithLayout compWithLayout = new JComponentWithLayout(comp, borderLayout);
        assertEquals(comp, compWithLayout.getPanel());
        assertEquals(borderLayout, compWithLayout.getBorderLayout());
    }

    @Test
    void testAddToFrame()
    {
        JComponent comp = new TestComponent();
        String borderLayout = BorderLayout.NORTH;
        JComponentWithLayout compWithLayout = new JComponentWithLayout(comp, borderLayout);
        JFrame frame = new JFrame();
        assertEquals(0, frame.getContentPane().getComponentCount());
        compWithLayout.addToFrame(frame);
        assertEquals(1, frame.getContentPane().getComponentCount());
    }

}
