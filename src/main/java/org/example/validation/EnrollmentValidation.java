package org.example.validation;

import org.example.dao.StudentDao;
import org.example.model.Student;
import org.example.model.Subject;

import java.util.List;
import java.util.Set;

public class EnrollmentValidation {
    private static StudentDao studentDao;
    static{
        studentDao = new StudentDao();
    }
    public static void validateCreate(int studentId, String subjectCode) throws Exception {
        List<Subject> subjects = studentDao.fetchSubjectsById(studentId);
        Boolean isContains = subjects.stream().anyMatch(subject -> subject.getCode().equals(subjectCode));
        if(isContains){
            throw new Exception("This Subject ("+subjectCode+") already enrolled !!!!");
        }
    }

}
