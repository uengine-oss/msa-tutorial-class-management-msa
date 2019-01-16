package hello;

import java.util.Date;

/**
 * Created by uengine on 2018. 11. 3..
 */
public class ClazzDayRegistered {

    public ClazzDayRegistered(){} //for serialization

    public ClazzDayRegistered(ClazzDay clazzDay) {
        if(clazzDay.getInstructor()!=null)
            setInstructorId(clazzDay.getInstructor().getId().toString());

        setDate(clazzDay.getDate());

        if(clazzDay.getClazz()!=null)
            setTitle(clazzDay.getClazz().getTitle());
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    Date date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String title;


    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    String instructorId;

}
