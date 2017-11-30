/**
 * JUnit tests for the DBConnect class
 * 
 * @author Bibek Shrestha
 */
package test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import logic.DBConnect;

public class TestDBConnect
{

    @Test
    public void testprocessGeneralQuery()
    {
        DBConnect.connectToDB();
        String query = "SELECT * FROM Courses LIMIT 10;";
        assertThrows(SQLException.class, () -> {
            DBConnect.processGeneralQuery(query);});
    }
    
    @Test
    public void testprocessUpdateQuery()
    {
        DBConnect.connectToDB();
        String query = "INSERT INTO Courses VALUE (1, \"CSC\", 310, \"Software Engineering IV\");";
        assertThrows(SQLException.class, () -> {
            DBConnect.processUpdateQuery(query);});
    }
}
