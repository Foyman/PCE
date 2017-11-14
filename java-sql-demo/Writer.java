// Bibek Shrestha 
// //  bshresth@calpoly.edu
// Wenmin He
// // whe01@calpoly.edu
// Lab 8

import java.sql.*;
import java.io.*;
import java.util.*;

public class Writer {
	private static Connection conn;

	public static void printTable(BufferedWriter bw, ResultSet rs) throws Exception {
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		bw.write("<table>");

		bw.write("<tr>");
		for(int i = 1; i <= columnsNumber; i++)
		{
			bw.write("<th>" + rsmd.getColumnName(i) +"</th>");
		}
		bw.write("</tr>");

		while(rs.next()) {
			bw.write("<tr>");
			
			for (int i = 1; i <= columnsNumber; i++) {
				
				bw.write("<th>" +rs.getString(i)+"</th>");
			}
			bw.write("</tr>");
		}
		bw.write("</table>");
		
		bw.write("<p>");
		bw.write("--------------------------------------------------------------------");
		bw.write("</p>");
	}
	
	public static void processGeneralQuery(BufferedWriter bw, String query, ArrayList<String> stringArr) throws Exception {
		writeQuesComm(bw, stringArr);	

		// Gets the result and pass it to printTable
		// Place holder for now
		
		try {
			Statement s = conn.createStatement();
			ResultSet result = s.executeQuery(query);
			printTable(bw, result);
		} catch(SQLException e) {
			
		}

		
	}

	public static void writeQuesComm(BufferedWriter bw, ArrayList<String> arr) throws Exception {
		for (int i = 0; i < arr.size(); i++) {
			if (i == 0) {
				bw.write("<h3>");
				bw.write(arr.get(i));
				bw.write("</h3>");
			}
			else {
				bw.write("<p>");
				bw.write(arr.get(i));
				bw.write("</p>");
			}
		}
		arr.clear();
	}

	public static void addStyle(BufferedWriter bw) throws Exception {
		bw.write("<style>");
		bw.write("table, th, td {border: 1px solid black;}");
		bw.write("</style>");	
	}


	public static void htmlHeader() {
		FileWriter fWriter = null;
		BufferedWriter writer = null;
		try {
			fWriter = new FileWriter("General.html");
			writer = new BufferedWriter(fWriter);
			writer.write("<span>This iss your html content here</span>");
  			writer.newLine(); //this is not actually needed for html files - can make your code more readable though 
    		writer.close(); //make sure you close the writer object 
    	} catch (Exception e) {
  		//catch any exceptions here
    		System.out.println("Can not open file");
    	}
    }


