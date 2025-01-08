package org.example.view;

import org.example.dao.EnrollmentDao;
import org.example.dao.MajorDao;
import org.example.dao.StudentDao;
import org.example.dao.SubjectDao;
import org.example.model.*;
import org.example.service.StudentRegistrationService;
import org.example.validation.EnrollmentValidation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StudentView extends BaseView{

    private static StudentRegistrationService studentRegistrationService = new StudentRegistrationService();
    private static StudentDao studentDao = new StudentDao();
    private static MajorDao majorDao = new MajorDao();
    private static SubjectDao subjectDao = new SubjectDao();
    private static EnrollmentDao enrollmentDao = new EnrollmentDao();

    public static Student getRegularStudentInfo(Student stu) throws IOException {
        System.out.print("Enter Status (Active/Inactive)?");
        String status = br.readLine();
        RegularStudent regularStudent = new RegularStudent(stu, status);
        return regularStudent;
    }

    public static Student getScholarStudentInfo(Student stu) throws IOException {
        System.out.print("Enter Scholarship Amount :");
        double scholarshipAmount = Double.parseDouble(br.readLine());
        System.out.print("Enter Study Hour :");
        int studyHour = Integer.parseInt(br.readLine());
        System.out.print("Enter Scholarship Percentage :");
        double scholarPercentage = Double.parseDouble(br.readLine());
        ScholarStudent scholarStudent = new ScholarStudent(stu, scholarshipAmount, studyHour, scholarPercentage);
        return scholarStudent;
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

        System.out.print("Enter Student Type :");
        String type = br.readLine();

        if(type.equalsIgnoreCase("regular")){
            stu = getRegularStudentInfo(stu);
        }else if(type.equalsIgnoreCase("scholar")){
            stu = getScholarStudentInfo(stu);
        }

        return stu;
    }

    public static Major getSelectedMajor() throws IOException {
        System.out.println("<< Majors >>");
        List<Major> mojors = majorDao.getAll();
        for(Major major : mojors){
            System.out.println(" ID :"+major.getId()+" "+major.getName());
        }

        System.out.println("Enter Your Selected Major Id : ");
        int majorId = Integer.parseInt(br.readLine());
        Major major = majorDao.findById(majorId);

        return major;
    }

    public static void createStudent() throws IOException, SQLException {
        Student student = getStudentInfo();
        studentRegistrationService.createStudent(student);
    }

    public static void displayStudnetMenu(){
        System.out.println("====Display Menu===");
        System.out.println("(1)Create New Student");
        System.out.println("(2)Display All Students");
        System.out.println("(3)Delete Student");
        System.out.println("(4)Subject Enrollment Menu");
        System.out.println("(5)Exit");
    }

    public static void displayStudentsInfo(List<Student> studentList){
        for(Student s : studentList){
            System.out.println(s);
        }
    }

    private static void deleteStudent() throws IOException, SQLException {
        System.out.print("Enter Student Id Do you want to delete :");
        int stuId= Integer.parseInt(br.readLine());
        studentDao.delete(stuId);
    }

    public static Student findStudentById() throws IOException {
        System.out.println("Enter Your Student Id?");
        int studnetId = Integer.parseInt(br.readLine());
        Student student = studentDao.findById(studnetId);
        if(student == null){
            System.out.print("Student Not Found For Id : "+studnetId );
            findStudentById();
        }
        return student;
    }

    public static Subject findSubjectByCode() throws IOException{
        SubjectView.displaySubjects();
        System.out.print("Enter Subject Code do you want to enroll?");
        String subjectCode = br.readLine();
        Subject subject = subjectDao.findByCode(subjectCode);
        if(subject==null){
            System.out.println("Subject Not found for code : "+ subjectCode);
            findSubjectByCode();
        }
        return subject;
    }

    private static void subjectEnrollmentMenu() throws IOException {
        Student student = findStudentById();
        Subject subject = findSubjectByCode();
        EnrollmentId enrollmentId = new EnrollmentId(student.getId(), subject.getId());
        Enrollment enrollment = new Enrollment();
        enrollment.setId(enrollmentId);
        enrollment.setStudent(student);
        enrollment.setSubject(subject);

        try {
            EnrollmentValidation.validateCreate(student.getId(), subject.getCode());
            enrollmentDao.create(enrollment);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void call() throws IOException, SQLException {
        while(true){
            displayStudnetMenu();
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
                subjectEnrollmentMenu();
            } else if(menu_id == 5){
                break;
            }
        }
    }
}
