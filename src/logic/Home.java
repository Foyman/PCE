package logic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import java.util.*;
import java.util.regex.*;

public class Home
{
    
    public static void main(String[] args) throws Exception
    {
        createFrame();
    }

    public static void createFrame() throws FileNotFoundException
    {
        // Frame
        JFrame frame = new JFrame("Home Page");
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

        // Department text
        JLabel deptText = new JLabel("Department: ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        main.add(deptText, c);

        // Department drop down box
        ArrayList<String> deptArrayList = getDepartments();
        String[] deptArray = deptArrayList.toArray(new String[deptArrayList.size()]);
        JComboBox<String> deptList = new JComboBox<String>(deptArray);
        c.gridx = 1;
        c.gridy = 0;
        main.add(deptList, c);

        // Class # text
        JLabel courseText = new JLabel("Course #: ");
        c.gridx = 2;
        c.gridy = 0;
        main.add(courseText, c);

        // Class search bar
        JTextField courseNumberInput = new JTextField("", 5);
        c.gridx = 3;
        c.gridy = 0;
        main.add(courseNumberInput, c);

        // Class Name text
        JLabel searchText = new JLabel("Course Name: ");
        c.gridx = 0;
        c.gridy = 1;
        main.add(searchText, c);

        // Class search bar
        JTextField courseNameInput = new JTextField("");
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
        c.insets = new Insets(14,0,0,0); // top margin
        main.add(searchButton, c);
        
        
        // Search Results
        JLabel result = new JLabel("");
        c.gridx = 0;
        c.gridy = 3;
        main.add(result, c);
        
        // Displaying Search Results
        searchButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                StringBuilder search = new StringBuilder();
                Type t;
                try
                {
                    Search.readCourses();
                } catch (FileNotFoundException e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
                // Search Department and number
                if(!courseNumberInput.getText().equals(""))
                {
                    t = Type.name;
                    search.append((String) deptList.getSelectedItem());
                    search.append(" ");
                    search.append(courseNumberInput.getText());
                }
                // Search Course description
                else
                {
                    t = Type.description;
                    search.append(courseNameInput.getText());
                }
                
                EditDistance.sortList(search.toString(), t);
                if (Search.courses.get(0).distance == 0)
                {
                    result.setText("Found: " + Search.courses.get(0).name);
                } else
                {
                    result.setText("Closest Course: " + Search.courses.get(0).name + ", distance: " + Search.courses.get(0).distance);
                }
            }
          });
        
        


        // Everything for footer below
        JLabel footText = new JLabel("© 2017 Polyratings Course Edition");
        footText.setForeground(Color.WHITE);
        footer.setBackground(new Color(7, 88, 64));
        footer.add(footText);

        // All panels into frame
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setPreferredSize(new Dimension(1440, 830));
        frame.getContentPane().add(header, BorderLayout.NORTH);
        frame.getContentPane().add(main, BorderLayout.CENTER);
        frame.getContentPane().add(footer, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    // Gets all departments for drop down box
    public static ArrayList<String> getDepartments() throws FileNotFoundException
    {
        File courses = new File("courses.txt");
        Scanner scan = new Scanner(courses);
        String department;
        ArrayList<String> list = new ArrayList<String>();
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
