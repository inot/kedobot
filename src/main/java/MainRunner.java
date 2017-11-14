import sx.blah.discord.api.IDiscordClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MainRunner {
    static Properties prop = new Properties();
    static InputStream input = null;



    public static void main(String[] args) throws IOException {
        input = new FileInputStream("config.properties");
        prop.load(input);

        String token = prop.getProperty("token");
        IDiscordClient cli = BotUtils.getBuiltDiscordClient(token);
        cli.getDispatcher().registerListener(new MyEvents());
        cli.login();


    }

}