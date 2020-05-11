import Mail.Email;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/email.properties"));
            Email email = new Email(properties.getProperty("emailFrom"),properties.getProperty("password"));
            email.sendMessage(properties.getProperty("emailTo"));
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
