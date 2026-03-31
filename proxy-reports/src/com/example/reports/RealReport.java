package com.example.reports;

/**
 * Real Subject
 * Responsible for the expensive report loading.
 */
public class RealReport implements Report {

    private final String reportId;
    private final String title;
    private final String classification;

    private String content;

    public RealReport(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading report from disk: " + reportId + " (" + title + ")");
        content = "Confidential content of report: " + title;
    }

    @Override
    public void display(User user) {
        System.out.println("Displaying report '" + title + "' to " + user.getName());
        System.out.println(content);
    }

    public String getClassification() {
        return classification;
    }
}