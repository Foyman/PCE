/**
 * JComponent holds the JComponent and Layout to make it easier to switch
 * between screens in one single Frame
 */

package logic;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class JComponentWithLayout
{

    private JComponent component;
    private final String borderLayout;

    public JComponentWithLayout(JComponent component, String borderLayout)
    {
        this.component = component;
        this.borderLayout = borderLayout;
    }

    public JComponent getPanel()
    {
        return component;
    }

    public String getBorderLayout()
    {
        return borderLayout;
    }

    public void addToFrame(JFrame frame)
    {
        frame.getContentPane().add(component, borderLayout);
    }

}
