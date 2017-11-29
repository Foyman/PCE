package logic;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class CourseListPage
{

    // To Please SonarQube
    private CourseListPage()
    {

    }

    public static List<JComponentWithLayout> createFrame(final List<Course> courses)
    {

        List<JComponentWithLayout> panels = new ArrayList<JComponentWithLayout>(3);

        // Panels
        JPanel header = HeaderFactory.createHeader("Course List");
        JPanel footer = new JPanel();

        // Getting column names and rows for search Data
        Object[] columnNames = { "Course", "Description" };
        Object[][] rowData = new Object[courses.size()][2];
        for (int i = 0; i < courses.size(); i++)
        {
            Course course = courses.get(i);
            rowData[i][0] = course.getName().toUpperCase();
            rowData[i][1] = course.getDescription().toUpperCase();
        }

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

            // @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 2)
                {
                    // VERIFIES THAT IT IS CLICKING ON THE ACTUAL COURSE
                    System.out.println("Double Click on " + courses.get(table.getSelectedRow()).getName());
                }
            }

            // @Override
            public void mouseEntered(MouseEvent arg0)
            {
                return;
            }

            // @Override
            public void mouseExited(MouseEvent arg0)
            {
                return;
            }

            // @Override
            public void mousePressed(MouseEvent arg0)
            {
                return;
            }

            // @Override
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

        return panels;
    }
}