    public static void writeGeneralHTML(BufferedWriter bw) throws Exception {
    	bw.write("<html>");

    	addStyle(bw);

    	bw.write("<body>");
    	bw.write("<h1>General Analytical Report</h1>");
    	ArrayList<String> stringArr = new ArrayList<String>();

		// Query1A
    	String question1A = "Total Number of Securities traded at the start of 2016";
    	String query1A = "SELECT COUNT(*) AS numTradesStart2016" +
    	"	FROM Prices" +
    	" 	WHERE Day = ("+
    	" 		SELECT MIN(Day)" +
    	" 		FROM Prices" +
    	"		WHERE Year(Day) = 2016)";
    	stringArr.add(question1A);
    	processGeneralQuery(bw, query1A, stringArr);


		// Query1B
    	String question1B = "Total Number of Securities traded at the end of 2016";
    	String query1B = "SELECT COUNT(*) AS numTradesEnd2016" +
    	"		FROM Prices" +
    	"		WHERE Day = (" +
    	"			SELECT MAX(Day)" +
    	"			FROM Prices" +
    	"			WHERE Year(Day) = 2016)";
    	stringArr.add(question1B);
    	processGeneralQuery(bw, query1B, stringArr);

		// Query1C
    	String question1C = "Total Number of Securities with price increase from end of 2015 to end of 2016";
    	String query1C = "SELECT COUNT(*) AS NumSecurities "+
    	"	FROM Prices p1, Prices p2," +
    	"	(SELECT Ticker, MAX(Day) AS LastDay" +
    	"		FROM Prices" +
    	"		WHERE Year(Day) = 2015" +
    	"		GROUP BY Ticker) t1," +
    	"	(SELECT Ticker, MAX(Day) AS LastDay" +
    	"		FROM Prices" +
    	"		WHERE Year(Day) = 2016" +
    	"		GROUP BY Ticker) t2" +
    	"	WHERE t1.Ticker = t2.Ticker" +
    	"	AND p1.Ticker = t1.Ticker" +
    	"	AND p2.Ticker = t2.Ticker" +
    	"	AND p1.Day = t1.LastDay" +
    	"	AND p2.Day = t2.LastDay" +
    	"	AND p1.Close < p2.Close";
    	stringArr.add(question1C);
    	processGeneralQuery(bw, query1C, stringArr);
		
    	// Query1D
    	String question1D = "Total Number of Securities with price decrease from end of 2015 to end of 2016";
    	String query1D = "SELECT COUNT(*) AS NumSecurities" +
    	"	FROM Prices p1, Prices p2," +
    	"	(SELECT Ticker, MAX(Day) AS LastDay" +
    	"		FROM Prices" +
    	"		WHERE Year(Day) = 2015" +
    	"		GROUP BY Ticker) t1," +
    	"	(SELECT Ticker, MAX(Day) AS LastDay" +
    	"		FROM Prices" +
    	"		WHERE Year(Day) = 2016" +
    	"		GROUP BY Ticker) t2" +
    	"	WHERE t1.Ticker = t2.Ticker" +
    	"	AND p1.Ticker = t1.Ticker" +
    	"	AND p2.Ticker = t2.Ticker" +
    	"	AND p1.Day = t1.LastDay" +
    	"	AND p2.Day = t2.LastDay" +
    	"	AND p1.Close > p2.Close";
    	stringArr.add(question1D);
    	processGeneralQuery(bw, query1D, stringArr);

		// Query2
    	String question2 = "Top 10 stocks with highest trading volume";
    	String query2 = "SELECT Ticker, SUM(Volume) AS totalTrade" +
    	"	FROM Prices" +
    	"	WHERE Year(Day) = 2016" +
    	"	GROUP BY Ticker" +
    	"	ORDER BY totalTrade DESC" +
    	"	LIMIT 10";
    	stringArr.add(question2);
    	processGeneralQuery(bw, query2, stringArr);

		// Query3A
    	String question3A = "Top 5 stocks with highest absolute price increase in 2010";
    	String query3A = "SELECT p1.Ticker ,(p2.Close - p1.Open) AS AbsolutePerformance2010" +
    	"	FROM Prices p1, Prices p2," +
    	"	(SELECT Ticker, MIN(Day) AS minDate, MAX(Day) AS maxDate" +
    	"		FROM Prices" +
    	"		WHERE Year(Day) = 2010" +
    	"		GROUP BY Ticker) t1" +
    	"	WHERE t1.Ticker = p1.Ticker" +
    	"	AND t1.Ticker = p2.Ticker" +
    	"	AND t1.minDate = p1.Day" +
    	"	AND t1.maxDate = p2.Day" +
    	"	ORDER BY AbsolutePerformance2010 DESC" +
    	"	LIMIT 5";
    	stringArr.add(question3A);
    	processGeneralQuery(bw, query3A, stringArr);

		// Query3B
    	String question3B = "Top 5 stocks with highest relative price increase in 2010";
    	String query3B = "SELECT p1.Ticker," +
    	"	(p2.Close - p1.Open)/ p1.Open AS RelativePerformance2010" +
    	"	FROM Prices p1, Prices p2," +
    	"	(SELECT Ticker, MIN(Day) AS minDate, MAX(Day) AS maxDate" +
    	"		FROM Prices" +
    	"		WHERE Year(Day) = 2010" +
    	"		GROUP BY Ticker) t1" +
    	"	WHERE t1.Ticker = p1.Ticker" +
    	"	AND t1.Ticker = p2.Ticker" +
    	"	AND t1.minDate = p1.Day" +
    	"	AND t1.maxDate = p2.Day" +
    	"	ORDER BY RelativePerformance2010 DESC" +
    	"	LIMIT 5";
    	stringArr.add(question3B);
    	processGeneralQuery(bw, query3B, stringArr);


		// Query3C
    	String question3C = "Top 5 stocks with highest absolute price increase in 2011";
    	String query3C = "SELECT p1.Ticker ,(p2.Close - p1.Open) AS AbsolutePerformance2011" +
    	"	FROM Prices p1, Prices p2," +
    	"	(SELECT Ticker, MIN(Day) AS minDate, MAX(Day) AS maxDate" +
    	"		FROM Prices" +
    	"		WHERE Year(Day) = 2011" +
    	"		GROUP BY Ticker) t1" +
    	"	WHERE t1.Ticker = p1.Ticker" +
    	"	AND t1.Ticker = p2.Ticker" +
    	"	AND t1.minDate = p1.Day" +
    	"	AND t1.maxDate = p2.Day" +
    	"	ORDER BY AbsolutePerformance2011 DESC" +
    	"	LIMIT 5";
    	stringArr.add(question3C);
    	processGeneralQuery(bw, query3C, stringArr);

		// Query3D
    	String question3D = "Top 5 stocks with highest relative price increase in 2011";
    	String query3D = "SELECT p1.Ticker," +
    	"	(p2.Close - p1.Open)/ p1.Open AS RelativePerformance2011" +
    	"	FROM Prices p1, Prices p2," +
    	"	(SELECT Ticker, MIN(Day) AS minDate, MAX(Day) AS maxDate" +
    	"		FROM Prices" +
    	"		WHERE Year(Day) = 2011" +
    	"		GROUP BY Ticker) t1" +
    	"	WHERE t1.Ticker = p1.Ticker" +
    	"	AND t1.Ticker = p2.Ticker" +
    	"	AND t1.minDate = p1.Day" +
    	"	AND t1.maxDate = p2.Day" +
    	"	ORDER BY RelativePerformance2011 DESC" +
    	"	LIMIT 5";
    	stringArr.add(question3D);
    	processGeneralQuery(bw, query3D, stringArr);

		// Query3E
    	String question3E = "Top 5 stocks with highest absolute price increase in 2012";
    	String query3E = "SELECT p1.Ticker ,(p2.Close - p1.Open) AS AbsolutePerformance2012" +
    	"	FROM Prices p1, Prices p2," +
    	"	(SELECT Ticker, MIN(Day) AS minDate, MAX(Day) AS maxDate" +
    	"		FROM Prices" +
    	"		WHERE Year(Day) = 2012" +
    	"		GROUP BY Ticker) t1" +
    	"	WHERE t1.Ticker = p1.Ticker" +
    	"	AND t1.Ticker = p2.Ticker" +
    	"	AND t1.minDate = p1.Day" +
    	"	AND t1.maxDate = p2.Day" +
    	"	ORDER BY AbsolutePerformance2012 DESC" +
    	"	LIMIT 5";
    	stringArr.add(question3E);
    	processGeneralQuery(bw, query3E, stringArr);
    	bw.write("</body>");
    	bw.write("</html>");

		// Query3F
    	String question3F = "Top 5 stocks with highest relative price increase in 2012";
    	String query3F = "SELECT p1.Ticker," +
    	"	(p2.Close - p1.Open)/ p1.Open AS RelativePerformance2012" +
    	"	FROM Prices p1, Prices p2," +
    	"	(SELECT Ticker, MIN(Day) AS minDate, MAX(Day) AS maxDate" +
    	"		FROM Prices" +
    	"		WHERE Year(Day) = 2012" +
    	"		GROUP BY Ticker) t1" +
    	"	WHERE t1.Ticker = p1.Ticker" +
    	"	AND t1.Ticker = p2.Ticker" +
    	"	AND t1.minDate = p1.Day" +
    	"	AND t1.maxDate = p2.Day" +
    	"	ORDER BY RelativePerformance2012 DESC" +
    	"	LIMIT 5";
    	stringArr.add(question3F);
    	processGeneralQuery(bw, query3F, stringArr);

		// Query3G
    	String question3G = "Top 5 stocks with highest absolute price increase in 2013";
    	String query3G = "SELECT p1.Ticker ,(p2.Close - p1.Open) AS AbsolutePerformance2013" +
    	"		FROM Prices p1, Prices p2," +
    	"		(SELECT Ticker, MIN(Day) AS minDate, MAX(Day) AS maxDate" +
    	"			FROM Prices" +
    	"			WHERE Year(Day) = 2013" +
    	"			GROUP BY Ticker) t1" +
    	"		WHERE t1.Ticker = p1.Ticker" +
    	"		AND t1.Ticker = p2.Ticker" +
    	"		AND t1.minDate = p1.Day" +
    	"		AND t1.maxDate = p2.Day" +
    	"		ORDER BY AbsolutePerformance2013 DESC" +
    	"		LIMIT 5"; 
    	stringArr.add(question3G);
    	processGeneralQuery(bw, query3G, stringArr);


		// Query3H
    	String question3H = "Top 5 stocks with highest relative price increase in 2013";
    	String query3H ="SELECT p1.Ticker," +
    	"		(p2.Close - p1.Open)/ p1.Open AS RelativePerformance2013" +
    	"		FROM Prices p1, Prices p2," +
    	"		(SELECT Ticker, MIN(Day) AS minDate, MAX(Day) AS maxDate" +
    	"			FROM Prices" +
    	"			WHERE Year(Day) = 2013" +
    	"			GROUP BY Ticker) t1" +
    	"		WHERE t1.Ticker = p1.Ticker" +
    	"		AND t1.Ticker = p2.Ticker" +
    	"		AND t1.minDate = p1.Day" +
    	"		AND t1.maxDate = p2.Day" +
    	"		ORDER BY RelativePerformance2013 DESC" +
    	"		LIMIT 5";
    	stringArr.add(question3H);
    	processGeneralQuery(bw, query3H, stringArr);


		// Query3I
    	String question3I = "Top 5 stocks with highest absolute price increase in 2014";
    	String query3I = "SELECT p1.Ticker ,(p2.Close - p1.Open) AS AbsolutePerformance2014" +
    	"		FROM Prices p1, Prices p2," +
    	"		(SELECT Ticker, MIN(Day) AS minDate, MAX(Day) AS maxDate" +
    	"			FROM Prices" +
    	"			WHERE Year(Day) = 2014" +
    	"			GROUP BY Ticker) t1" +
    	"		WHERE t1.Ticker = p1.Ticker" +
    	"		AND t1.Ticker = p2.Ticker" +
    	"		AND t1.minDate = p1.Day" +
    	"		AND t1.maxDate = p2.Day" +
    	"		ORDER BY AbsolutePerformance2014 DESC" +
    	"		LIMIT 5";
    	stringArr.add(question3I);
    	processGeneralQuery(bw, query3I, stringArr);
    	bw.write("</body>");
    	bw.write("</html>");

		// Query3J
    	String question3J = "Top 5 stocks with highest relative price increase in 2014";
    	String query3J = "SELECT p1.Ticker," +
    	"		(p2.Close - p1.Open)/ p1.Open AS RelativePerformance2014" +
    	"		FROM Prices p1, Prices p2," +
    	"		(SELECT Ticker, MIN(Day) AS minDate, MAX(Day) AS maxDate" +
    	"			FROM Prices" +
    	"			WHERE Year(Day) = 2014" +
    	"			GROUP BY Ticker) t1" +
    	"		WHERE t1.Ticker = p1.Ticker" +
    	"		AND t1.Ticker = p2.Ticker" +
    	"		AND t1.minDate = p1.Day" +
    	"		AND t1.maxDate = p2.Day" +
    	"		ORDER BY RelativePerformance2014 DESC" +
    	"		LIMIT 5";
    	stringArr.add(question3J);
    	processGeneralQuery(bw, query3J, stringArr);


		// Query3K
    	String question3K = "Top 5 stocks with highest absolute price increase in 2015";
    	String query3K = "SELECT p1.Ticker ,(p2.Close - p1.Open) AS AbsolutePerformance2015" +
    	"		FROM Prices p1, Prices p2," +
    	"		(SELECT Ticker, MIN(Day) AS minDate, MAX(Day) AS maxDate" +
    	"			FROM Prices" +
    	"			WHERE Year(Day) = 2015" +
    	"			GROUP BY Ticker) t1" +
    	"		WHERE t1.Ticker = p1.Ticker" +
    	"		AND t1.Ticker = p2.Ticker" +
    	"		AND t1.minDate = p1.Day" +
    	"		AND t1.maxDate = p2.Day" +
    	"		ORDER BY AbsolutePerformance2015 DESC" +
    	"		LIMIT 5";
    	stringArr.add(question3K);
    	processGeneralQuery(bw, query3K, stringArr);
    	bw.write("</body>");
    	bw.write("</html>");

		// Query3L
    	String question3L = "Top 5 stocks with highest relative price increase in 2015";
    	String query3L = "SELECT p1.Ticker," +
    	"		(p2.Close - p1.Open)/ p1.Open AS RelativePerformance2015" +
    	"		FROM Prices p1, Prices p2," +
    	"		(SELECT Ticker, MIN(Day) AS minDate, MAX(Day) AS maxDate" +
    	"			FROM Prices" +
    	"			WHERE Year(Day) = 2015" +
    	"			GROUP BY Ticker) t1" +
    	"		WHERE t1.Ticker = p1.Ticker" +
    	"		AND t1.Ticker = p2.Ticker" +
    	"		AND t1.minDate = p1.Day" +
    	"		AND t1.maxDate = p2.Day" +
    	"		ORDER BY RelativePerformance2015 DESC" +
    	"		LIMIT 5";
    	stringArr.add(question3L);
    	processGeneralQuery(bw, query3L, stringArr);

		// Query3M
    	String question3M = "Top 5 stocks with highest absolute price increase in 2016";
    	String query3M = "SELECT p1.Ticker," +
    	"		(p2.Close - p1.Open) AS AbsolutePerformance2016" +
    	"		FROM Prices p1, Prices p2," +
    	"		(SELECT Ticker, MIN(Day) AS minDate, MAX(Day) AS maxDate" +
    	"			FROM Prices" +
    	"			WHERE Year(Day) = 2016" +
    	"			GROUP BY Ticker) t1" +
    	"		WHERE t1.Ticker = p1.Ticker" +
    	"		AND t1.Ticker = p2.Ticker" +
    	"		AND t1.minDate = p1.Day" +
    	"		AND t1.maxDate = p2.Day" +
    	"		ORDER BY AbsolutePerformance2016 DESC" +
    	"		LIMIT 5";
    	stringArr.add(question3M);
    	processGeneralQuery(bw, query3M, stringArr);
    	bw.write("</body>");
    	bw.write("</html>");

		// Query3N
    	String question3N = "Top 5 stocks with highest relative price increase in 2016";
    	String query3N = "SELECT p1.Ticker," +
    	"		(p2.Close - p1.Open)/ p1.Open AS RelativePerformance2016" +
    	"		FROM Prices p1, Prices p2," +
    	"		(SELECT Ticker, MIN(Day) AS minDate, MAX(Day) AS maxDate" +
    	"			FROM Prices" +
    	"			WHERE Year(Day) = 2016" +
    	"			GROUP BY Ticker) t1" +
    	"		WHERE t1.Ticker = p1.Ticker" +
    	"		AND t1.Ticker = p2.Ticker" +
    	"		AND t1.minDate = p1.Day" +
    	"		AND t1.maxDate = p2.Day" +
    	"		ORDER BY RelativePerformance2016 DESC" +
    	"		LIMIT 5";
    	stringArr.add(question3N);
    	processGeneralQuery(bw, query3N, stringArr);

		// Query4
    	String question4 = "Top 10 stocks with best performance every month in 2017";
    	String question4Comment = "--The performance is based calculate based on the absolute price increase" +
    	" from opening price to the closing price--";
    	String query4 = "SELECT p1.Ticker," + 
    	"		(p2.Close - p1.Open) / p1.Open AS RelativePerformance" +
    	"		FROM Prices p1, Prices p2," +
    	"		(" +
    	"			SELECT Ticker, MIN(Day) AS minDate, MAX(Day) AS maxDate" +
    	"			FROM Prices" +
    	"			WHERE YEAR(Day) = 2016" +
    	"			GROUP BY Ticker) t1" +
    	"		WHERE t1.Ticker = p1.Ticker" +
    	"		AND t1.Ticker = p2.Ticker" +
    	"		AND t1.minDate = p1.Day" +
    	"		AND t1.maxDate = p2.Day" +
    	"		ORDER BY RelativePerformance" +
    	"		LIMIT 10";
    	stringArr.add(question4);
    	stringArr.add(question4Comment);
    	processGeneralQuery(bw, query4, stringArr);

		// Query5A
    	String question5A = "We believe that Stock are Performing well when the relative Price increase"+
    	" is above 7% about right when price increase is about 7%, and underperformaning under 7%";
    	String question5AComment = "Health Care Sector";
    	String query5A = "SELECT t1.Sector," +
    	"		CASE" + 
    	"		WHEN AVG(t1.Performance) > 7 THEN 'Out-Performing'" +
    	"		WHEN AVG(t1.Performance) < 7 THEN 'Under-Performing'" +
    	"		ELSE 'Average Performance'" +
    	"		END AS 'Performance'" +
    	"		FROM (" +
    	"			SELECT t1.Sector," +
    	"			(p2.Close - p1.Open) / p1.Open AS Performance" +
    	"			FROM Prices p1, Prices p2," +
    	"			(" +
    	"				SELECT s.Sector, s.Ticker," + 
    	"				MIN(p.Day) AS minDate, MAX(p.Day) AS maxDate" +
    	"				FROM Securities s, Prices p " +
    	"				WHERE s.Ticker = p.Ticker" +
    	"				AND YEAR(p.Day) = 2016" +
    	"				AND s.Sector = 'Health Care'" +
    	"				GROUP BY s.Ticker) t1" +
    	"			WHERE p1.Ticker = t1.Ticker" +
    	"			AND p2.Ticker = t1.Ticker" +
    	"			AND p1.Day = t1.minDate" +
    	"			AND p2.Day = t1.maxDate) t1";
    	stringArr.add(question5A);
    	stringArr.add(question5AComment);
    	processGeneralQuery(bw, query5A, stringArr);

		// Query5B
    	String question5B = "Industrials Sector";
    	String query5B = "SELECT t1.Sector," +
    	"		CASE" + 
    	"		WHEN AVG(t1.Performance) > 7 THEN 'Out-Performing'" +
    	"		WHEN AVG(t1.Performance) < 7 THEN 'Under-Performing'" +
    	"		ELSE 'Average Performance'" +
    	"		END AS 'Performance'" +
    	"		FROM (" +
    	"			SELECT t1.Sector," +
    	"			(p2.Close - p1.Open) / p1.Open AS Performance" +
    	"			FROM Prices p1, Prices p2," +
    	"			(" +
    	"				SELECT s.Sector, s.Ticker," + 
    	"				MIN(p.Day) AS minDate, MAX(p.Day) AS maxDate" +
    	"				FROM Securities s, Prices p " +
    	"				WHERE s.Ticker = p.Ticker" +
    	"				AND YEAR(p.Day) = 2016" +
    	"				AND s.Sector = 'Industrials'" +
    	"				GROUP BY s.Ticker) t1" +
    	"			WHERE p1.Ticker = t1.Ticker" +
    	"			AND p2.Ticker = t1.Ticker" +
    	"			AND p1.Day = t1.minDate" +
    	"			AND p2.Day = t1.maxDate) t1";
    	stringArr.add(question5B);
    	processGeneralQuery(bw, query5B, stringArr);

		// Query5C
    	String question5C = "Consumer Discretionary";
    	String query5C = "SELECT t1.Sector," +
    	"		CASE" + 
    	"		WHEN AVG(t1.Performance) > 7 THEN 'Out-Performing'" +
    	"		WHEN AVG(t1.Performance) < 7 THEN 'Under-Performing'" +
    	"		ELSE 'Average Performance'" +
    	"		END AS 'Performance'" +
    	"		FROM (" +
    	"			SELECT t1.Sector," +
    	"			(p2.Close - p1.Open) / p1.Open AS Performance" +
    	"			FROM Prices p1, Prices p2," +
    	"			(" +
    	"				SELECT s.Sector, s.Ticker," + 
    	"				MIN(p.Day) AS minDate, MAX(p.Day) AS maxDate" +
    	"				FROM Securities s, Prices p " +
    	"				WHERE s.Ticker = p.Ticker" +
    	"				AND YEAR(p.Day) = 2016" +
    	"				AND s.Sector = 'Consumer Discretionary'" +
    	"				GROUP BY s.Ticker) t1" +
    	"			WHERE p1.Ticker = t1.Ticker" +
    	"			AND p2.Ticker = t1.Ticker" +
    	"			AND p1.Day = t1.minDate" +
    	"			AND p2.Day = t1.maxDate) t1";
    	stringArr.add(question5C);
    	processGeneralQuery(bw, query5C, stringArr);

		// Query5D
    	String question5D = "Information Technology";
    	String query5D = "SELECT t1.Sector," +
    	"		CASE" + 
    	"		WHEN AVG(t1.Performance) > 7 THEN 'Out-Performing'" +
    	"		WHEN AVG(t1.Performance) < 7 THEN 'Under-Performing'" +
    	"		ELSE 'Average Performance'" +
    	"		END AS 'Performance'" +
    	"		FROM (" +
    	"			SELECT t1.Sector," +
    	"			(p2.Close - p1.Open) / p1.Open AS Performance" +
    	"			FROM Prices p1, Prices p2," +
    	"			(" +
    	"				SELECT s.Sector, s.Ticker," + 
    	"				MIN(p.Day) AS minDate, MAX(p.Day) AS maxDate" +
    	"				FROM Securities s, Prices p " +
    	"				WHERE s.Ticker = p.Ticker" +
    	"				AND YEAR(p.Day) = 2016" +
    	"				AND s.Sector = 'Information Technology'" +
    	"				GROUP BY s.Ticker) t1" +
    	"			WHERE p1.Ticker = t1.Ticker" +
    	"			AND p2.Ticker = t1.Ticker" +
    	"			AND p1.Day = t1.minDate" +
    	"			AND p2.Day = t1.maxDate) t1";
    	stringArr.add(question5D);
    	processGeneralQuery(bw, query5D, stringArr);


		// Query5E
    	String question5E = "Consumer Staples";
    	String query5E = "SELECT t1.Sector," +
    	"		CASE" + 
    	"		WHEN AVG(t1.Performance) > 7 THEN 'Out-Performing'" +
    	"		WHEN AVG(t1.Performance) < 7 THEN 'Under-Performing'" +
    	"		ELSE 'Average Performance'" +
    	"		END AS 'Performance'" +
    	"		FROM (" +
    	"			SELECT t1.Sector," +
    	"			(p2.Close - p1.Open) / p1.Open AS Performance" +
    	"			FROM Prices p1, Prices p2," +
    	"			(" +
    	"				SELECT s.Sector, s.Ticker," + 
    	"				MIN(p.Day) AS minDate, MAX(p.Day) AS maxDate" +
    	"				FROM Securities s, Prices p " +
    	"				WHERE s.Ticker = p.Ticker" +
    	"				AND YEAR(p.Day) = 2016" +
    	"				AND s.Sector = 'Consumer Staples'" +
    	"				GROUP BY s.Ticker) t1" +
    	"			WHERE p1.Ticker = t1.Ticker" +
    	"			AND p2.Ticker = t1.Ticker" +
    	"			AND p1.Day = t1.minDate" +
    	"			AND p2.Day = t1.maxDate) t1";
    	stringArr.add(question5E);
    	processGeneralQuery(bw, query5E, stringArr);


		// Query5F
    	String question5F = "Utilities";
    	String query5F = "SELECT t1.Sector," +
    	"		CASE " +
    	"		WHEN AVG(t1.Performance) > 7 THEN 'Out-Performing'" +
    	"		WHEN AVG(t1.Performance) < 7 THEN 'Under-Performing'" +
    	"		ELSE 'Average Performance'" +
    	"		END AS 'Performance'" +
    	"		FROM (" +
    	"			SELECT t1.Sector," +
    	"			(p2.Close - p1.Open) / p1.Open AS Performance" +
    	"			FROM Prices p1, Prices p2," +
    	"			(" +
    	"				SELECT s.Sector, s.Ticker," + 
    	"				MIN(p.Day) AS minDate, MAX(p.Day) AS maxDate" +
    	"				FROM Securities s, Prices p" + 
    	"				WHERE s.Ticker = p.Ticker" +
    	"				AND YEAR(p.Day) = 2016" +
    	"				AND s.Sector = 'Utilities'" +
    	"				GROUP BY s.Ticker) t1" +
    	"			WHERE p1.Ticker = t1.Ticker" +
    	"			AND p2.Ticker = t1.Ticker" +
    	"			AND p1.Day = t1.minDate" +
    	"			AND p2.Day = t1.maxDate) t1";
    	stringArr.add(question5F);
    	processGeneralQuery(bw, query5F, stringArr);


		// Query5G
    	String question5G = "Financials";
    	String query5G = "SELECT t1.Sector," +
    	"		CASE" + 
    	"		WHEN AVG(t1.Performance) > 7 THEN 'Out-Performing'" +
    	"		WHEN AVG(t1.Performance) < 7 THEN 'Under-Performing'" +
    	"		ELSE 'Average Performance'" +
    	"		END AS 'Performance'" +
    	"		FROM (" +
    	"			SELECT t1.Sector," +
    	"			(p2.Close - p1.Open) / p1.Open AS Performance" +
    	"			FROM Prices p1, Prices p2," +
    	"			(" +
    	"				SELECT s.Sector, s.Ticker," + 
    	"				MIN(p.Day) AS minDate, MAX(p.Day) AS maxDate" +
    	"				FROM Securities s, Prices p " +
    	"				WHERE s.Ticker = p.Ticker" +
    	"				AND YEAR(p.Day) = 2016" +
    	"				AND s.Sector = 'Financials'" +
    	"				GROUP BY s.Ticker) t1" +
    	"			WHERE p1.Ticker = t1.Ticker" +
    	"			AND p2.Ticker = t1.Ticker" +
    	"			AND p1.Day = t1.minDate" +
    	"			AND p2.Day = t1.maxDate) t1";
    	stringArr.add(question5G);
    	processGeneralQuery(bw, query5G, stringArr);


		// Query5H
    	String question5H = "Real Estate";
    	String query5H = "SELECT t1.Sector," +
    	"		CASE" + 
    	"		WHEN AVG(t1.Performance) > 7 THEN 'Out-Performing'" +
    	"		WHEN AVG(t1.Performance) < 7 THEN 'Under-Performing'" +
    	"		ELSE 'Average Performance'" +
    	"		END AS 'Performance'" +
    	"		FROM (" +
    	"			SELECT t1.Sector," +
    	"			(p2.Close - p1.Open) / p1.Open AS Performance" +
    	"			FROM Prices p1, Prices p2," +
    	"			(" +
    	"				SELECT s.Sector, s.Ticker," + 
    	"				MIN(p.Day) AS minDate, MAX(p.Day) AS maxDate" +
    	"				FROM Securities s, Prices p " +
    	"				WHERE s.Ticker = p.Ticker" +
    	"				AND YEAR(p.Day) = 2016" +
    	"				AND s.Sector = 'Real Estate'" +
    	"				GROUP BY s.Ticker) t1" +
    	"			WHERE p1.Ticker = t1.Ticker" +
    	"			AND p2.Ticker = t1.Ticker" +
    	"			AND p1.Day = t1.minDate" +
    	"			AND p2.Day = t1.maxDate) t1";
    	stringArr.add(question5H);
    	processGeneralQuery(bw, query5H, stringArr);


		// Query5I
    	String question5I = "Materials";
    	String query5I = "SELECT t1.Sector," +
    	"		CASE " +
    	"		WHEN AVG(t1.Performance) > 7 THEN 'Out-Performing'" +
    	"		WHEN AVG(t1.Performance) < 7 THEN 'Under-Performing'" +
    	"		ELSE 'Average Performance'" +
    	"		END AS 'Performance'" +
    	"		FROM (" +
    	"			SELECT t1.Sector," +
    	"			(p2.Close - p1.Open) / p1.Open AS Performance" +
    	"			FROM Prices p1, Prices p2," +
    	"			(" +
    	"				SELECT s.Sector, s.Ticker," + 
    	"				MIN(p.Day) AS minDate, MAX(p.Day) AS maxDate" +
    	"				FROM Securities s, Prices p " +
    	"				WHERE s.Ticker = p.Ticker" +
    	"				AND YEAR(p.Day) = 2016" +
    	"				AND s.Sector = 'Materials'" +
    	"				GROUP BY s.Ticker) t1" +
    	"			WHERE p1.Ticker = t1.Ticker" +
    	"			AND p2.Ticker = t1.Ticker" +
    	"			AND p1.Day = t1.minDate" +
    	"			AND p2.Day = t1.maxDate) t1";
    	stringArr.add(question5I);
    	processGeneralQuery(bw, query5I, stringArr);


		// Query5J
    	String question5J = "Energy";
    	String query5J = "SELECT t1.Sector," +
    	"		CASE" + 
    	"		WHEN AVG(t1.Performance) > 7 THEN 'Out-Performing'" +
    	"		WHEN AVG(t1.Performance) < 7 THEN 'Under-Performing'" +
    	"		ELSE 'Average Performance'" +
    	"		END AS 'Performance'" +
    	"		FROM (" +
    	"			SELECT t1.Sector," +
    	"			(p2.Close - p1.Open) / p1.Open AS Performance" +
    	"			FROM Prices p1, Prices p2," +
    	"			(" +
    	"				SELECT s.Sector, s.Ticker," + 
    	"				MIN(p.Day) AS minDate, MAX(p.Day) AS maxDate" +
    	"				FROM Securities s, Prices p " +
    	"				WHERE s.Ticker = p.Ticker" +
    	"				AND YEAR(p.Day) = 2016" +
    	"				AND s.Sector = 'Energy'" +
    	"				GROUP BY s.Ticker) t1" +
    	"			WHERE p1.Ticker = t1.Ticker" +
    	"			AND p2.Ticker = t1.Ticker" +
    	"			AND p1.Day = t1.minDate" +
    	"			AND p2.Day = t1.maxDate) t1";
    	stringArr.add(question5J);
    	processGeneralQuery(bw, query5J, stringArr);

    	bw.write("</body>");
    	bw.write("</html>");

    	bw.close();
    }

