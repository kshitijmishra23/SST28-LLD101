/**
 * Abstraction for saving StudentRecords.
 * Decouples the onboarding workflow from any specific storage mechanism.
 */
public interface StudentRepository {
    void save(StudentRecord record);
    int count();
}
