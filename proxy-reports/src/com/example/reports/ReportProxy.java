package com.example.reports;

// proxy that sits between client and real report
// handles access control + lazy loading + caching
public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();

    private RealReport cachedReport; // null until first authorized access

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        // step 1: check if user even has permission
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("[ACCESS DENIED] " + user.getName()
                    + " (" + user.getRole() + ") cannot view " + classification + " report: " + title);
            return;
        }

        // step 2: lazy load - only hit disk on first access
        if (cachedReport == null) {
            cachedReport = new RealReport(reportId, title, classification);
        }

        // step 3: delegate to real report (cached after first load)
        cachedReport.display(user);
    }
}
