public class EvaluationPipeline {

    private final PlagiarismService plagiarismService;
    private final GradingService gradingService;
    private final ReportService reportService;
    private final Rubric rubric;

    public EvaluationPipeline(
            PlagiarismService plagiarismService,
            GradingService gradingService,
            ReportService reportService,
            Rubric rubric
    ) {
        this.plagiarismService = plagiarismService;
        this.gradingService = gradingService;
        this.reportService = reportService;
        this.rubric = rubric;
    }

    public void evaluate(Submission sub) {

        int plag = plagiarismService.check(sub);
        System.out.println("PlagiarismScore=" + plag);

        int code = gradingService.grade(sub, rubric);
        System.out.println("CodeScore=" + code);

        String reportName = reportService.write(sub, plag, code);
        System.out.println("Report written: " + reportName);

        int total = plag + code;
        String result = (total >= 90) ? "PASS" : "FAIL";
        System.out.println("FINAL: " + result + " (total=" + total + ")");
    }
}