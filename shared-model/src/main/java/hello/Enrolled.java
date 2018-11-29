package hello;

/**
 * Created by uengine on 2018. 11. 3..
 */
public class Enrolled {

    public Enrolled(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    Enrollment enrollment;

        public Enrollment getEnrollment() {
            return enrollment;
        }

        public void setEnrollment(Enrollment enrollment) {
            this.enrollment = enrollment;
        }
}
