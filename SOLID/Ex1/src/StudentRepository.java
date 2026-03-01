import java.util.List;

public interface StudentRepository {
    void save(StudentRecord r);
    int count();
    List<StudentRecord> all();
}
