package org.example.view;

import java.io.IOException;
import java.sql.SQLException;

public class HomeView extends BaseView{
    public static void displayStudnetMenu(){
        System.out.println("====Display Menu===");
        System.out.println("(1)Student Menu");
        System.out.println("(2)Major Menu");
        System.out.println("(3)Subject Menu");
        System.out.println("(4)Exit");
    }

    public static void call() throws SQLException, IOException {
        while(true){
            displayStudnetMenu();
            System.out.print("Enter Menu Number : ");
            int menu_id = Integer.parseInt(br.readLine());
            if(menu_id == 1){
                StudentView.call();
            } else if (menu_id == 2) {
                MajorView.call();
            } else if (menu_id == 3) {
                SubjectView.call();
            } else if (menu_id == 4){
                break;
            }
        }
    }
}
