package com.example.reports;

/**
 * Proxy that controls access and lazy loading of RealReport.
 */
public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;

    private RealReport realReport; // cached instance

    private final AccessControl accessControl = new AccessControl();

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {

        System.out.println("Checking access for " + user.getName());

        // 1️⃣ Access Control
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("ACCESS DENIED for " + user.getRole() +
                    " to report '" + title + "'");
            return;
        }

        // 2️⃣ Lazy Loading
        if (realReport == null) {
            System.out.println("Lazy loading RealReport...");
            realReport = new RealReport(reportId, title, classification);
        } else {
            System.out.println("Using cached report...");
        }

        // 3️⃣ Delegate to RealReport
        realReport.display(user);
    }
}