    public static void writeHTML(BufferedWriter bw, String src) throws Exception {
    	bw.write("<html>");

    	addStyle(bw);

    	bw.write("<body>");
    	bw.write("<h1>Analytical Report for " +src+"</h1>");
    	ArrayList<String> stringArr = new ArrayList<String>();

		// Query1
    	String question1 = "Range of dates for which the pricing data is available";
    	String query1 = "SELECT COUNT(*)" +
    	"		FROM Prices" +
    	"		WHERE Ticker = '"+ src + "'";
    	stringArr.add(question1);
    	processGeneralQuery(bw, query1, stringArr);

		// Query2
    	String question2 = "Stock performance for every year from 2010 - 2016";
    	String query2 = "SELECT YEAR(p1.Day)," +
    	"		CASE " +
    	"		WHEN p1.Open > p2.Close THEN 'Increase'" + 
    	"		WHEN p1.Open < p2.Close THEN 'Decrease'" +
    	"		ELSE 'No Change' " +
    	"		END AS 'Status'," +
    	"		t1.totalVolume, t1.AvgClosingPrice, t1.VolumePerDay" +
    	"		FROM Prices p1, Prices p2," +
    	"		(SELECT MAX(Day) AS LastDay, SUM(Volume) AS totalVolume," +
    	"			AVG(Close) AS AvgClosingPrice, AVG(Volume) AS VolumePerDay" +
    	"			FROM Prices" +
    	"			WHERE Ticker = '"+ src + "'" +
    	"			GROUP BY YEAR(Day)) t1," +
    	"		(SELECT MIN(Day) AS FirstDay" +
    	"			FROM Prices" +
    	"			WHERE Ticker = '"+ src + "'" +
    	"			GROUP BY YEAR(Day)) t2" +
    	"		WHERE p1.Day = t1.LastDay" +
    	"		AND p2.Day = t2.FirstDay" +
    	"		AND p1.Ticker = '"+ src + "'" +
    	"		AND p2.Ticker = '"+ src + "'" +
    	"		AND YEAR(t1.LastDay) = YEAR(t2.FirstDay)" +
    	"		ORDER BY p1.Day";
    	stringArr.add(question2);
    	processGeneralQuery(bw, query2, stringArr);

		// Query 3
    	String question3 = "Average closing price, the highest and the lowest price," +
    	" the average daily trading volume by month";
    	String query3 = "SELECT MONTH(p.Day)," +
    	"		AVG(p.Close) AS AvgClosingPrice," + 
    	"		MAX(p.High) AS HighestPrice," +
    	"		MIN(p.Low) AS LowestPrice," +
    	"		AVG(p.Volume) AS AvgVolume" +
    	"		FROM Prices p, ( " +
    	"			SELECT MAX(Day) AS Day" +
    	"			FROM Prices" +
    	"			WHERE Ticker = '"+ src + "') t1" +
    	"		WHERE" +
    	"		YEAR(p.Day) = YEAR(t1.Day)" +
    	"		AND Ticker = '"+ src + "'" +
    	"		GROUP BY MONTH(p.Day)";
    	stringArr.add(question3);
    	processGeneralQuery(bw, query3, stringArr);

		// Query 4
    	String question4 = "Best performance month each year";
    	String question4Comment = "--The performance of a stock every month is calculate based" +
    	" on the absolute price increase from the opening price of first monthly trade to the" +
    	" closing price of last monthly trade. The best performance month of a year is the month" +
    	" with the highest absolute price increase--";
    	String query4 = "SELECT YEAR(PerformanceTable.Day) AS 'Year'," +
    	"		MONTH(PerformanceTable.Day) AS 'Best Performance Month'" +
    	"		FROM" +
    	"		(" +
    	"			SELECT p1.Day, (p1.Open - p2.Close) AS Performance" +
    	"			FROM Prices p1, Prices p2," +
    	"			(SELECT MIN(Day) AS FirstDay, MAX(Day) AS LastDay" +
    	"				FROM Prices" +
    	"				WHERE Ticker = '"+ src + "'" +
    	"				GROUP BY YEAR(Day), MONTH(Day)) t1" +
    	"			WHERE p1.Ticker = '"+ src + "'" +
    	"			AND p1.Ticker = p2.Ticker" +
    	"			AND t1.FirstDay = p1.Day" +
    	"			AND t1.LastDay = p2.Day" +
    	"		) PerformanceTable" +
    	"		WHERE PerformanceTable.Performance IN" + 
    	"		(SELECT MAX(t2.Performance)" +
    	"			FROM (" +
    	"				SELECT p1.Day, (p1.Open - p2.Close) AS Performance" +
    	"				FROM Prices p1, Prices p2," +
    	"				(SELECT MIN(Day) AS FirstDay, MAX(Day) AS LastDay" +
    	"					FROM Prices" +
    	"					WHERE Ticker = '"+ src + "'" +
    	"					GROUP BY YEAR(Day), MONTH(Day)) t1" +
    	"				WHERE p1.Ticker = '"+ src + "'" +
    	"				AND p1.Ticker = p2.Ticker" +
    	"				AND t1.FirstDay = p1.Day" +
    	"				AND t1.LastDay = p2.Day" +
    	"				GROUP BY YEAR(p1.Day), MONTH(p1.Day)) t2" +
    	"			GROUP BY YEAR(t2.Day))";
    	stringArr.add(question4);
    	stringArr.add(question4Comment);
    	processGeneralQuery(bw, query4, stringArr);

		// Query 5
    	String question5 = "My stock position on January 1, 2015, June 1, 2015," +
    	" October 1, 2015, January 1, 2016, May 1, 2016, October 1, 2016";
    	String question5Comment = "--We believe that stock market will eventually recover itself," +
    	" therefore buying the stocks that are decreasing in value will have higher chance of profit--";
    	String query5 = "SELECT Days.ActualDate," +
    	"		CASE" +
    	"		WHEN p.Open < p.Close THEN 'Buy'" +
    	"		WHEN p.Open > p.Close THEN 'Sell'" +
    	"		ELSE 'Hold'" +
    	"		END AS 'Status'" +
    	"	FROM " +
    	"	Prices p," +
    	"	(SELECT PreviousDay, ActualDate" +
    	"	FROM" +
    	"	(SELECT MAX(day) AS PreviousDay, '2015-01-01' AS ActualDate" +
    	"		FROM Prices" +
    	"		WHERE day < '2015-01-01'" +
    	"		AND Ticker = '"+ src + "') t1 UNION" +
    	"	(SELECT MAX(day), '2015-06-01'" +
    	"		FROM Prices" +
    	"		WHERE day < '2015-06-01'" +
    	"		AND Ticker = '"+ src + "') UNION" +
    	"	(SELECT MAX(day), '2015-10-01'" +
    	"		FROM Prices" +
    	"		WHERE day < '2015-10-01'" +
    	"		AND Ticker = '"+ src + "') UNION " +
    	"	(SELECT MAX(day), '2016-01-01'" +
    	"		FROM Prices" +
    	"		WHERE day < '2016-01-01'" +
    	"		AND Ticker = '"+ src + "') UNION" +
    	"	(SELECT MAX(day), '2016-05-01'" + 
    	"		FROM Prices" +
    	"		WHERE day < '2016-05-01') UNION" +
    	"	(SELECT MAX(day), '2016-10-01'" +
    	"		FROM Prices" +
    	"		WHERE day < '2016-10-01'" +
    	"		AND Ticker = '"+ src + "')) Days" +
    	"	WHERE p.Ticker = '"+ src + "'" +
    	"	AND p.Day IN (Days.PreviousDay)";
    	stringArr.add(question5);
    	stringArr.add(question5Comment);
    	processGeneralQuery(bw, query5, stringArr);

		// Query 6
    	String question6 = "Validate whether the previous stock positions are performaning as expected";
    	String question6Comment = "--The performance is based on the absolute price increase. The position is" +
    	" considered good if the performance is positive, bad when the performance is negative, and no difference" +
    	" when the net performance is 0--";
    	String query6 = "SELECT t1.ActualDate," +
    	"		CASE" +
    	"		WHEN ((p1.Open > p2.Close AND t2.Status = 'Sell')" +
    	"			OR" +
    	"			(p1.Open < p2.Close AND t2.Status = 'Buy')) THEN 'Bad Decision'" +
    	"		WHEN ((p1.Open > p2.Close AND t2.Status = 'Buy')" +
    	"			OR" +
    	"			(p1.Open < p2.Close AND t2.Status = 'Sell')) THEN 'Good Decision'" +
    	"		ELSE 'No Change'" +
    	"		END AS Status" +
    	"		FROM Prices p1, Prices p2," +
    	"		(SELECT *" +
    	"		FROM" +
    	"		(SELECT MIN(day) AS minDate," +
    	"			MAX(day) AS maxDate, '2015-01-01' AS ActualDate" +
    	"			FROM Prices" +
    	"			WHERE day >= '2015-01-01'" +
    	"			AND DATEDIFF(day, '2015-01-01') < 180" +
    	"			AND Ticker = '"+ src + "') t1 " +
    	"		UNION" +
    	"		(SELECT MIN(day), MAX(day), '2015-06-01'" +
    	"			FROM Prices" +
    	"			WHERE day >= '2015-06-01'" +
    	"			AND DATEDIFF(day, '2015-06-01') < 180" +
    	"			AND Ticker = '"+ src + "') " +
    	"		UNION" +
    	"		(SELECT MIN(day), MAX(day), '2015-10-01'" +
    	"			FROM Prices" +
    	"			WHERE day >= '2015-10-01'" +
    	"			AND DATEDIFF(day, '2015-10-01') < 180" +
    	"			AND Ticker = '"+ src + "') " +
    	"		UNION " +
    	"		(SELECT MIN(day), MAX(day), '2016-01-01'" +
    	"			FROM Prices" +
    	"			WHERE day >= '2016-01-01'" +
    	"			AND DATEDIFF(day, '2016-01-01') < 180" +
    	"			AND Ticker = '"+ src + "') " +
    	"		UNION" +
    	"		(SELECT MIN(day), MAX(day), '2016-05-01'" + 
    	"			FROM Prices" +
    	"			WHERE day >= '2016-05-01'" +
    	"			AND DATEDIFF(day, '2016-05-01') < 180" +
    	"			AND Ticker = '"+ src + "') " +
    	"		UNION" +
    	"		(SELECT MIN(day), MAX(day), '2016-10-01'" +
    	"			FROM Prices" +
    	"			WHERE day >= '2016-10-01'" +
    	"			AND DATEDIFF(day, '2016-10-01') < 180" +
    	"			AND Ticker = '"+ src + "')) t1," +
    	"		(" +
    	"		SELECT Days.ActualDate," + 
    	"			CASE" +
    	"			WHEN p.Open < p.Close THEN 'Buy'" +
    	"			WHEN p.Open > p.Close THEN 'Sell'" +
    	"			ELSE 'Hold'" +
    	"			END AS Status" +
    	"		FROM " +
    	"		Prices p," +
    	"		(SELECT PreviousDay, ActualDate" +
    	"		FROM" +
    	"		(SELECT MAX(day) AS PreviousDay, '2015-01-01' AS ActualDate" +
    	"			FROM Prices" +
    	"			WHERE day < '2015-01-01'" +
    	"			AND Ticker = '"+ src + "') t1 UNION" +
    	"		(SELECT MAX(day), '2015-06-01'" +
    	"			FROM Prices" +
    	"			WHERE day < '2015-06-01'" +
    	"			AND Ticker = '"+ src + "') UNION" +
    	"		(SELECT MAX(day), '2015-10-01'" +
    	"			FROM Prices" +
    	"			WHERE day < '2015-10-01'" +
    	"			AND Ticker = '"+ src + "') UNION " +
    	"		(SELECT MAX(day), '2016-01-01'" +
    	"			FROM Prices" +
    	"			WHERE day < '2016-01-01'" +
    	"			AND Ticker = '"+ src + "') UNION" +
    	"		(SELECT MAX(day), '2016-05-01' " +
    	"			FROM Prices" +
    	"			WHERE day < '2016-05-01') UNION" +
    	"		(SELECT MAX(day), '2016-10-01'" +
    	"			FROM Prices" +
    	"			WHERE day < '2016-10-01'" +
    	"			AND Ticker = '"+ src + "')) Days" +
    	"		WHERE p.Ticker = '"+ src + "'" +
    	"		AND p.Day IN (Days.PreviousDay)) t2" +
    	"		WHERE t1.minDate = p1.Day" +
    	"		AND p1.Ticker = '"+ src + "'" +
    	"		AND t1.maxDate = p2.Day" +
    	"		AND p1.Ticker = p2.Ticker" +
    	"		AND t2.ActualDate = t1.ActualDate";
    	stringArr.add(question6);
    	stringArr.add(question6Comment);
    	processGeneralQuery(bw, query6, stringArr);

		// Query 7
    	String question7 = "Performance comparison between " + src +" with the top performing stocks";
    	String question7Comment = "--The performance is based on the absolute price increase. The second column" +
    	" indicates the stock which has better performance";
    	String query7 = "SELECT t1.Ticker," +
    	"		CASE" +
    	"		WHEN t1.top5Performance > t1.ANPerformance THEN t1.Ticker" +
    	"		WHEN t1.top5Performance < t1.ANPerformance THEN '"+ src + "'" +
    	"		ELSE 'NO Difference'" +
    	"		END AS 'Performance Status'," +
    	"		CASE" +
    	"		WHEN t1.top5VolumeChange > t1.ANVolumeChange THEN t1.Ticker" +
    	"		WHEN t1.top5Performance < t1.ANVolumeChange THEN '"+ src + "'" +
    	"		ELSE 'NO Difference'" +
    	"		END AS 'Performance Status(higher)'" +
    	"		FROM (" +
    	"			SELECT p1.Ticker, MONTH(p1.Day)," +
    	"			(p2.Close - p1.Open) AS top5Performance," +
    	"			(p4.Close - p3.Open) AS ANPerformance," +
    	"			(p2.Volume - p1.Volume) AS top5VolumeChange," +
    	"			(p4.Volume - p3.Volume) AS ANVolumeChange" +
    	"			FROM Prices p1, Prices p2, Prices p3, Prices p4," +
    	"			(SELECT p1.Ticker, MIN(p1.Day) AS minDate, MAX(p1.Day) AS maxDate," +
    	"				SUM(p1.Volume) AS tradingVolume" +
    	"				FROM Prices p1," +
    	"				(SELECT p1.Ticker, (p2.Close - p1.Open) AS Performance," +
    	"					p1.Day" +
    	"					FROM Prices p1, Prices p2," +
    	"					(SELECT p.Ticker," +
    	"						MIN(p.Day) AS minDate, MAX(p.Day) AS maxDate" + 
    	"						FROM Prices p," +
    	"						(SELECT Ticker, MAX(YEAR(Day)) AS LastYear" +
    	"							FROM Prices" +
    	"							GROUP BY Ticker) t1" +
    	"						WHERE p.Ticker = t1.Ticker" +
    	"						AND t1.LastYear = YEAR(p.Day)" +
    	"						GROUP BY p.Ticker) t1" +
    	"					WHERE t1.Ticker = p1.Ticker" +
    	"					AND t1.Ticker = p2.Ticker" +
    	"					AND (p1.Day IN (t1.minDate))" +
    	"					AND (p2.Day IN (t1.maxDate))" +
    	"					ORDER BY Performance DESC " +
    	"					LIMIT 5) t1" +
    	"				WHERE (p1.Ticker IN (t1.Ticker))" +
    	"				AND YEAR(p1.Day) = YEAR(t1.Day)" +
    	"				GROUP BY p1.Ticker, MONTH(p1.Day)) top5," +
    	"			(SELECT MIN(p1.Day) AS minDate, MAX(p1.Day) AS maxDate," +
    	"				SUM(p1.Volume) AS tradingVolume" +
    	"				FROM Prices p1," +
    	"				(SELECT MAX(YEAR(Day)) AS LastYear" +
    	"					FROM Prices" +
    	"					WHERE Ticker = '"+ src + "') t1" +
    	"				WHERE Ticker = '"+ src + "'" +
    	"				AND YEAR(p1.Day) = t1.LastYear" +
    	"				GROUP BY MONTH(p1.Day)) ANDates" +
    	"			WHERE ((p1.Ticker IN (top5.Ticker))" +
    	"				AND" +
    	"				(p2.Ticker IN (top5.Ticker))" +
    	"				AND " +
    	"				(p1.Day IN (top5.minDate))" +
    	"				AND " +
    	"				(p2.Day IN (top5.maxDate)))" +
    	"			AND" +
    	"			p3.Ticker = '"+ src + "'" +
    	"			AND p4.Ticker = '"+ src + "'" +
    	"			AND ((p3.Day IN (ANDates.minDate))" +
    	"				AND (p4.Day IN (ANDates.maxDate)))" +
    	"			AND MONTH(p3.Day) = MONTH(p2.Day)" +
    	"			AND YEAR(p2.Day) = YEAR(p2.Day)) t1";
    	stringArr.add(question7);
    	stringArr.add(question7Comment);
    	processGeneralQuery(bw, query7, stringArr);

		// Query 8
    	ArrayList<String> labTick = new ArrayList<String>();
        labTick.add("AN");
        labTick.add("NFLX");
        labTick.add("SRE");
        labTick.add("NTAP");
    	String oth = labTick.get(3 - labTick.indexOf(src));
    	String question8 = "Performance comparison between " + src +" with " + oth;
    	String question8Comment = "--The performance is based on the absolute price increase. The second column" +
    	" indicates the stock which has better performance";
    	String query8 = "SELECT MONTH(t1.Day) AS Month," +
    	"		CASE " +
    	"		WHEN t1.othPerformance > t1.srcPerformance THEN '" + oth + "'" +
    	"		WHEN t1.othPerformance < t1.srcPerformance THEN '" + src + "'" +
    	"		ELSE 'No Difference'" +
    	"		END AS 'Performance Status'" +
    	"		FROM (" +
    	"			SELECT p1.Day," +
    	"			(p2.Close - p1.Open) AS othPerformance," +
    	"			(p4.Close - p3.Open) AS srcPerformance," +
    	"			(p2.Volume - p1.Volume) AS othVolumeChange," +
    	"			(p4.Volume - p3.Volume) AS srcVolumeChange" +
    	"			FROM Prices p1, Prices p2, Prices p3, Prices p4," +
    	"			(SELECT MIN(p1.Day) AS othMinDate, MAX(p1.Day) AS othMaxDate," +
    	"				MIN(p2.Day) AS srcMinDate, MAX(p2.Day) AS srcMaxDate" +
    	"				FROM Prices p1, Prices p2," +
    	"				(SELECT MAX(t1.YEAR) AS LastYear" +
    	"					FROM" +
    	"					(SELECT YEAR(p1.Day) AS Year" +
    	"						FROM Prices p1" +
    	"						WHERE p1.Ticker = '"+ src + "'" +
    	"						GROUP BY YEAR(p1.Day)) t1," +
    	"					(SELECT YEAR(p1.Day) AS Year" +
    	"						FROM Prices p1" +
    	"						WHERE p1.Ticker = '" +oth + "'" +
    	"						GROUP BY YEAR(p1.Day)) t2" +
    	"					WHERE t1.Year = t2.Year) t1" +
    	"				WHERE p1.Ticker = '"+ src + "'" +
    	"				AND p2.Ticker = '" +oth + "'" +
    	"				AND YEAR(p1.Day) = t1.LastYear" +
    	"				AND YEAR(p2.Day) = t1.LastYear" +
    	"				AND MONTH(p1.Day) = MONTH(p2.Day)" +
    	"				GROUP BY p1.Ticker, MONTH(p1.Day)) Days" +
    	"			WHERE YEAR(p1.Day) = YEAR(Days.othMaxDate)" +
    	"			AND YEAR(p2.Day) = YEAR(Days.othMaxDate)" +
    	"			AND YEAR(p3.Day) = YEAR(Days.othMaxDate)" +
    	"			AND YEAR(p4.Day) = YEAR(Days.othMaxDate)" +
    	"			AND p1.Ticker = '" +oth + "'" +
    	"			AND p2.Ticker = '" +oth + "'" +
    	"			AND p3.Ticker = '"+ src + "'" +
    	"			AND p4.Ticker = '"+ src + "'" +
    	"			AND (p1.Day IN (Days.othMinDate)" +
    	"				AND p2.Day IN (Days.othMaxDate)" +
    	"				AND p3.Day IN (Days.srcMinDate)" +
    	"				AND p4.Day IN (Days.srcMaxDate))) t1";
    	stringArr.add(question8);
    	stringArr.add(question8Comment);
    	processGeneralQuery(bw, query8, stringArr);

    	bw.write("</body>");
    	bw.write("</html>");

    	bw.close();
    }

