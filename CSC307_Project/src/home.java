import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.*;

public class home {
	public static void main(String[] args) throws Exception
	{
		JFrame frame = new JFrame("Home Page");
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		//Department drop down box
		ArrayList<String> deptArrayList = getDeparments();
		String[] deptArray = deptArrayList.toArray(new String[deptArrayList.size()]);
		JComboBox<String> deptList = new JComboBox <String> (deptArray);
		panel.add(deptList);
		
		//Class search bar
		JTextField searchbar = new JTextField("Class Number", 50);
		panel.add(searchbar);
		
		//Search button
		JButton button = new JButton("Search");
		button.setSize(new Dimension(50,50));
		panel.add(button);
		
		frame.setPreferredSize(new Dimension(1440,830));
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	//Gets all departments for drop down box
	public static ArrayList<String> getDeparments() throws FileNotFoundException
	{
		File manifesto = new File("manifesto.txt");
		Scanner scan = new Scanner(manifesto);
		String department;
		ArrayList<String> list = new ArrayList<String>();
		
		while(scan.nextLine() != null)
		{
			scan.useDelimiter(",");
			if(scan.hasNext())
			{
				department = scan.next();
				list.add(department);
			}
			else
				break;
		}
		
		scan.close();
		return list;
	}
}

