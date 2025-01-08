package org.example.service;

import org.example.dao.StudentDao;
import org.example.model.Student;

public class StudentRegistrationService {
    private StudentDao studentDao;

    public StudentRegistrationService(){
        this.studentDao = new StudentDao();
    }

    public void createStudent(Student student){
        this.studentDao.create(student);
    }
}
