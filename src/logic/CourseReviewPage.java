package logic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class CourseReviewPage
{
    private static String courseNum;
    private static String overallgrade;
    private static double score1;
    private static double score2;
    private static double score3;
    private static ImageIcon stars;
    private static List<StudentReview> reviews;
    
 // To Please SonarQube
    private CourseReviewPage()
    {

    }
    
    //Constructor used
    public CourseReviewPage(List<StudentReview> r, String courseNum)
    {
        CourseReviewPage.courseNum = courseNum;
        reviews = r;
    }

    public static List<JComponentWithLayout> createFrame()
    {
        List<JComponentWithLayout> panels = new ArrayList<JComponentWithLayout>(3);

        // Panels
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel main = new JPanel(new GridBagLayout());
        JPanel footer = new JPanel();

        // Does everything to create header
        createHeader(header);

        // Makes table scroll-able
        //JScrollPane scroll = new JScrollPane();
        
        createMain(main);

        // Everything for footer below
        JLabel footText = new JLabel("© 2017 Polyratings Course Edition");
        footText.setForeground(Color.WHITE);
        footer.setBackground(new Color(7, 88, 64));
        footer.add(footText);

        // All panels into frame
        panels.add(new JComponentWithLayout(header, BorderLayout.NORTH));
        //panels.add(new JComponentWithLayout(scroll, BorderLayout.CENTER));
        panels.add(new JComponentWithLayout(main, BorderLayout.CENTER));
        panels.add(new JComponentWithLayout(footer, BorderLayout.SOUTH));

        return panels;
    }

    /**
     * Helper method to replace complexity of this method
     * 
     * @param header - JPanel of the header
     */
    public static void createHeader(JPanel header)
    {
        // Back Button
        final JButton button = new JButton();
        button.setText("< Back");
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (e.getSource() == button)
                {
                    FrameController.backFrame();
                }
            }
        });
        header.add(button, 0);

        // Adds whitespace to center title (Sorry)
        JLabel whitespace = new JLabel("                              ");
        header.add(whitespace, 1);

        // Everything for header below
        JLabel headText = new JLabel("FAQ");
        headText.setForeground(Color.WHITE);
        headText.setFont(headText.getFont().deriveFont(64.0f));
        header.setBackground(new Color(7, 88, 64));
        header.add(headText, 2);
    }
    
    public static void createMain(JPanel main)
    {
        // Constraints used for setting up main
        GridBagConstraints c = new GridBagConstraints();

        // Everything for main below
        main.setBackground(new Color(255, 255, 255));
        
        //Evaluate
        JLabel evaluation = new JLabel("Evaluate " + courseNum, SwingConstants.SOUTH_EAST);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.SOUTH;
        c.weightx = 0.5;
        evaluation.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //For visually seeing grids
        main.add(evaluation, c);
        
        //Class Title (Will need to get from class list)
        JLabel className = new JLabel(courseNum, SwingConstants.CENTER);
        className.setFont(className.getFont().deriveFont(64.0f));
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.ipady = 50;
        c.gridheight = 2;
        className.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //For visually seeing grids
        main.add(className, c);
        
        //Class Teachers (Get from SQL or Polylearn)
        JLabel teachers = new JLabel(courseNum + " Teachers");
        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 1;
        c.ipady = 0;
        c.insets = new Insets(0, 10, 0, 0);
        teachers.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //For visually seeing grids
        main.add(teachers, c);
        
        //Class Documents
        JLabel docs = new JLabel(courseNum + " Documents");
        c.gridx = 2;
        c.gridy = 1;
        docs.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //For visually seeing grids
        main.add(docs, c);
        
        //Overall Score
        stars = getStars();
        JLabel overall = new JLabel("Overall Score", SwingConstants.LEFT);
        JLabel starRating = new JLabel(stars, SwingConstants.RIGHT);
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.NORTH;
        c.ipady = 50;
        c.insets = new Insets(0, 50, 0, 0);
        overall.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //For visually seeing grids
        main.add(overall, c);
        main.add(starRating, c);
        
        //Average Grade (Calculated based on grades from student reviews for class on SQL database)
        JLabel grade = new JLabel("Average Grade: " + overallgrade);
        c.gridx = 2;
        c.gridy = 2;
        c.insets = new Insets(0, 0, 0, 50);
        grade.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //For visually seeing grids
        main.add(grade, c);
        
        //Criteria 1 (Calculated based on criteria1 from student reviews for class on SQL database)
        score1 = averageCriteria(1);
        JLabel criteria1 = new JLabel("Criteria 1: " + Double.toString(score1) + "/4.0");
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(0, 0, 0, 0);
        criteria1.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //For visually seeing grids
        main.add(criteria1, c);
        
        //Criteria 2 (Calculated based on criteria2 from student reviews for class on SQL database)
        score2 = averageCriteria(2);
        JLabel criteria2 = new JLabel("Criteria 2: " + Double.toString(score2) + "/4.0");
        c.gridx = 1;
        c.gridy = 3;
        criteria2.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //For visually seeing grids
        main.add(criteria2, c);
        
        //Criteria 3 (Calculated based on criteria3 from student reviews for class on SQL database)
        score3 = averageCriteria(3);
        JLabel criteria3 = new JLabel("Criteria 3: " + Double.toString(score3) + "/4.0");
        c.gridx = 2;
        c.gridy = 3;
        criteria3.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //For visually seeing grids
        main.add(criteria3, c);
        
        //Content (Each student review that submitted written feedback/review)
        JLabel content = new JLabel("Content");
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 4;
        c.weighty = 1;
        c.ipady = 0;
        content.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //For visually seeing grids
        main.add(content, c);
    }
    
    public static ImageIcon getStars()
    {
        int average = (int) (score1 + score2 + score3) / 3;
        if(average > 4 || average < 1)
            average = 0;
        System.out.print(average);
        String link = "images/" + Integer.toString(average) + "_star.png";
        return new ImageIcon(link);
    }
    
    //Takes the courseNum and gets all reviews for that course
    public static List<StudentReview> getReviews()
    {
        List<StudentReview> list = new ArrayList<StudentReview>();
        
        return list;
    }
    
    //Gets the average criteria score from the StudentReview list "reviews" based on the string passed in
    public static double averageCriteria(int criteria)
    {
        int i;
        double total = 0;
        for(i = 0; i < reviews.size(); i++)
        {
            total += reviews.get(i).getCriteria(criteria);
        }
        return total/reviews.size();
    }
}
