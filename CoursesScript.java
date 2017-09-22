import java.io.*;
import java.net.*;
import java.util.regex.*;

public class CoursesScript
{
    public static void main(String[] args) throws Exception
    {
        URL site = new URL("http://catalog.calpoly.edu/coursesaz/");
        BufferedReader in = new BufferedReader(
            new InputStreamReader(site.openStream()));
        PrintStream out = new PrintStream(new FileOutputStream("courses.txt"));
        System.setOut(out);
        getDepartments(in);

        in.close();
    }

    public static void getDepartments(BufferedReader in) throws Exception
    {
        String inputLine;
        while ((inputLine = in.readLine()) != null)
        {
            if (inputLine.compareTo("<table>") == 0)
            {
                while ((inputLine = in.readLine()).compareTo("</table>") != 0)
                {
                    String department = inputLine.substring(
                        inputLine.indexOf("(") + 1, inputLine.indexOf(")"));
                    System.out.println("~~~ " + department + " ~~~");
                    getCourses(department, getDepartmentURL(inputLine));
                    System.out.println("\n");
                }
            }
        }
    }

    public static String getDepartmentURL(String inputLine)
    {
        String link = inputLine.substring(inputLine.indexOf("href=\"") + 6, inputLine.indexOf("\" "));
        link = "http://catalog.calpoly.edu" + link;
        return link;
    }

    public static void getCourses(String department, String link) throws Exception
    {
        String courseNum, inputLine;
        URL site = new URL(link);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(site.openStream()));

        while ((inputLine = in.readLine()) != null)
        {
            if (inputLine.contains("courseblocktitle"))
            {
                courseNum = inputLine.substring(inputLine.indexOf(department) + department.length() + 6, inputLine.indexOf("."));
                Pattern p = Pattern.compile("([A-Z]+)&#160;(\\d+)\\. (.*)\\.");
                Matcher m = p.matcher(inputLine);
                if (m.find())
                {
                    String courseNumber = m.group(2);
                    String courseName = m.group(3);
                    System.out.println(department + " " + courseNumber + ": " + courseName);
                }
                else
                {
                    System.err.println("Parsing failed: " + inputLine);
                }
            }
        }
        
        in.close();
    }
}
