package logic;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

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

        SearchPage.createFrameHelper(courses, columnNames, rowData, footer, header, panels);
        
        return panels;
    }
}
