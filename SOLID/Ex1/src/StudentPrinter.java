import java.util.List;

public class StudentPrinter {
    void printErrors(List<String> errors){
        System.out.println("ERROR: cannot register");
        for (String e : errors)
            System.out.println("- " + e);
    }

    void printSuccess(StudentRecord rec, int totalCount){
        System.out.println("OK: created student " + rec.id);
        System.out.println("Saved. Total students: " + totalCount);
        System.out.println("CONFIRMATION:");
        System.out.println(rec);
    }
}