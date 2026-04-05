public class DriverAllocator implements DriverService {
    public String allocate(String studentId) {
        // fake deterministic driver
        return "DRV-17";
    }
}
