package logic;

import java.awt.BorderLayout;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import javax.swing.JFrame;

public class FrameController
{
    // Stack to hold the components of previous pages and the current page
    private static Deque<JComponentWithLayout> components = new ArrayDeque<JComponentWithLayout>();

    // Stack to hold the number of components used on the previous pages and current
    // page
    private static Deque<Integer> numComps = new ArrayDeque<Integer>();

    private static JFrame frame;

    /**
     * Run this to start the program
     * 
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception
    {
        // Frame
        frame = new JFrame("PolyRatings Course Edition");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DBConnect.connectToDB();
        // Set Home Frame
        Home.createFrame(frame);

    }

    /**
     * Removes all the current JComponents from the frame and adds the previous
     * JComponents from the components stack
     * 
     */
    public static void backFrame()
    {
        // Remove Previous Frame Components
        int numToPop = numComps.pop();
        for (int i = 0; i < numToPop; i++)
        {
            components.pop();
        }

        // Return to Old Frame Components
        numToPop = numComps.pop();
        List<JComponentWithLayout> newComps = new ArrayList<JComponentWithLayout>(numToPop);
        for (int i = 0; i < numToPop; i++)
        {
            newComps.add(components.pop());
        }

        // Reuses changeFrame Method
        changeFrame(newComps);
    }

    /**
     * Removes all the current JComponents from the frame and adds the new
     * JComponents from the List newComps.
     * 
     * @param newComps - List of Components to add to the Frame
     */
    public static void changeFrame(List<JComponentWithLayout> newComps)
    {
        // Removes all Components
        frame.getContentPane().removeAll();
        frame.invalidate();

        // Add new Components
        numComps.push(newComps.size());
        for (JComponentWithLayout j : newComps)
        {
            components.push(j);
            j.addToFrame(frame);
        }
        frame.revalidate();
        frame.repaint();
    }
    
    
    
    
    public static void goHome() throws Exception{
    	String[] args = {};
    	main(args);
    	frame.dispose();
    }

}
