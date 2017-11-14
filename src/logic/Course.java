package logic;

public class Course
{
    public String name;
    public String description;
    public int distance;

    public Course(String name, String description)
    {
        this.name = name;
        this.description = description;
        this.distance = Integer.MAX_VALUE;
    }

}
