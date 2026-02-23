public class LegacyFlags {
    public static final int NONE = 0;
    public static final int WARNING = 1;
    public static final int SUSPENDED = 2;

    public static String nameOf(int f) {
        return switch (f) {
            case WARNING -> "WARNING";
            case SUSPENDED -> "SUSPENDED";
            default -> "NONE";
        };
    }
}