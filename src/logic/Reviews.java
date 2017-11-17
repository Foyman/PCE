package logic;

import java.awt.*;
import java.io.FileNotFoundException;
import javax.swing.*;

public class Reviews
{
    private static String classInfo;
    private static String overallgrade;
    private static double score1;
    private static double score2;
    private static double score3;
    private static ImageIcon stars;
    
    Reviews(String classInfo, String overallgrade, double score1, double score2, double score3) throws Exception
    {
        Reviews.classInfo = classInfo;
        Reviews.overallgrade = overallgrade;
        Reviews.score1 = score1;
        Reviews.score2 = score2;
        Reviews.score3 = score3;
    }
    
    public static void main(String[] args) throws Exception
    {
        Reviews test = new Reviews("CSC 307", "A-", 1.1, 3.2, 3.4);
        test.createFrame();
    }
    
    public void createFrame() throws FileNotFoundException
    {
        // Frame
        JFrame frame = new JFrame("Class Review Page");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Constraints used for setting up main
        GridBagConstraints c = new GridBagConstraints();

        // Panels
        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel main = new JPanel(new GridBagLayout());
        JPanel footer = new JPanel();

        // Everything for header below
        JLabel headText = new JLabel("Polyratings: Course Edition");
        headText.setForeground(Color.WHITE);
        headText.setFont(headText.getFont().deriveFont(64.0f));
        header.setBackground(new Color(7, 88, 64));
        header.add(headText);

        // Everything for main below
        main.setBackground(new Color(255, 255, 255));
        
        //Evaluate
        JLabel evaluation = new JLabel("Evaluate " + classInfo, SwingConstants.SOUTH_EAST);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.SOUTH;
        c.weightx = 0.5;
        evaluation.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //For visually seeing grids
        main.add(evaluation, c);
        
        //Class Title
        JLabel className = new JLabel(classInfo, SwingConstants.CENTER);
        className.setFont(headText.getFont().deriveFont(64.0f));
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.ipady = 50;
        c.gridheight = 2;
        className.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //For visually seeing grids
        main.add(className, c);
        
        //Class Teachers
        JLabel teachers = new JLabel(classInfo + " Teachers");
        c.gridx = 2;
        c.gridy = 0;
        //c.anchor = GridBagConstraints.SOUTH;
        c.gridheight = 1;
        c.ipady = 0;
        //c.ipadx = 50;
        c.insets = new Insets(0, 10, 0, 0);
        teachers.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //For visually seeing grids
        main.add(teachers, c);
        
        //Class Documents
        JLabel docs = new JLabel(classInfo + " Documents");
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
        //c.ipadx = 0;
        c.insets = new Insets(0, 50, 0, 0);
        overall.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //For visually seeing grids
        main.add(overall, c);
        main.add(starRating, c);
        
        //Average Grade
        JLabel grade = new JLabel("Average Grade: " + overallgrade);
        c.gridx = 2;
        c.gridy = 2;
        c.insets = new Insets(0, 0, 0, 50);
        grade.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //For visually seeing grids
        main.add(grade, c);
        
        //Criteria 1
        JLabel criteria1 = new JLabel("Criteria 1: " + Double.toString(score1) + "/4.0");
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(0, 0, 0, 0);
        criteria1.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //For visually seeing grids
        main.add(criteria1, c);
        
        //Criteria 2
        JLabel criteria2 = new JLabel("Criteria 2: " + Double.toString(score2) + "/4.0");
        c.gridx = 1;
        c.gridy = 3;
        criteria2.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //For visually seeing grids
        main.add(criteria2, c);
        
        //Criteria 3
        JLabel criteria3 = new JLabel("Criteria 3: " + Double.toString(score3) + "/4.0");
        c.gridx = 2;
        c.gridy = 3;
        criteria3.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //For visually seeing grids
        main.add(criteria3, c);
        
        //Content
        JLabel content = new JLabel("Content");
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 4;
        c.weighty = 1;
        c.ipady = 0;
        content.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //For visually seeing grids
        main.add(content, c);

        // Everything for footer below
        JLabel footText = new JLabel("© 2017 Polyratings Course Edition");
        footText.setForeground(Color.WHITE);
        footer.setBackground(new Color(7, 88, 64));
        footer.add(footText);

        // All panels into frame
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().add(header, BorderLayout.NORTH);
        frame.getContentPane().add(main, BorderLayout.CENTER);
        frame.getContentPane().add(footer, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }
    
    public ImageIcon getStars()
    {
        int average = (int) (score1 + score2 + score3) / 3;
        if(average > 4 || average < 1)
            average = 0;
        System.out.print(average);
        String link = "images/" + Integer.toString(average) + "_star.png";
        return new ImageIcon(link);
    }
}
