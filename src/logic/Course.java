package logic;

public class Course
{
    // Course Name and Number eg) CSC 307
    private String name;
    // Course Description eg) Introduction to Software Engineering
    private String description;
    // Edit Distance from Search String
    private int distance;

    public Course(String name, String description)
    {
        this.name = name;
        this.description = description;
        this.distance = Integer.MAX_VALUE;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public int getDistance()
    {
        return distance;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setDistance(int distance)
    {
        this.distance = distance;
    }
    
    public String toString() {
        return name + " " + description;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course myCourse = (Course) o;

        return 
                (this.name.equalsIgnoreCase(myCourse.name)) &&
                (this.description.equalsIgnoreCase(myCourse.description));
    }
    
    public int hashCode() 
    {
       int prime = 31;
       int result = 1;
       result = prime * result;
       if(name != null)
       {
      	 	result += name.hashCode();
       }
       return result;
   }

}
