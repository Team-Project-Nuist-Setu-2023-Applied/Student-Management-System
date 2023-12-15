import java.util.ArrayList;
import java.util.List;

class StudentManager {
    private List<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully！");
    }

    public Student findStudentByID(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    public void updateStudent(String studentID, String newName, int newAge, String newGender) {
        Student student = findStudentByID(studentID);
        if (student != null) {
            student.setName(newName);
            student.setAge(newAge);
            student.setGender(newGender);
            System.out.println("Student information updated successfully！");
        } else {
            System.out.println("The student ID was not found and the information cannot be updated.。");
        }
    }

    public void deleteStudent(String studentID) {
        Student student = findStudentByID(studentID);
        if (student != null) {
            students.remove(student);
            System.out.println("Student deleted successfully！");
        } else {
            System.out.println("The student ID was not found and cannot be deleted.。");
        }
    }

    public void displayStudents() {
        for (Student student : students) {
            System.out.println("Name：" + student.getName());
            System.out.println("Age：" + student.getAge());
            System.out.println("Gender：" + student.getGender());
            System.out.println("StudentID：" + student.getStudentID());
            System.out.println();
        }
    }
}

