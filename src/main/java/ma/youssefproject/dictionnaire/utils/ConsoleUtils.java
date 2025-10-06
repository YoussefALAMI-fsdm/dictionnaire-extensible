package ma.youssefproject.dictionnaire.utils ;

import java.io.IOException;

public class ConsoleUtils {

    /**
     * Vide l'écran du terminal si possible.
     * - Windows → cls
     * - Linux/macOS → clear
     * - IDE ou si la commande échoue → rien
     */
    public static void clearScreen() {
        String os = System.getProperty("os.name").toLowerCase();

        try {
            if (os.contains("win")) {
                // Windows
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                pb.inheritIO().start().waitFor();
            } else if (os.contains("nix") || os.contains("nux") || os.contains("aix") || os.contains("mac")) {
                // Linux / Unix / macOS
                ProcessBuilder pb = new ProcessBuilder("clear");
                pb.inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            // Si la commande échoue (IDE ou terminal non standard), ne rien faire
        }
    }

    // Test rapide
    public static void main(String[] args) {
        System.out.println("Avant clear...");
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        clearScreen();
        System.out.println("Après clear !");
    }
}
