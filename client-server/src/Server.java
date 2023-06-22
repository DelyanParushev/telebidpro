import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Старт на сървъра с порт 8080");

        while (true) {
            Socket clientSocket = serverSocket.accept(); // чака клиентска връзка
            System.out.println("Клиент: " + clientSocket);
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            clientHandler.start(); //позволява повече от един клиент в сървъра
        }
    }
}

class ClientHandler extends Thread {
    //отговаря за рекуестите на клиента
    private final Socket clientSocket;
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            writer.println("Моля, въведете данните за регистрация:");

            String name = reader.readLine();
            String email = reader.readLine();
            String password = reader.readLine();

            // Валидация на данните с булева, която връща съответно подадените правилно данни
            boolean isValid = validateData(name, email, password);

            if (isValid) {
                writer.println("Регистрацията е успешна!");
            } else {
                writer.println("Грешка. Моля, въведете валидни данни.");
            }
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validateData(String name, String email, String password) {
        //чрез Regex проверявам дали имейла, паролата и имената са правилно изписани
        // Валидация на имейл адреса
        if (!email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")) {
            return false;
        }

        // Валидация на имената (само букви и интервали)
        if (!name.matches("[A-Za-z\\s]+")) {
            return false;
        }

        // Валидация на паролата (> 6 символа)
        if (password.length() < 6) {
            return false;
        }

        return true;
    }
}
