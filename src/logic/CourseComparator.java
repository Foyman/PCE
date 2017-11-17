package logic;

import java.util.Comparator;

public class CourseComparator implements Comparator<Course>
{
    /** Compares Edit Distances of the courses to sort an Array
     */
    @Override
    public int compare(Course o1, Course o2)
    {
        if (o1.distance < o2.distance)
            return -1;
        else if (o1.distance > o2.distance)
            return 1;
        else
            return 0;
    }

}
