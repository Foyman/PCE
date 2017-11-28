package logic;

import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.*;
import java.util.regex.*;

public class Home
{

    // To Please SonarQube
    private Home()
    {

    }    
    
    
    public static void createFrame(JFrame frame) throws FileNotFoundException
    {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Constraints used for setting up main
        final GridBagConstraints c = new GridBagConstraints();

        List<JComponentWithLayout> panels = new ArrayList<JComponentWithLayout>(3);

        // Panels
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JPanel main = new JPanel(new GridBagLayout());
        JPanel footer = new JPanel();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;



        // Everything for header below
        header.add(Box.createHorizontalStrut(20));
        JLabel headText = new JLabel("PCE");
        headText.setForeground(Color.WHITE);
        headText.setFont(headText.getFont().deriveFont(64.0f));
        header.setBackground(new Color(7, 88, 64));
        header.add(headText);
        header.add(Box.createHorizontalStrut((screenWidth - (1220))));


        // Course List button
        JButton courseListButton = new JButton("Course List");
        courseListButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    Search.readCourses();
                } catch (FileNotFoundException e1)
                {
                    return;
                }
                FrameController.changeFrame(CourseListPage.createFrame(Search.getCourses()));
            }
        });
        courseListButton.setBorderPainted(false);
        courseListButton.setContentAreaFilled(false); 
        courseListButton.setFocusPainted(false); 
        courseListButton.setOpaque(false);
        courseListButton.setForeground(Color.WHITE);
        courseListButton.setFont(new Font("Arial", Font.BOLD, 30));
        header.add(Box.createHorizontalStrut(20));
        header.add(courseListButton);


        // Evaluate Course button
        JButton evaluateButton = new JButton("Evaluate a Course");
        evaluateButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    Search.readCourses();
                } catch (FileNotFoundException e1)
                {
                    return;
                }
                FrameController.changeFrame(EvaluatePage.createFrame());
            }
        });
        header.add(Box.createHorizontalStrut(20));
        evaluateButton.setBorderPainted(false);
        evaluateButton.setContentAreaFilled(false); 
        evaluateButton.setFocusPainted(false); 
        evaluateButton.setOpaque(false);
        evaluateButton.setForeground(Color.WHITE);
        evaluateButton.setFont(new Font("Arial", Font.BOLD, 30));
        header.add(evaluateButton);

        // FAQ button
        JButton faqButton = new JButton("FAQ");
        faqButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    Search.readCourses();
                } catch (FileNotFoundException e1)
                {
                    return;
                }
                FrameController.changeFrame(FaqPage.createFrame());
            }
        });
        header.add(Box.createHorizontalStrut(20));
        faqButton.setBorderPainted(false);
        faqButton.setContentAreaFilled(false); 
        faqButton.setFocusPainted(false); 
        faqButton.setOpaque(false);
        faqButton.setForeground(Color.WHITE);
        faqButton.setFont(new Font("Arial", Font.BOLD, 30));
        header.add(faqButton);

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

        // Displaying Search Results
        searchButton.addActionListener(new ActionListener()
        {
            int noInput = 0; //track if user did not enter input
            JLabel noInputText = new JLabel();

            public void actionPerformed(ActionEvent e)
            {
                StringBuilder search = new StringBuilder();
                Type t;
                try
                {
                    Search.readCourses();
                } catch (FileNotFoundException e1)
                {
                    return;
                }

                // Search Department and number
                //String querySub;
                if (!courseNumberInput.getText().equals(""))
                {
                    t = Type.NAME;
                    search.append((String) deptList.getSelectedItem());
                    search.append(" ");
                    search.append(courseNumberInput.getText());

                    //                    querySub = String.format("SELECT CourseId FROM Course WHERE Dept = \"%s\" AND CourseNum = %d", 
                    //                              (String) deptList.getSelectedItem(), courseNumberInput.getText());
                }
                // Search Course description
                else if (!courseNameInput.getText().equals(""))
                {
                    t = Type.DESCRIPTION;
                    search.append(courseNameInput.getText());
                    //                    querySub = String.format("SELECT CourseId FROM Course WHERE CourseName LIKE \"%%%s%%\"", courseNameInput.getText());
                } else {
                    noInputText.setText("Please add a search input before searching");
                    c.gridx = 0;
                    c.gridy = 4;
                    c.gridwidth = 4;
                    c.ipady = 4; // vertical padding
                    main.add(noInputText, c);
                    t = null;
                }

                if (t != null) {
                    List<Course> courses = Search.getCourses();
                    EditDistance.sortList(search.toString(), t);

                    if (noInput == 1){ //remove jlabel that asks for input
                        noInputText.setText("");
                        noInput = 0;
                        main.revalidate();
                        main.repaint();
                    }



//                    String query = String.format("SELECT * FROM Reviews r WHERE r.CourseId = (%s)", querySub);
//                    try
//                    {
//                        ResultSet r = DBConnect.processGeneralQuery(query);
//                    } catch (Exception e1)
//                    {
//                        // TODO Auto-generated catch block
//                        e1.printStackTrace();
//                    }

                    // Distance of the first two courses in the search list
                    int t1 = courses.get(0).getDistance();
                    int t2 = courses.get(1).getDistance();
                    if (t1 == 0 && t2 == 0)
                    {
                        // send to Search Page frame
                        FrameController.changeFrame(SearchPage.createFrame(courses));
                    } else if (t1 == 0)
                    {
                        // send to Course Page Frame
                    } else
                    {
                        // send to Search Page Frame
                        FrameController.changeFrame(SearchPage.createFrame(courses));
                    }
                } else {
                    if (noInput == 0){
                        main.revalidate(); //display new jlabel
                        main.repaint();
                        noInput = 1;
                    }
                }
            }
        });

        // Everything for footer below
        JLabel footText = new JLabel("� 2017 Polyratings Course Edition");
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
        frame.pack();
        frame.setVisible(true);

        FrameController.changeFrame(panels);
    }

    /**
     * Gets all departments for drop down box
     * 
     * @return List of Departments for all courses
     * @throws FileNotFoundException
     */
    public static List<String> getDepartments() throws FileNotFoundException
    {
        File courses = new File("courses.txt");
        Scanner scan = new Scanner(courses);
        String department;
        List<String> list = new ArrayList<String>();
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

        scan.close();
        return list;
    }
}
