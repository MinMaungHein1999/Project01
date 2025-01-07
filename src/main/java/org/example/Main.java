package org.example;

import org.example.view.HomeView;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        HomeView.call();
    }
}
