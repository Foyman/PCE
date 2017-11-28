//This is for getting the student reviews for a class from SQL

package logic;

public class StudentReview
{
    private double criteria1;
    private double criteria2;
    private double criteria3;
    private String grade;
    private String review;
    private String course;

    public StudentReview()
    {

    }

    public StudentReview(double criteria1, double criteria2, double criteria3, 
            String grade, String review, String c)
    {
        this.criteria1 = criteria1;
        this.criteria2 = criteria2;
        this.criteria3 = criteria3;
        this.grade = grade;
        this.review = review;
        this.course = c;
    }


    public double getCriteria(int criteriaNum)
    {
        switch (criteriaNum)
        {
            case 1:
                return criteria1;
            case 2:
                return criteria2;
            case 3:
                return criteria3;
            default:
                return 0.0;
        }
    }

    public String getReview()
    {
        return review;
    }

    public String getGrade()
    {
        return grade;
    }

    public String getCourse()
    {
        return course;
    }

    public String toString() {
        return (criteria1 + " " + criteria2 + " " + criteria3 + " " + grade + " " + review);
    }

}
