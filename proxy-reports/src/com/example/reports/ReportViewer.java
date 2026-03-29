package com.example.reports;

/**
 * Refactored Viewer
 * Depends on abstraction (Report) instead of ReportFile.
 */
public class ReportViewer {

    public void open(Report report, User user) {
        report.display(user);
    }
}