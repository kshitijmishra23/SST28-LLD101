package com.example.reports;

public class ReportViewer {

    public void open(Report report, User user) {
        report.display(user);
    }
}
