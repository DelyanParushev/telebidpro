import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        // Четене на съобщение от сървъра
        String message = reader.readLine();
        System.out.println(message);

        // Въвеждане на данните за регистрация
        System.out.print("Име: ");
        String name = consoleReader.readLine();
        System.out.print("Имейл: ");
        String email = consoleReader.readLine();
        System.out.print("Парола: ");
        String password = consoleReader.readLine();

        // С writer изпращам данните към сървъра
        writer.println(name);
        writer.println(email);
        writer.println(password);

        // Четене на отговора от сървъра
        String response = reader.readLine();
        System.out.println(response);

        socket.close();
    }
}

