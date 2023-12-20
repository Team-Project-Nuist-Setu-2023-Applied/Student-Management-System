import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;
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
            System.out.println("6. Search students");
            System.out.println("0. quit");
            System.out.println("Please enter your choice: ");

            int choice = -1;
            boolean isValidInput = false;

            while (!isValidInput) {
                try {
                    choice = scanner.nextInt();
                    if (choice >= 0 && choice <= 6) {
                        isValidInput = true;
                    } else {
                        System.out.println("Invalid operation! Please enter BETWEEN 0 AND 6."); // Emphasizes that the user must input a number
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a NUMBER between 0 and 6."); // Prompts the user to enter a number within the 0-5 range
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
                    /*System.out.print("Please enter student name：");
                    String name = scanner.nextLine();
                    System.out.print("Please enter student age：");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Please enter student gender：");
                    String gender = scanner.nextLine();
                    System.out.print("Please enter student ID：");
                    String studentID = scanner.nextLine();*/
                    String name = inputName(scanner);
                    int age = inputAge(scanner);
                    String gender = inputGender(scanner);
                    String studentID = inputStudentID(scanner);


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
                        name = inputName(scanner);
                        age = inputAge(scanner);
                        gender = inputGender(scanner);

                        manager.updateStudent(studentID, name, age, gender);
                    } else {
                        System.out.println("The student ID was not found and the information cannot be updated.");
                    }
                    break;
                case 4:
                    System.out.print("Please enter the student number to be deleted：");
                    studentID = scanner.nextLine();
                    manager.deleteStudent(studentID);
                    break;
                case 5:
                    manager.displayStudents();
                    break;
                case 6:
                    System.out.print("Please enter a keyword for searching students：");
                    String keyword = scanner.nextLine();
                    List<Student> foundStudents = manager.searchStudents(keyword);
                    if (foundStudents.isEmpty()) {
                        System.out.println("No students found with the given keyword.");
                    } else {
                        for (Student searchResult : foundStudents) { // Rename variable to avoid conflict
                            System.out.println("Name：" + searchResult.getName());
                            System.out.println("Age：" + searchResult.getAge());
                            System.out.println("Gender：" + searchResult.getGender());
                            System.out.println("StudentID：" + searchResult.getStudentID());
                            System.out.println();
                        }
                    }
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

    private static String inputName(Scanner scanner) {
        while (true) {
            System.out.print("Please enter student name：");
            String name = scanner.nextLine();
            if (!name.isEmpty() && name.length() <= 20) {
                return name;
            }
            System.out.println("Invalid name. Please try again.");
        }
    }

    private static int inputAge(Scanner scanner) {
        while (true) {
            System.out.print("Please enter student age：");
            if (scanner.hasNextInt()) {
                int age = scanner.nextInt();
                scanner.nextLine(); // Clear newline characters / 清除换行符
                if (age > 5 && age <= 100) {
                    return age;
                }
            } else {
                scanner.nextLine(); // Clear invalid input / 清除无效输入
            }
            System.out.println("Invalid age. Please enter a number between 6 and 100.");
        }
    }

    private static String inputGender(Scanner scanner) {
        while (true) {
            System.out.print("Please enter student gender (Male/Female)：");
            String gender = scanner.nextLine();
            if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female")) {
                return gender;
            }
            System.out.println("Invalid gender. Please enter 'Male' or 'Female'.");
        }
    }

    private static String inputStudentID(Scanner scanner) {
        while (true) {
            System.out.print("Please enter student ID：");
            String studentID = scanner.nextLine();
            if (!studentID.isEmpty()) {
                return studentID;
            }
            System.out.println("It's empty. Please enter student ID again.");
        }
    }

}