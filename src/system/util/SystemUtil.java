package system.util;

public class SystemUtil {
    public static boolean isValid(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static String[] lineReader(String line) {
        return line.split(",");
    }
}
