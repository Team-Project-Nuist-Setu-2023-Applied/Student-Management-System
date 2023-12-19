import java.util.Scanner;
import java.util.InputMismatchException;

public class MainMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        while (true) {
            System.out.println("Please select an action：");
            System.out.println("1. Add students");
            System.out.println("2. Find students");
            System.out.println("3. Update student information");
            System.out.println("4. Delete students");
            System.out.println("5. Show all student information");
            System.out.println("0. quit");

            int choice = -1;
            boolean isValidInput = false;

            while (!isValidInput) {
                try {
                    choice = scanner.nextInt();
                    if (choice >= 0 && choice <= 5) {
                        isValidInput = true;
                    } else {
                        System.out.println("Invalid operation! Please enter BETWEEN 0 AND 5."); // Emphasizes that the user must input a number
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a NUMBER between 0 and 5."); // Prompts the user to enter a number within the 0-5 range
                } finally {
                    scanner.nextLine(); // Clear the contents of the buffer / 清除缓冲区的内容
                }
            }
            /*int choice = scanner.nextInt();
            scanner.nextLine();*/

            //Select action
            switch (choice) {
                //Add student information
                case 1:
                    System.out.print("Please enter student name：");
                    String name = scanner.nextLine();
                    System.out.print("Please enter student age：");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Please enter student gender：");
                    String gender = scanner.nextLine();
                    System.out.print("Please enter student ID：");
                    String studentID = scanner.nextLine();

                    Student student = new Student(name, age, gender, studentID);
                    manager.addStudent(student);
                    break;
//Find student information
                case 2:
                    System.out.print("Please enter the student ID you are looking for：");
                    studentID = scanner.nextLine();
                    Student foundStudent = manager.findStudentByID(studentID);
                    if (foundStudent != null) {
                        System.out.println("Name：" + foundStudent.getName());
                        System.out.println("Age：" + foundStudent.getAge());
                        System.out.println("Gender：" + foundStudent.getGender());
                        System.out.println("Student ID：" + foundStudent.getStudentID());
                    } else {
                        System.out.println("The student ID was not found。");
                    }
                    break;
//Update student information
                case 3:
                    System.out.print("Please enter the student ID whose information you want to update：");
                    studentID = scanner.nextLine();
                    foundStudent = manager.findStudentByID(studentID);
                    if (foundStudent != null) {
                        System.out.print("Please enter new student name：");
                        name = scanner.nextLine();
                        System.out.print("Please enter new student age：");
                        age = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Please enter new student gender：");
                        gender = scanner.nextLine();

                        manager.updateStudent(studentID, name, age, gender);
                    } else {
                        System.out.println("The student ID was not found and the information cannot be updated.。");
                    }
                    break;
//Delete student information
                case 4:
                    System.out.print("Please enter the student number to be deleted：");
                    studentID = scanner.nextLine();
                    manager.deleteStudent(studentID);
                    break;

                case 5:
                    manager.displayStudents();
                    break;

                case 0:
                    System.out.println("Program has exited。");
                    System.exit(0);
                    break;

                /*default:
                    System.out.println("Invalid operation! Please enter a number between 0 and 5.");
                    break;*/
            }
        }
    }
}