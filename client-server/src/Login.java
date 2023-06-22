import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Login {
    private static Map<String, String> users = new HashMap<>(); // Съхранява потребителските имена и пароли

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean isLoggedIn = false;
        String username = "";

        while (true) {
            if (isLoggedIn) {
                System.out.println("Добре дошли, " + username + "!");
                System.out.println("Изход");
                int option = scanner.nextInt();

                scanner.nextLine();
            }
        }
    }
}
