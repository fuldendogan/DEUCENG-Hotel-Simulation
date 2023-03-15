public class Utils {

    private Utils() {
    }

    public static void logCommand(String logging) {
        System.out.println("\u001B[32m" + logging + "\u001B[0m");
    }

    public static void logWarning(String logging) {
        System.out.println("\u001B[31m" + logging + "\u001B[0m");
    }

    public static void logError(String logging) {
        System.out.println("\u001B[30m" + logging + "\u001B[0m");
    }
}
