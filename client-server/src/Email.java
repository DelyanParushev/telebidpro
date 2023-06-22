import com.mysql.cj.Session;

import javax.mail.*;
import javax.mail.internet.*;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

public class EmailSender {
    public static void main(String[] args) {
        String toEmail = "recipient@example.com";
        String fromEmail = "john.doe@example.com";
        String password = "password";

        // Настройки на пропъртита за изпращане на имейл
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.example.com");
        props.put("mail.smtp.port", "8080");

        // Създаване на автентикатор
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        // Сесия за изпращане на имейл
        Session session = Session.getInstance(props, auth);

        try {
            //MimeMessage
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Имейл за верификация");

            // Съдържание на имейла
            String verificationLink = "http://telebidpro.com/verify";
            String emailContent = "Здравейте,\nПотвърдете имейла, като посетите линка: " + verificationLink;
            message.setText(emailContent);

            // Изпращане на имейла
            Transport.send(message);

            System.out.println("Верификацията е изпратена успешно до " + toEmail);
        } catch (MessagingException e) {
            System.out.println("Грешка при изпращането: " + e.getMessage());
        }
    }
}
