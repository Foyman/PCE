//This is for getting the student reviews for a class from SQL

package logic;

public class StudentReview
{
    private double criteria1; // Workload
    private double criteria2; // Course Content Difficulty
    private double criteria3; // Test Difficulty
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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentReview myReview = (StudentReview) o;

        return (this.criteria1 == myReview.criteria1) &&
                (this.criteria2 == myReview.criteria2) &&
                (this.criteria3 == myReview.criteria3) &&
                (this.grade.equals(myReview.grade)) &&
                (this.review.equals(myReview.review)) &&
                (this.course.equals(myReview.course));
    }
    
    public int hashCode() 
    {
       int prime = 31;
       int result = 1;
       result = prime * result;
       if(course != null)
       {
      	 	result += course.hashCode();
       }
       return result;
   }

}
