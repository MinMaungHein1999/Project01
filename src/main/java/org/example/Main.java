package org.example;

import org.example.dao.StudentDao;
import org.example.model.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StudentDao studentDao = new StudentDao();

    public static void displayStudentsInfo(List<Student> studentList){
        for(Student s : studentList){
            System.out.println(s);
        }
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

        Student stu = new Student();
        stu.setName(name);
        stu.setEmail(email);
        stu.setGender(gender);
        stu.setPhone(phone);
        stu.setAddress(address);
        return stu;
    }

    public static void displayMenu(){
        System.out.println("====Display Menu===");
        System.out.println("(1)Create New Student");
        System.out.println("(2)Display All Students");
        System.out.println("(3)Delete Student");
        System.out.println("(4)Exit");
    }

    public static void createStudent() throws IOException, SQLException {
        Student student = getStudentInfo();
        studentDao.create(student);
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

            } else if(menu_id == 4){
                break;
            }
        }

    }

}
