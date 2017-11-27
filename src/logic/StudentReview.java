//This is for getting the student reviews for a class from SQL

package logic;

public class StudentReview
{
    public static double criteria1;
    public static double criteria2;
    public static double criteria3;
    public static String grade;
    public static String review;
    
    private StudentReview()
    {
        
    }
    
    private StudentReview(double criteria1, double criteria2, double criteria3, String grade, String review)
    {
        StudentReview.criteria1 = criteria1;
        StudentReview.criteria2 = criteria2;
        StudentReview.criteria3 = criteria3;
        StudentReview.grade = grade;
        StudentReview.review = review;
    }
    
    public double getCriteria(String varName)
    {
        switch (varName)
        {
            case "criteria1":
                return criteria1;
            case "criteria2":
                return criteria2;
            case "criteria3":
                return criteria3;
            default:
                return 0.0;
        }
    }
}
