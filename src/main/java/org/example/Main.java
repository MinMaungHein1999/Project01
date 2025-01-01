package org.example;

import org.example.dao.MajorDao;
import org.example.dao.StudentDao;
import org.example.dao.SubjectDao;
import org.example.model.Major;
import org.example.model.Student;
import org.example.model.Subject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StudentDao studentDao = new StudentDao();
    private static MajorDao majorDao = new MajorDao();
    private static SubjectDao subjectDao = new SubjectDao();

    public static void displayStudentsInfo(List<Student> studentList){
        for(Student s : studentList){
            System.out.println(s);
        }
    }

    public static Major getSelectedMajor() throws IOException {
        System.out.println("<< Majors >>");
        List<Major> mojors = majorDao.getAll();
        for(Major major : mojors){
            System.out.println(" ID :"+major.getId()+" "+major.getName());
        }

        System.out.print("Enter Your Selected Major Id : ");
        int majorId = Integer.parseInt(br.readLine());
        Major major = majorDao.findById(majorId);

        return major;
    }

    public static Student getStudentInfo() throws IOException {
        System.out.print("Enter Your Name :");
        String name = br.readLine();
        System.out.print("Enter Your Email :");
        String email = br.readLine();
        System.out.print("Enter Gender :");
        String gender = br.readLine();
        System.out.print("Enter Phone :");
        String phone = br.readLine();
        System.out.print("Enter Address :");
        String address = br.readLine();

        Major major = getSelectedMajor();

        Student stu = new Student();
        stu.setName(name);
        stu.setEmail(email);
        stu.setGender(gender);
        stu.setPhone(phone);
        stu.setAddress(address);
        stu.setMajor(major);
        return stu;
    }

    public static void displayMenu(){
        System.out.println("====Display Menu===");
        System.out.println("(1)Create New Student");
        System.out.println("(2)Display All Students");
        System.out.println("(3)Delete Student");
        System.out.println("(4)Major Menu");
        System.out.println("(5)Subject Menu");
        System.out.println("(6)Subject Enrollment Menu");
        System.out.println("(7)Exit");
    }

    public static void createStudent() throws IOException, SQLException {
        Student student = getStudentInfo();
        studentDao.create(student);
    }

    public static void majorMenu() throws IOException {
        while(true) {
            displayMajorMenu();
            int major_menu_id = Integer.parseInt(br.readLine());

            if (major_menu_id == 1) {
                createMajor();
            } else if (major_menu_id == 2) {
                deleteMajor();
            } else if (major_menu_id == 3) {
                updateMajor();
            } else if (major_menu_id == 4) {
                getALLMajor();
            } else if (major_menu_id == 5) {
                break;
            }
        }
    }

    public static void main(String[] args) throws SQLException, IOException {

        System.out.println("*********Student Management System**************");
        while(true){
            displayMenu();
            System.out.print("Enter Menu Number : ");
            int menu_id = Integer.parseInt(br.readLine());
            if(menu_id == 1){
                createStudent();
            } else if (menu_id == 2) {
                List<Student> students = studentDao.getAll();
                displayStudentsInfo(students);
            } else if (menu_id == 3) {
                deleteStudent();
            } else if (menu_id == 4){
                majorMenu();
            }else if (menu_id == 5){
                subjectMenu();
            } else if (menu_id == 6){
                subjectEnrollmentMenu();
            } else if(menu_id == 7){
                break;
            }
        }

    }

    private static void subjectMenu() throws IOException {
        while(true) {
            displaySubjectMenu();
            int subjectMenuId = Integer.parseInt(br.readLine());

            if (subjectMenuId == 1) {
                createSubject();
            } else if (subjectMenuId == 2) {
                deleteSubject();
            } else if (subjectMenuId == 3) {
                updateSubject();
            } else if (subjectMenuId == 4) {
                getALLSubject();
            } else if (subjectMenuId == 5) {
                break;
            }
        }
    }

    private static void createSubject() throws IOException {
        System.out.print("Enter Subject Title : ");
        String title = br.readLine();
        System.out.print("Enter Subject Code : ");
        String code = br.readLine();

        Subject subject = new Subject();
        subject.setCode(code);
        subject.setTitle(title);

        subjectDao.create(subject);
    }

    private static void getALLSubject() {
        displaySubjects();
    }

    private static void updateSubject() {
    }

    private static void deleteSubject() throws IOException {
        System.out.print("Enter Subject Id do you want to delete?");
        int subjectId = Integer.parseInt(br.readLine());
        subjectDao.delete(subjectId);
    }

    private static void subjectEnrollmentMenu() throws IOException {
        System.out.print("Enter Your Student Id?");
        int studnetId = Integer.parseInt(br.readLine());
        displaySubjects();
        System.out.print("Enter Subject Id do you want to enroll?");
        int subjectId = Integer.parseInt(br.readLine());

        Student student = studentDao.findById(studnetId);
        Subject subject = subjectDao.findById(subjectId);
        student.getSubjects().add(subject);
        studentDao.update(student);

    }

    private static void displaySubjects() {
        List<Subject> subjects = subjectDao.getAll();
        for(Subject subject : subjects){
            System.out.print(subject);
        }
    }

    private static void displaySubjectMenu() {
        System.out.println("(1) Create New Subject");
        System.out.println("(2) Delete Subject");
        System.out.println("(3) Update Subject");
        System.out.println("(4) Fetch All Subjects");
        System.out.println("(5) Exit");
    }

    private static void deleteStudent() throws IOException, SQLException {
        System.out.print("Enter Student Id Do you want to delete :");
        int stuId= Integer.parseInt(br.readLine());
        studentDao.delete(stuId);
    }

    private static void getALLMajor() {
        List<Major> majors = majorDao.getAll();
        displayMajors(majors);
    }

    private static void displayMajors(List<Major> majors) {
        for(Major major : majors){
            System.out.println(major);
        }
    }

    private static void updateMajor() {

    }

    private static void deleteMajor() throws IOException {

        System.out.print("Enter Major Id Do you want to delete :");
        int majorId = Integer.parseInt(br.readLine());
        majorDao.delete(majorId);

    }

    private static void createMajor() throws IOException {

        System.out.print("Enter New Major :");
        String majorName =br.readLine();
        Major major = new Major();
        major.setName(majorName);
        majorDao.create(major);
    }

    private static void displayMajorMenu() {
        System.out.println("(1) Create New Major");
        System.out.println("(2) Delete Major");
        System.out.println("(3) Update Major");
        System.out.println("(4) Fetch All Majors");
        System.out.println("(5) Exit");
    }

}
