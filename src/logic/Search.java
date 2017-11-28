package logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.List;

public class Search
{
    private static List<Course> courses;

    // For SonarQube
    private Search()
    {

    }

    /**
     * Getter method for the courses
     * 
     * @return courses - a list of all the courses
     */
    public static List<Course> getCourses()
    {
        return courses;
    }

    /**
     * Reads in Courses into Course Objects from the courses.txt file Sets the
     * static courses list in this class
     * 
     * @throws FileNotFoundException
     */
    public static void readCourses() throws FileNotFoundException
    {
        if (courses != null)
        {
            return;
        }
        courses = new ArrayList<Course>(5000);
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(new FileReader("courses.txt"));
            while (scanner.hasNextLine())
            {
                String temp = scanner.nextLine();
                readCoursesHelper(temp, courses);
            }
        } catch (Exception e)
        {
            return;
        } finally
        {
            if (scanner != null)
                scanner.close();
        }

    }

    /**
     * Helper method for the readCourses in order to please SonarQube...
     * 
     * @param line - the last line read (holds one course's information)
     * @param courses - reference to the courses list
     */
    private static void readCoursesHelper(String line, List<Course> courses)
    {
        Scanner classScanner = null;
        try
        {
            classScanner = new Scanner(line);

            classScanner.findInLine("(\\w+ \\d+): (.*)");
            MatchResult result = classScanner.match();
            String className = result.group(1);
            String classDescription = result.group(2);
            courses.add(new Course(className.toLowerCase(), classDescription.toLowerCase()));
        } catch (Exception e)
        {
            return;
        } finally
        {
            if (classScanner != null)
                classScanner.close();
        }
    }

    /**
     * Resets the list of courses.
     * Used in unit tests since the methods of this class are static.
     */
    public static void resetCourses()
    {
        courses = null;
    }

}
