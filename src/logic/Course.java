package logic;

public class Course
{
    // Course Name and Number eg) CSC 307
    public String name;
    // Course Description eg) Introduction to Software Engineering
    public String description;
    // Edit Distance from Search String
    public int distance;

    public Course(String name, String description)
    {
        this.name = name;
        this.description = description;
        this.distance = Integer.MAX_VALUE;
    }

}
