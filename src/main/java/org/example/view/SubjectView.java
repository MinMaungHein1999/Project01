package org.example.view;

import org.example.dao.SubjectDao;
import org.example.model.Subject;

import java.io.IOException;
import java.util.List;

public class SubjectView extends BaseView{
    private static SubjectDao subjectDao = new SubjectDao();

    public static void call() throws IOException {
        subjectMenu();
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
        System.out.print("Enter Subject Code do you want to delete?");
        String subjectCode = br.readLine();
        subjectDao.deleteByCode(subjectCode);
    }

    public static void displaySubjects() {
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

}
