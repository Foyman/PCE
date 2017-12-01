package logic;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.text.*;
import java.util.logging.Logger;

public class CourseReviewPage
{
    private static final Logger LOGGER = Logger.getLogger(CourseReviewPage.class.getName());

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
        //Used for formating printed Criteria values
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        
        // Constraints used for setting up main
        GridBagConstraints c = new GridBagConstraints();

        // Everything for main below
        main.setBackground(new Color(255, 255, 255));

        // Evaluate
        JLabel evaluation = new JLabel("Evaluate " + courseNum, SwingConstants.SOUTH_EAST);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.SOUTH;
        c.weightx = 0.5;
        evaluation.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // For visually seeing grids
        main.add(evaluation, c);

        // Class Title (Will need to get from class list)
        JLabel className = new JLabel(courseNum, SwingConstants.CENTER);
        className.setFont(className.getFont().deriveFont(64.0f));
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.ipady = 50;
        c.gridheight = 2;
        className.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // For visually seeing grids
        main.add(className, c);

        // Class Teachers (Get from SQL or Polylearn)
        JLabel teachers = new JLabel(courseNum + " Teachers");
        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 1;
        c.ipady = 0;
        c.insets = new Insets(0, 10, 0, 0);
        teachers.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // For visually seeing grids
        main.add(teachers, c);

        // Class Documents
        JLabel docs = new JLabel(courseNum + " Documents");
        c.gridx = 2;
        c.gridy = 1;
        docs.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // For visually seeing grids
        main.add(docs, c);

        // Average Grade (Calculated based on grades from student reviews for class on
        // SQL database)
        JLabel grade = new JLabel("Average Grade: " + calculateOverallGrade(reviews));
        c.gridx = 2;
        c.gridy = 2;
        c.insets = new Insets(0, 0, 0, 50);
        grade.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // For visually seeing grids
        main.add(grade, c);

        // Criteria 1 (Calculated based on criteria1 from student reviews for class on
        // SQL database)
        double score1 = averageCriteria(1, reviews);
        JLabel criteria1 = new JLabel("Workload: " + numberFormat.format(score1) + "/4.0");
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(0, 0, 0, 0);
        criteria1.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // For visually seeing grids
        main.add(criteria1, c);

        // Criteria 2 (Calculated based on criteria2 from student reviews for class on
        // SQL database)
        double score2 = averageCriteria(2, reviews);
        JLabel criteria2 = new JLabel("Test Difficulty: " + numberFormat.format(score2) + "/4.0");
        c.gridx = 1;
        c.gridy = 3;
        criteria2.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // For visually seeing grids
        main.add(criteria2, c);

        // Criteria 3 (Calculated based on criteria3 from student reviews for class on
        // SQL database)
        double score3 = averageCriteria(3, reviews);
        JLabel criteria3 = new JLabel("Content Difficulty: " + numberFormat.format(score3) + "/4.0");
        c.gridx = 2;
        c.gridy = 3;
        criteria3.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // For visually seeing grids
        main.add(criteria3, c);

        // Overall Score
        ImageIcon stars = getStars(score1, score2, score3);
        JLabel overall = new JLabel("Overall Score", SwingConstants.LEFT);
        JLabel starRating = new JLabel(stars, SwingConstants.RIGHT);
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.NORTH;
        c.ipady = 50;
        c.insets = new Insets(0, 50, 0, 0);
        overall.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // For visually seeing grids
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
        if (reviews.size() == 0) {
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
        if (reviews.size() == 0) {
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
                return "";
        }
    }
}
