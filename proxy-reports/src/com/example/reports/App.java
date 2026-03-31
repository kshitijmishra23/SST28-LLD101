package com.example.reports;

public class App {

    public static void main(String[] args) {

        User student = new User("Jasleen", "STUDENT");
        User faculty = new User("Prof. Noor", "FACULTY");
        User admin = new User("Kshitij", "ADMIN");

        Report publicReport =
                new ReportProxy("R-101", "Orientation Plan", "PUBLIC");

        Report facultyReport =
                new ReportProxy("R-202", "Midterm Review", "FACULTY");

        Report adminReport =
                new ReportProxy("R-303", "Budget Audit", "ADMIN");

        ReportViewer viewer = new ReportViewer();

        System.out.println("=== CampusVault Demo ===\n");

        viewer.open(publicReport, student);
        System.out.println();

        viewer.open(facultyReport, student);
        System.out.println();

        viewer.open(facultyReport, faculty);
        System.out.println();

        viewer.open(adminReport, admin);
        System.out.println();

        viewer.open(adminReport, admin);
    }
}