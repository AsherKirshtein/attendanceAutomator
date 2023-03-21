package com.automation.Server;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class course
{
    private String courseName;
    private String courseID;
    private HashMap<String, Student> students;
    private HashMap<String, attendanceStatus> attendance;

    private short startHour;
    private short startMinute;
    private short endHour;
    private short endMinute;

    public course(String courseName, String courseID)
    {
        this.courseName = courseName;
        this.courseID = courseID;
        this.students = new HashMap<String, Student>();
        this.attendance = new HashMap<String, attendanceStatus>();
    }

    public void addStudent(Student student)
    {
        this.students.put(student.toString(), student);
    }

    public void removeStudent(Student student)
    {
        this.students.remove(student.toString());
    }

    public boolean containsStudent(String student)
    {
        return this.students.containsKey(student);
    }

    public String getCourseName()
    {
        return courseName;
    }

    @Override
    public String toString()
    {
        return courseName;
    }

    public void setAttendance(String student, attendanceStatus status)
    {
        this.attendance.put(student, status);
    }

    public void setAttendance(String student)
    {
        Calendar now = Calendar.getInstance();
        short hour = (short) now.get(Calendar.HOUR_OF_DAY);
        short minute = (short) now.get(Calendar.MINUTE);
        if(hour < startHour && startHour == hour + 1) // early but not by more than an hour
        {
            setAttendance(student, attendanceStatus.PRESENT);
        }
        if(hour == startHour && minute <= startMinute + 15)
        {
            setAttendance(student, attendanceStatus.PRESENT);
        }
        if(hour == startHour && minute > startMinute + 15)
        {
            setAttendance(student, attendanceStatus.TARDY);
        }
        if(hour > startHour)
        {
            setAttendance(student, attendanceStatus.ABSENT);
        }
        else
        {
            setAttendance(student, attendanceStatus.UNKNOWN);
        }
    }

    public attendanceStatus getAttendance(String student)
    {
        if(!this.attendance.containsKey(student))
        {
            return attendanceStatus.UNKNOWN;
        }
        return this.attendance.get(student);
    }

    public List<Student> getStudents()
    {
        return new ArrayList<Student>(this.students.values());
    }

    public void setStartTime(short hour, short minute)
    {
        if(hour > 23 || hour < 0 || minute > 59 || minute < 0)
        {
            System.out.println("Invalid time");
            return;
        }
        this.startHour = hour;
        this.startMinute = minute;
    }
    public String getStartTime()
    {
        return this.startHour + ":" + this.startMinute;
    }

    public void setEndTime(short hour, short minute)
    {
        if(hour > 23 || hour < 0 || minute > 59 || minute < 0)
        {
            System.out.println("Invalid time");
            return;
        }
        this.endHour = hour;
        this.endMinute = minute;
    }
    public String getEndTime()
    {
        return this.endHour + ":" + this.endMinute;
    }
}
