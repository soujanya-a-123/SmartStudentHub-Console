import dao.StudentDAO;
import model.Student;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        // 🔐 Admin Login
        System.out.println("===== Admin Login =====");

        System.out.print("Enter Username: ");
        String username = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        if (!username.equals("admin") || !password.equals("1234")) {
            System.out.println("❌ Invalid Login! Program Terminated.");
            System.exit(0);
        }

        System.out.println("✅ Login Successful!");

        // 📋 Main Menu Loop
        while (true) {

            System.out.println("\n===== Smart Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. View Topper");
            System.out.println("7. Department Topper");
            System.out.println("8. Average Marks");
            System.out.println("9. Export Data");
            System.out.println("10. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();  // Clear buffer

            switch (choice) {

                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Department: ");
                    String dept = sc.nextLine();

                    System.out.print("Enter Marks: ");
                    double marks = sc.nextDouble();

                    System.out.print("Enter Attendance: ");
                    double attendance = sc.nextDouble();

                    Student student = new Student(name, dept, marks, attendance);
                    dao.addStudent(student);
                    break;

                case 2:
                    dao.viewStudents();
                    break;

                case 3:
                    System.out.print("Enter Student ID to search: ");
                    int searchId = sc.nextInt();
                    dao.searchStudent(searchId);
                    break;

                case 4:
                    System.out.print("Enter Student ID to update: ");
                    int updateId = sc.nextInt();

                    System.out.print("Enter New Marks: ");
                    double newMarks = sc.nextDouble();

                    System.out.print("Enter New Attendance: ");
                    double newAttendance = sc.nextDouble();

                    dao.updateStudent(updateId, newMarks, newAttendance);
                    break;

                case 5:
                    System.out.print("Enter Student ID to delete: ");
                    int deleteId = sc.nextInt();
                    dao.deleteStudent(deleteId);
                    break;

                case 6:
                    dao.viewTopper();
                    break;

                case 7:
                    System.out.print("Enter Department: ");
                    String deptName = sc.nextLine();
                    dao.departmentTopper(deptName);
                    break;

                case 8:
                    dao.averageMarks();
                    break;

                case 9:
                    dao.exportToFile();
                    break;

                case 10:
                    System.out.println("👋 Exiting Program...");
                    System.exit(0);

                default:
                    System.out.println("❌ Invalid Choice! Try Again.");
            }
        }
    }
}