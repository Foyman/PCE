package logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.MatchResult;

public class Search
{
    public static ArrayList<Course> courses;

    public static void main(String[] args) throws FileNotFoundException
    {
        String search = "";
        readClasses();
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.print("Enter a course to search for (or \"quit\" to exit): ");
            search = scanner.nextLine();
            if (search.equals("quit"))
                break;
            EditDistance.sortList(search);
            if (courses.get(0).distance == 0)
            {
                System.out.println("Found: " + courses.get(0).name);
            } else
            {
                System.out.println("Closest Course: " + courses.get(0).name + ", distance: " + courses.get(0).distance);
            }
        }
        scanner.close();
    }

    public static void readClasses() throws FileNotFoundException
    {
        if (courses != null)
        {
            return;
        }
        courses = new ArrayList<Course>(5000);
        Scanner scanner = new Scanner(new FileReader("courses.txt"));
        while (scanner.hasNextLine())
        {
            String temp = scanner.nextLine();
            Scanner classScanner = new Scanner(temp);

            classScanner.findInLine("\\w+ \\d+:");
            try
            {
                MatchResult result = classScanner.match();
                String className = result.group(0);
                int len = className.length();
                className = className.substring(0, len - 1);
                courses.add(new Course(className.toLowerCase()));
            } catch (IllegalStateException e)
            {

            }
            classScanner.close();
        }
        scanner.close();
    }

}
