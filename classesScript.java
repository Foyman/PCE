import java.io.*;
import java.net.*;

public class classesScript
{
	public static void main(String[] args) throws Exception
	{
		//System.out.println("Hello World!\n");
		URL site = new URL("http://catalog.calpoly.edu/coursesaz/");
		BufferedReader in = new BufferedReader(
			new InputStreamReader(site.openStream()));
		PrintStream out = new PrintStream(new FileOutputStream("manifesto.txt"));
	    System.setOut(out);
		getDepartment(in);

		in.close();
	}
	
	public static void getDepartment(BufferedReader in) throws Exception
	{
		String inputLine;
		while ((inputLine = in.readLine()) != null)
		{
			//System.out.println(inputLine);
			if (inputLine.compareTo("<table>") == 0)
			{
				while ((inputLine = in.readLine()).compareTo("</table>") != 0)
				{
					String department = inputLine.substring(inputLine.indexOf("(") + 1, inputLine.indexOf(")"));
					System.out.print(department);
					getClasses(department, getDepartmentURL(inputLine));
				}
			}
		}
	}
	
	public static String getDepartmentURL(String inputLine)
	{
		String link = inputLine.substring(inputLine.indexOf("href=\"") + 6, inputLine.indexOf("\" "));
		link = "http://catalog.calpoly.edu" + link;
		//System.out.println(departmentURL);
		return link;
	}
	
	public static void getClasses(String department, String link) throws Exception
	{
		String classNum;
		URL site = new URL(link);
		BufferedReader in = new BufferedReader(
				new InputStreamReader(site.openStream()));
		
		String inputLine;

		while ((inputLine = in.readLine()) != null)
		{
			if (inputLine.contains("courseblocktitle"))
			{
				classNum = inputLine.substring(inputLine.indexOf(department) + department.length() + 6, inputLine.indexOf("."));
				System.out.print("," + classNum);
			}
		}
		
		System.out.println();
		in.close();
	}
}
