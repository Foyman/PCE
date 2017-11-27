package logic;

import java.util.Comparator;

public class CourseComparator implements Comparator<Course>
{
    /**
     * Compares Edit Distances of the courses to sort an Array
     */
    @Override
    public int compare(Course c1, Course c2)
    {
        int o1 = c1.getDistance();
        int o2 = c2.getDistance();
        if (o1 < o2)
            return -1;
        else if (o1 > o2)
            return 1;
        else
            return 0;
    }

}
