package logic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import java.util.List;
import javax.swing.*;
import java.text.*;
import java.util.logging.Logger;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class CourseReviewPage
{
	private static final Logger LOGGER = Logger.getLogger(CourseReviewPage.class.getName());
	private static final String ARIAL = "Arial";
	
	// To Please SonarQube
	private CourseReviewPage()
	{

	}

	public static List<JComponentWithLayout> createFrame(String department, String courseNum, List<StudentReview> reviews)
	{
		List<JComponentWithLayout> panels = new ArrayList<JComponentWithLayout>(3);

		// Panels
		JPanel header = HeaderFactory.createHeader("Course Reviews for " + department + " " + courseNum);
		JPanel main = new JPanel(new GridBagLayout());
		JPanel footer = new JPanel();

		createMain(main, courseNum, reviews);

		// Everything for footer below
		JLabel footText = new JLabel("© 2017 Polyratings Course Edition");
		footText.setForeground(Color.WHITE);
		footer.setBackground(new Color(7, 88, 64));
		footer.add(footText);

		// All panels into frame
		panels.add(new JComponentWithLayout(header, BorderLayout.NORTH));
		panels.add(new JComponentWithLayout(main, BorderLayout.CENTER));
		panels.add(new JComponentWithLayout(footer, BorderLayout.SOUTH));

		return panels;
	}

	public static void createMain(JPanel main, String courseNum, List<StudentReview> reviews)
	{
	    DecimalFormat numberFormat = new DecimalFormat("#.00");		
	    
	    // Constraints used for setting up main
		GridBagConstraints c = new GridBagConstraints();

		JPanel topRight = new JPanel(new BorderLayout());
		topRight.setBackground(new Color(255, 255, 255));

		
		JPanel topLeft = new JPanel(new BorderLayout());
		topLeft.setBackground(new Color(255, 255, 255));

	
		JPanel topMiddle = new JPanel(new BorderLayout());
		topMiddle.setBackground(new Color(255, 255, 255));
		
		JPanel topMiddle2Left = new JPanel(new BorderLayout());
		topMiddle2Left.setBackground(new Color(255, 255, 255));
		
		JPanel topMiddle2Middle = new JPanel(new BorderLayout());
        topMiddle2Middle.setBackground(new Color(255, 255, 255));
        
        JPanel topMiddle2Right = new JPanel(new BorderLayout());
        topMiddle2Right.setBackground(new Color(255, 255, 255));


		
		// Everything for main below
		main.setBackground(new Color(255, 255, 255));

        JButton teacher = new JButton("Teachers for " + courseNum);
        teacher.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Search.readCourses();
                FrameController.changeFrame(NotImplementedPage.createFrame());
            }
        });
        teacher.setBorderPainted(false);
        teacher.setContentAreaFilled(false);
        teacher.setFocusPainted(false);
        teacher.setOpaque(false);
        teacher.setForeground(Color.BLACK);
        teacher.setFont(teacher.getFont().deriveFont(30.0f));
        teacher.setFont(new Font(ARIAL, Font.BOLD, 20));
        topRight.add(teacher, BorderLayout.CENTER);
		
        
        JButton docs = new JButton("Documents for " + courseNum);
        docs.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Search.readCourses();
                FrameController.changeFrame(NotImplementedPage.createFrame());
            }
        });
        docs.setBorderPainted(false);
        docs.setContentAreaFilled(false);
        docs.setFocusPainted(false);
        docs.setOpaque(false);
        docs.setForeground(Color.BLACK);
        docs.setFont(docs.getFont().deriveFont(30.0f));
        docs.setFont(new Font(ARIAL, Font.BOLD, 20));
        topRight.add(docs, BorderLayout.EAST);
		
        c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		c.anchor = GridBagConstraints.EAST;
		c.weightx = 0.5;
		main.add(topRight, c);

		

        JButton evaluation = new JButton("Evaluate " + courseNum);
        evaluation.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Search.readCourses();
                FrameController.changeFrame(NotImplementedPage.createFrame());
            }
        });
        evaluation.setBorderPainted(false);
        evaluation.setContentAreaFilled(false);
        evaluation.setFocusPainted(false);
        evaluation.setOpaque(false);
        evaluation.setForeground(Color.BLACK);
        evaluation.setFont(teacher.getFont().deriveFont(30.0f));
        evaluation.setFont(new Font(ARIAL, Font.BOLD, 20));
        topLeft.add(evaluation, BorderLayout.WEST);
        
        
        c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 0.5;
        main.add(topLeft, c);
		

		JLabel grade = new JLabel("Average Grade: " + calculateOverallGrade(reviews));
		grade.setForeground(Color.BLACK);
		grade.setFont(grade.getFont().deriveFont(20.0f));
		grade.setHorizontalAlignment(JLabel.CENTER);
        topMiddle.add(grade, BorderLayout.CENTER);  
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(0, 0, 0, 50);
		main.add(topMiddle, c);

		double score1 = averageCriteria(1, reviews);
		JLabel criteria1 = new JLabel("Workload: " + numberFormat.format(score1) + "/4.0");
		criteria1.setForeground(Color.BLACK);
		criteria1.setFont(criteria1.getFont().deriveFont(20.0f));
		criteria1.setHorizontalAlignment(JLabel.CENTER);
        topMiddle2Left.add(criteria1, BorderLayout.WEST);  

		double score2 = averageCriteria(2, reviews);
		JLabel criteria2 = new JLabel("Test Difficulty: " + numberFormat.format(score2) + "/4.0");
		criteria2.setForeground(Color.BLACK);
		criteria2.setFont(grade.getFont().deriveFont(20.0f));
		criteria2.setHorizontalAlignment(JLabel.CENTER);
        topMiddle2Middle.add(criteria2, BorderLayout.CENTER);  
		
		double score3 = averageCriteria(3, reviews);
		JLabel criteria3 = new JLabel("Content Difficulty: " + numberFormat.format(score3) + "/4.0");
		criteria3.setForeground(Color.BLACK);
		criteria3.setFont(criteria3.getFont().deriveFont(20.0f));
		criteria3.setHorizontalAlignment(JLabel.CENTER);
        topMiddle2Right.add(criteria3, BorderLayout.EAST);  
		
        c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(0, 0, 0, 50);
		main.add(topMiddle2Left, c);
		
		c.gridx = 1;
        main.add(topMiddle2Middle, c);
        
        c.gridx = 2;
        main.add(topMiddle2Right, c);

		
		// Overall Score
		ImageIcon stars = getStars(score1, score2, score3);
		JLabel overall = new JLabel("Overall Score", SwingConstants.LEFT);
		JLabel starRating = new JLabel(stars, SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.NORTH;
		c.ipady = 50;
		c.insets = new Insets(0, 50, 0, 0);
		main.add(overall, c);
		main.add(starRating, c);

               // Content (Each student review that submitted written feedback/review)
               JLabel content;
               c.fill = GridBagConstraints.BOTH;
               c.gridwidth = 3;
               c.gridx = 0;
               c.gridy = 4;
               c.weighty = 1;
               c.ipady = 0;

               for (int i = 0 ; i < reviews.size(); i++) {
                     content = new JLabel((reviews.get(i)).getReview());
                     c.gridy++;
                     c.ipady++;
                     content.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // For visually seeing grids
                     main.add(content, c);

               }
            }


	public static ImageIcon getStars(double score1, double score2, double score3)
	{
		int average = (int) (score1 + score2 + score3) / 3;
		if (average > 4 || average < 1)
			average = 0;
		String info = "Stars: " + average;
		LOGGER.info(info);
		String link = "images/" + Integer.toString(average) + "_star.png";
		return new ImageIcon(link);
	}

	// Takes the courseNum and gets all reviews for that course
	public static List<StudentReview> getReviews()
	{
		return new ArrayList<StudentReview>();
	}

	// Gets the average criteria score from the StudentReview list "reviews" based
	// on the string passed in
	public static double averageCriteria(int criteria, List<StudentReview> reviews)
	{
	        if (reviews.isEmpty()) {
                     return 0.0;
                }
		int i;
		double total = 0;
		for (i = 0; i < reviews.size(); i++)
		{
			total += reviews.get(i).getCriteria(criteria);
		}
		return total / reviews.size();
	}

	public static String calculateOverallGrade(List<StudentReview> reviews)
	{
	        if (reviews.isEmpty()) {
                     return "No grade yet";
                }
                int total = 0;
		for (StudentReview review : reviews)
		{
			switch (review.getGrade())
			{
			case "A":
				total += 4;
				break;
			case "B":
				total += 3;
				break;
			case "C":
				total += 2;
				break;
			case "D":
				total += 1;
				break;
			default:
				total += 0;
			}
		}

		int average = (int) Math.round((double) total / reviews.size());
		switch (average)
		{
		case 4:
			return "A";
		case 3:
			return "B";
		case 2:
			return "C";
		case 1:
			return "D";
		case 0:
			return "F";
		default:
			return "No grade yet";
		}
	}
}