import java.sql.*;

public class DatabaseWriter {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3006/?user=root"; // JDBC стринг за връзка с базата
        String username = "root";
        String password = "1234";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Свързване към базата данни");

            // Въвеждане на данните за регистрация
//            String name = "John Doe";
//            String email = "john.doe@example.com";
//            String pass = "mypassword";

            // Изпълнение на SQL заявка за вмъкване на данните в таблицата
            String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Данните са записани в базата данни.");
            } else {
                System.out.println("Грешка при записването на данните.");
            }
        } catch (SQLException e) {
            System.out.println("Грешка при свързването към базата данни: " + e.getMessage());
        }
    }
}
