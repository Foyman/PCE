package logic;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class SearchPage
{
    // To Please SonarQube
    private SearchPage()
    {

    }

    public static List<JComponentWithLayout> createFrame(final List<Course> courses)
    {

        List<JComponentWithLayout> panels = new ArrayList<JComponentWithLayout>(3);

        // Panels
        JPanel header;
        JPanel footer = new JPanel();

        
        
        // Does everything to create header
        
        header = HeaderFactory.createHeader("Search Results");

        // Getting column names and rows for search Data
        Object[] columnNames = { "Course", "Description" };
        Object[][] rowData = new Object[100][2];
        int n = 0;
        for (Course course : courses)
        {
            rowData[n][0] = course.getName().toUpperCase();
            rowData[n][1] = course.getDescription().toUpperCase();
            if (++n == 100)
                break;
        }

        createFrameHelper(courses, columnNames, rowData, footer, header, panels);

        return panels;
    }
    
    public static void createFrameHelper(final List<Course> courses, Object[] columnNames, Object[][] rowData,
   		 final JPanel footer, final JPanel header, List<JComponentWithLayout> panels)
    {
   	// Adding JTable for Search Data
       final JTable table = new JTable();
       table.setModel(new DefaultTableModel(rowData, columnNames)
       {

           private static final long serialVersionUID = 1L;

           @Override
           public boolean isCellEditable(int row, int col)
           {
               return false;
           }
       });

       // Changing the Table Header
       JTableHeader tableHeader = table.getTableHeader();
       tableHeader.setFont(new Font("Verdana", Font.BOLD, 30));
       tableHeader.setSize(tableHeader.getWidth(), 50);

       // Adds listener to get when a course is double clicked
       table.addMouseListener(new MouseListener()
       {
           //@Override
           public void mouseClicked(MouseEvent e)
           {
               if (e.getClickCount() == 2)
               {
                   // VERIFIES THAT IT IS CLICKING ON THE ACTUAL COURSE
                   String selectedCourse = courses.get(table.getSelectedRow()).getName();
                   String dept = selectedCourse.split(" ")[0];
                   String courseNum = selectedCourse.split(" ")[1];
                   Home.searchForReview(dept, courseNum, selectedCourse);

               }
           }

           //@Override
           public void mouseEntered(MouseEvent arg0)
           {
               return;
           }

           //@Override
           public void mouseExited(MouseEvent arg0)
           {
               return;
           }

           //@Override
           public void mousePressed(MouseEvent arg0)
           {
               return;
           }

           //@Override
           public void mouseReleased(MouseEvent arg0)
           {
               return;
           }

       });
       table.setRowHeight(40);
       table.setFont(new Font("Verdana", Font.PLAIN, 20));
       table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

       // Makes table scroll-able
       JScrollPane scroll = new JScrollPane(table);

       // Everything for footer below
       JLabel footText = new JLabel("© 2017 Polyratings Course Edition");
       footText.setForeground(Color.WHITE);
       footer.setBackground(new Color(7, 88, 64));
       footer.add(footText);

       // All panels into frame
       panels.add(new JComponentWithLayout(header, BorderLayout.NORTH));
       panels.add(new JComponentWithLayout(scroll, BorderLayout.CENTER));
       panels.add(new JComponentWithLayout(footer, BorderLayout.SOUTH));
    }
}
