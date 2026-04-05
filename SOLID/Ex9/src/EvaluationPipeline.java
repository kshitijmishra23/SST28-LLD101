public class EvaluationPipeline {
    private final PlagiarismCheckable checker;
    private final Gradable grader;
    private final ReportWritable writer;
    private final Rubric rubric;

    public EvaluationPipeline(PlagiarismCheckable checker, Gradable grader, ReportWritable writer, Rubric rubric) {
        this.checker = checker;
        this.grader = grader;
        this.writer = writer;
        this.rubric = rubric;
    }

    public void evaluate(Submission sub) {
        int plag = checker.check(sub);
        System.out.println("PlagiarismScore=" + plag);

        int code = grader.grade(sub, rubric);
        System.out.println("CodeScore=" + code);

        String reportName = writer.write(sub, plag, code);
        System.out.println("Report written: " + reportName);

        int total = plag + code;
        String result = (total >= 90) ? "PASS" : "FAIL";
        System.out.println("FINAL: " + result + " (total=" + total + ")");
    }
}
