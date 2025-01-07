package org.example.view;

import org.example.dao.MajorDao;
import org.example.model.Major;

import java.io.IOException;
import java.util.List;

public class MajorView extends BaseView{
    private static MajorDao majorDao = new MajorDao();

    public static void call() throws IOException {
        majorMenu();
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
