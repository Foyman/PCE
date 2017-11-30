package logic;

import java.awt.*;
import java.util.List;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;

import javax.swing.*;

import java.util.*;
import java.util.regex.*;

public class Home
{
    private static final Logger HOMELOGGER = Logger.getLogger(Home.class.getName());

    // To Please SonarQube
    private Home()
    {

    }

    public static List<JComponentWithLayout> createFrame(JFrame frame)
    {
        // Constraints used for setting up main
        final GridBagConstraints c = new GridBagConstraints();

        List<JComponentWithLayout> panels = new ArrayList<JComponentWithLayout>(3);

        // Panels
        JPanel header = HeaderFactory.createHeader("PCE");
        final JPanel main = new JPanel(new GridBagLayout());
        JPanel footer = new JPanel();

        
        // Everything for main below
        main.setBackground(new Color(255, 255, 255));

        // Department text
        JLabel deptText = new JLabel("Department: ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        main.add(deptText, c);

        // Department drop down box
        ArrayList<String> deptArrayList = (ArrayList<String>) getDepartments();
        String[] deptArray = deptArrayList.toArray(new String[deptArrayList.size()]);
        final JComboBox<String> deptList = new JComboBox<String>(deptArray);
        c.gridx = 1;
        c.gridy = 0;
        main.add(deptList, c);

        // Class # text
        JLabel courseText = new JLabel("Course #: ");
        c.gridx = 2;
        c.gridy = 0;
        main.add(courseText, c);

        // Class search bar
        final JTextField courseNumberInput = new JTextField("", 5);
        c.gridx = 3;
        c.gridy = 0;
        main.add(courseNumberInput, c);

        // Class Name text
        JLabel searchText = new JLabel("Course Name: ");
        c.gridx = 0;
        c.gridy = 1;
        main.add(searchText, c);

        // Class search bar
        final JTextField courseNameInput = new JTextField("");
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 3;
        main.add(courseNameInput, c);

        // Search button
        JButton searchButton = new JButton("Search");
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        c.ipady = 6; // vertical padding
        c.insets = new Insets(14, 0, 0, 0); // top margin
        main.add(searchButton, c);

        addAction(searchButton, c, courseNumberInput, deptList, main, courseNameInput);

        // Everything for footer below
        JLabel footText = new JLabel("© 2017 Polyratings Course Edition");
        footText.setForeground(Color.WHITE);
        footer.setBackground(new Color(7, 88, 64));
        footer.add(footText);

        // All panels into frame
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        panels.add(new JComponentWithLayout(header, BorderLayout.NORTH));
        panels.add(new JComponentWithLayout(main, BorderLayout.CENTER));
        panels.add(new JComponentWithLayout(footer, BorderLayout.SOUTH));

        for (JComponentWithLayout p : panels)
        {
            p.addToFrame(frame);
        }
        frame.setVisible(true);

        return panels;
    }

    /**
     * Creates search Button Action For SonarQube Complexity
     */
    public static void addAction(JButton searchButton, final GridBagConstraints c, final JTextField courseNumberInput,
            final JComboBox<String> deptList, final JPanel main, final JTextField courseNameInput)
    {

        // Displaying Search Results
        searchButton.addActionListener(new ActionListener()
        {
            int noInput = 0; // track if user did not enter input
            JLabel noInputText = new JLabel();

            public void actionPerformed(ActionEvent e)
            {
                StringBuilder search = new StringBuilder();
                boolean t = true;
                Search.readCourses();

                // Search Department and number
                String cSelected = "";
                if (!courseNumberInput.getText().equals(""))
                {
                    search.append((String) deptList.getSelectedItem());
                    search.append(" ");
                    search.append(courseNumberInput.getText());
                    cSelected = (String) deptList.getSelectedItem() + courseNumberInput.getText();
                    searchForReview((String) deptList.getSelectedItem(), courseNumberInput.getText(), cSelected);
                }
                // Search Course description
                else if (!courseNameInput.getText().equals(""))
                {
                    String query = String.format("SELECT Dept, CourseNum, CourseName FROM Course WHERE CourseName LIKE \"%%%s%%\"", courseNameInput.getText());
                    try
                    {
                        ResultSet r = DBConnect.processGeneralQuery(query);
                        List<Course> courseList = makeCourses(r);
                        if(!courseList.isEmpty())
                            FrameController.changeFrame(SearchPage.createFrame(courseList)); 
                        else
                            FrameController.changeFrame(CourseListPage.createFrame(Search.getCourses()));
                    } catch (SQLException s)
                    {
                        HOMELOGGER.info("SQL cannot process query");
                    }
                } else
                {
                    noInputText.setText("Please add a search input before searching");
                    c.gridx = 0;
                    c.gridy = 4;
                    c.gridwidth = 4;
                    c.ipady = 4; // vertical padding
                    main.add(noInputText, c);
                    t = false;
                }

                if (t)
                {
                    List<Course> courses = Search.getCourses();
                    EditDistance.sortList(search.toString());

                    if (noInput == 1)
                    { // remove jlabel that asks for input
                        noInputText.setText("");
                        noInput = 0;
                        main.revalidate();
                        main.repaint();
                    }
                    FrameController.changeFrame(SearchPage.createFrame(courses));
                } else
                {
                    if (noInput == 0)
                    {
                        main.revalidate(); // display new jlabel
                        main.repaint();
                        noInput = 1;
                    }
                }
            }
        });
    }

    public static List<Course> makeCourses (ResultSet r) throws SQLException {
        List<Course> cList = new ArrayList<Course>();
        Course c;
        while (r.next())
        {
            String cDept = r.getString("Dept");
            int cNum = r.getInt("CourseNum");
            String description = r.getString("CourseName");
            String cName = cDept + " " + cNum;
            c = new Course(cName, description);
            cList.add(c);
        }
        return cList;
    }

    public static void searchForReview(String department, String courseNumber, String courseName)
    {
        String querySub = String.format("SELECT CourseId FROM Course WHERE Dept = \"%s\" AND CourseNum = %s",
                department, courseNumber);
        String query = String.format(
                "SELECT Rating1, Rating2, Rating3, StudentGrade, Review FROM Reviews r WHERE r.CourseId = (%s);",
                querySub);
        try
        {
            ResultSet r = DBConnect.processGeneralQuery(query);
            List<StudentReview> reviews = makeReviews(r, courseName);
            if(!reviews.isEmpty())
                FrameController.changeFrame(CourseReviewPage.createFrame(department, courseNumber, reviews));
        } catch (SQLException s)
        {
            HOMELOGGER.info("SQL cannot process query");
        }
    }

    public static List<StudentReview> makeReviews(ResultSet r, String c) throws SQLException
    {
        List<StudentReview> reviews = new ArrayList<StudentReview>();
        StudentReview rev;
        while (r.next())
        {
            double rating1 = r.getDouble("Rating1");
            double rating2 = r.getDouble("Rating2");
            double rating3 = r.getDouble("Rating3");
            String sGrade = r.getString("StudentGrade");
            String sReview = r.getString("Review");
            rev = new StudentReview(rating1, rating2, rating3, sGrade, sReview, c);
            reviews.add(rev);
        }
        return reviews;
    }

    /**
     * Gets all departments for drop down box
     * 
     * @return List of Departments for all courses
     */
    public static List<String> getDepartments()
    {
        File courses = new File("courses.txt");
        List<String> list = new ArrayList<String>();
        Scanner scan = null;

        try
        {
            scan = new Scanner(courses);
            String department;
            list.add("");

            while (scan.hasNextLine())
            {
                department = scan.nextLine();
                Matcher match = Pattern.compile("^([A-Z]+)$").matcher(department);
                if (match.find())
                {
                    list.add(match.group(1));
                }
            }

        } catch (FileNotFoundException e)
        {
      	  		HOMELOGGER.info("FileNotFoundException in getDepartments");
        }
        finally
        {
      	  		if(scan != null)
      	  			scan.close();
        }

        return list;
    }
}
