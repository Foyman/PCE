package logic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeaderFactory
{
	private static final String ARIAL = "Arial";
	
    private HeaderFactory()
    {

    }

    public static JPanel createHeader(String headerLabel)
    {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(7, 88, 64));

        // Everything for header below
        header.add(Box.createHorizontalStrut(20));
   
        JButton homeButton = new JButton("PCE");
        homeButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                FrameController.goHome();
            }
        });
        homeButton.setBorderPainted(false);
        homeButton.setContentAreaFilled(false);
        homeButton.setFocusPainted(false);
        homeButton.setOpaque(false);
        homeButton.setForeground(Color.WHITE);
        homeButton.setFont(new Font(ARIAL, Font.BOLD, 40));
        header.setBackground(new Color(7, 88, 64));
        header.add(homeButton, BorderLayout.WEST);

        
        JLabel headerText = new JLabel(headerLabel);
        headerText.setForeground(Color.WHITE);
        headerText.setFont(headerText.getFont().deriveFont(40.0f));
        headerText.setHorizontalAlignment(JLabel.CENTER);
        header.add(headerText, BorderLayout.CENTER);   
        
        
        JPanel subPanel = new JPanel(/*new BorderLayout()*/);
        subPanel.setBackground(new Color(7, 88, 64));
        
        
        // Course List button
        JButton courseListButton = new JButton("Course List");
        courseListButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Search.readCourses();
                FrameController.changeFrame(CourseListPage.createFrame(Search.getCourses()));
            }
        });
        courseListButton.setBorderPainted(false);
        courseListButton.setContentAreaFilled(false);
        courseListButton.setFocusPainted(false);
        courseListButton.setOpaque(false);
        courseListButton.setForeground(Color.WHITE);
        courseListButton.setFont(new Font(ARIAL, Font.BOLD, 20));
        subPanel.add(courseListButton);
        
        
        
        // Evaluate Course button
        JButton evaluateButton = new JButton("Evaluate a Course");
        evaluateButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Search.readCourses();
                FrameController.changeFrame(NotImplementedPage.createFrame());
            }
        });

        evaluateButton.setBorderPainted(false);
        evaluateButton.setContentAreaFilled(false);
        evaluateButton.setFocusPainted(false);
        evaluateButton.setOpaque(false);
        evaluateButton.setForeground(Color.WHITE);
        evaluateButton.setFont(new Font(ARIAL, Font.BOLD, 20));
        subPanel.add(evaluateButton);

        // FAQ button
        JButton faqButton = new JButton("FAQ");
        faqButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Search.readCourses();
                FrameController.changeFrame(NotImplementedPage.createFrame());
            }
        });
        faqButton.setBorderPainted(false);
        faqButton.setContentAreaFilled(false);
        faqButton.setFocusPainted(false);
        faqButton.setOpaque(false);
        faqButton.setForeground(Color.WHITE);
        faqButton.setFont(new Font(ARIAL, Font.BOLD, 20));
        subPanel.add(faqButton);
        
        header.add(subPanel, BorderLayout.EAST);
        

        return header;
    }
    
    
}
