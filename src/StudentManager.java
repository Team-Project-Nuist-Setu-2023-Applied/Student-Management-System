import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class StudentManager {
    private List<Student> students;
    private final String filePath = "data/students.csv"; // CSV file path

    public StudentManager() {
        students = new ArrayList<>();
        loadStudentsFromFile(); // Load the student data in the constructor / 在构造函数中加载学生数据
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
            System.out.println("The student ID was not found and the information cannot be updated.");
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

    public List<Student> searchStudents(String keyword) {
        List<Student> foundStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(keyword.toLowerCase())
                    || student.getStudentID().contains(keyword)) {
                foundStudents.add(student);
            }
        }
        return foundStudents;
    }


    private void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (data.length >= 4) {
                    students.add(new Student(unformatCsvField(data[0]),
                            Integer.parseInt(data[1]),
                            unformatCsvField(data[2]),
                            unformatCsvField(data[3])));
                }
            }
        } catch (IOException e) {
            System.err.println("Unable to load student data：" + e.getMessage());
        }
    }

    public void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Student student : students) {
                String line = formatCsvField(student.getName()) + "," +
                        student.getAge() + "," +
                        formatCsvField(student.getGender()) + "," +
                        formatCsvField(student.getStudentID());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Unable to save student data：" + e.getMessage());
        }
    }

    private String formatCsvField(String data) {
        return "\"" + data.replace("\"", "\"\"") + "\"";
    }

    private String unformatCsvField(String data) {
        if (data.startsWith("\"") && data.endsWith("\"")) {
            data = data.substring(1, data.length() - 1); // Remove the double quotes / 移除首尾双引号
        }
        return data.replace("\"\"", "\""); // Converts two double quotes to one double quotation mark / 将两个双引号转换为一个双引号
    }
}