    public static void connectToDB() {
    	try{
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	}
    	catch (Exception ex)
    	{
    		System.out.println("Driver not found");
    		System.out.println(ex);
    	};
    	
    	String uId = null;
    	String pass = null;
    	String dataB = null;
    	
    	String url = "jdbc:mysql://cslvm74.csc.calpoly.edu/";
    	String accinfo = "credentials.in";
    	
    	File  f = new File(accinfo);
    	try {
    		
    		Scanner sc = new Scanner(f);
    		uId = sc.nextLine();
    		pass = sc.nextLine();
    		dataB = sc.nextLine();
    		sc.close();
    		
    	} catch (Exception e) {
    		System.out.println("No such file");
    	}
    	
    	conn = null;
    	url += dataB + "?";
    	
    	try { 
    		conn = DriverManager.getConnection(url, uId, pass);
    	}
    	catch (Exception ex)
    	{
    		System.out.println("Could not open connection");
    		System.out.println(ex);
    	};
    	System.out.println("Connected" );
  
    } 

    public static void main(String args[]) throws Exception {

    	connectToDB();
    	System.out.println("Enter the ticker that you want to general report on OR Press q to quit");
    	Scanner sc = new Scanner(System.in);
    	String userInput = sc.nextLine();
    	while (!(userInput.equalsIgnoreCase("q") || userInput.equalsIgnoreCase("quit"))) {
    		
    		if (userInput.equalsIgnoreCase("general")) {
    			File file = new File("General.html");
    			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
    			
    			writeGeneralHTML(bw);
    		}
    		else {
    			File f = new File(userInput+".html");
    			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
    			writeHTML(bw, userInput);
    		}

    		
    		System.out.println("Finish Generating report for " + userInput);
    		System.out.println("Enter the ticker that you want to general report on OR Press q to quit");
    		userInput = sc.nextLine();
    	}
    	sc.close();

    }
}
