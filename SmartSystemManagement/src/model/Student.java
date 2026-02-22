package model;

public class Student {

    private int id;
    private String name;
    private String department;
    private double marks;
    private double attendance;

    public Student(String name, String department, double marks, double attendance) {
        this.name = name;
        this.department = department;
        this.marks = marks;
        this.attendance = attendance;
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getMarks() { return marks; }
    public double getAttendance() { return attendance; }
}