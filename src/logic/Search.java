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

    public static List<Course> getCourses()
    {
        return courses;
    }
    

    /**
     * Reads in Courses into Course Objects from the courses.txt file
     * @throws FileNotFoundException
     */
    public static void readCourses() throws FileNotFoundException
    {
        if (courses != null)
        {
            return;
        }
        courses = new ArrayList<Course>(5000);
        try 
        {
            Scanner scanner = new Scanner(new FileReader("courses.txt"));
            
            while (scanner.hasNextLine())
            {
                String temp = scanner.nextLine();
                try {
                    Scanner classScanner = new Scanner(temp);
    
                    classScanner.findInLine("(\\w+ \\d+): (.*)");
                    try
                    {
                        MatchResult result = classScanner.match();
                        String className = result.group(1);
                        String classDescription = result.group(2);
                        courses.add(new Course(className.toLowerCase(), classDescription.toLowerCase()));
                        System.out.println("" + className + " " + classDescription);
                    } catch (IllegalStateException e)
                    {
    
                    }
                    classScanner.close();
                }
                catch(Exception e)
                {
                    scanner.close();
                    return;
                }
            }
            scanner.close();
        }
        catch (Exception e)
        {
            return;
        }
    }

}
