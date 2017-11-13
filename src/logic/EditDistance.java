package logic;

import java.lang.Math;
import java.util.Collections;

public class EditDistance
{
    /**
     * Sorts the Course List in Search.java based on the Edit Distance between each
     * course name and the search string.
     * 
     * @param search - The course name to search for
     */
    public static void sortList(String search)
    {
        CourseComparator compare = new CourseComparator();
        search = search.toLowerCase();
        for (Course c : Search.courses)
        {
            c.distance = getDistance(search, c.name);
        }
        Collections.sort(Search.courses, compare);
    }

    /**
     * Returns the Edit Distance of the two strings
     * 
     * @param s1 - First String to compare
     * @param s2 - Second String to compare
     * @return Edit Distance of the two strings
     */
    public static int getDistance(String s1, String s2)
    {
        s1 = s1.replaceAll("\\s", "");
        s2 = s2.replaceAll("\\s", "");
        int len1 = s1.length() + 1;
        int len2 = s2.length() + 1;
        int[][] ED = new int[len1][len2];

        // Loop 1 (Always Runs Once)
        for (int i = 0; i < len1; i++)
        {
            ED[i][0] = i;
        }
        // Loop 2 (Always Runs Once)
        for (int j = 0; j < len2; j++)
        {
            ED[0][j] = j;
        }

        // Loop 3
        for (int i = 1; i < len1; i++)
        {
            for (int j = 1; j < len2; j++)
            {
                int min = Math.min(1 + ED[i - 1][j], 1 + ED[i][j - 1]);

                int x = (s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1) + ED[i - 1][j - 1];
                ED[i][j] = Math.min(min, x);
            }
        }

        return ED[len1 - 1][len2 - 1];
    }

}
