package Mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Учебная работа по JavaMailApi
 * @author Artem Andreev
 */
public class Email {
    private String mail;
    private String password;
    private String username;

    public Email(){
        this.mail = "";
        this.password = "";
    }

    public Email(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Отправка письма
     * @param to электронный адрес получателя.
     */
    public void sendMessage(String to) {
        final String from = this.mail;
        final String password = this.password;
        String host = "smtp.mail.ru";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator(){
                    protected PasswordAuthentication getPassAuth(){
                        return new PasswordAuthentication(from, password);
                    }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Test message");
            message.setText("This message sended by JavaMailApi");
            Transport.send(message);
            System.out.println("Success");
        }catch (MessagingException e){
            throw new RuntimeException(e);
        }

    }
}
