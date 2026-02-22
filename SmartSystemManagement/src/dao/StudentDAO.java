package dao;

import model.Student;
import util.DBConnection;

import java.sql.*;
import java.io.FileWriter;

public class StudentDAO {

    // 1️⃣ Add Student
    public void addStudent(Student student) {

        String query = "INSERT INTO students (name, department, marks, attendance) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getDepartment());
            ps.setDouble(3, student.getMarks());
            ps.setDouble(4, student.getAttendance());

            ps.executeUpdate();
            System.out.println("✅ Student Added Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 2️⃣ View Students
    public void viewStudents() {

        String query = "SELECT * FROM students";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                double marks = rs.getDouble("marks");
                double attendance = rs.getDouble("attendance");

                String grade;
                if (marks >= 90) grade = "A";
                else if (marks >= 75) grade = "B";
                else if (marks >= 60) grade = "C";
                else grade = "Fail";

                String attendanceStatus = (attendance >= 75) ? "Good" : "Low";

                System.out.println("--------------------------------");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Department: " + rs.getString("department"));
                System.out.println("Marks: " + marks);
                System.out.println("Grade: " + grade);
                System.out.println("Attendance: " + attendance + " (" + attendanceStatus + ")");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3️⃣ Search Student
    public void searchStudent(int id) {

        String query = "SELECT * FROM students WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Student Found:");
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Marks: " + rs.getDouble("marks"));
            } else {
                System.out.println("❌ Student not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 4️⃣ Update Student
    public void updateStudent(int id, double marks, double attendance) {

        String query = "UPDATE students SET marks=?, attendance=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setDouble(1, marks);
            ps.setDouble(2, attendance);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("✅ Updated Successfully!");
            else
                System.out.println("❌ Student not found!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 5️⃣ Delete Student
    public void deleteStudent(int id) {

        String query = "DELETE FROM students WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("✅ Deleted Successfully!");
            else
                System.out.println("❌ Student not found!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 6️⃣ View Overall Topper
    public void viewTopper() {

        String query = "SELECT * FROM students ORDER BY marks DESC LIMIT 1";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                System.out.println("🏆 Topper: " + rs.getString("name"));
                System.out.println("Marks: " + rs.getDouble("marks"));
            } else {
                System.out.println("No data found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 7️⃣ Department Topper
    public void departmentTopper(String department) {

        String query = "SELECT * FROM students WHERE department=? ORDER BY marks DESC LIMIT 1";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, department);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("🏆 " + department + " Topper: " + rs.getString("name"));
                System.out.println("Marks: " + rs.getDouble("marks"));
            } else {
                System.out.println("No students found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 8️⃣ Average Marks
    public void averageMarks() {

        String query = "SELECT AVG(marks) AS avg_marks FROM students";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                System.out.println("📊 Average Marks: " + rs.getDouble("avg_marks"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 9️⃣ Export Data
    public void exportToFile() {

        String query = "SELECT * FROM students";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery();
             FileWriter writer = new FileWriter("students.txt")) {

            while (rs.next()) {
                writer.write(
                        rs.getInt("id") + ", " +
                        rs.getString("name") + ", " +
                        rs.getString("department") + ", " +
                        rs.getDouble("marks") + "\n"
                );
            }

            System.out.println("✅ Data Exported to students.txt");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}