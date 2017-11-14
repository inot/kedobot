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

//        if(args.length != 1){
//            System.out.println("Please enter the bots token as the first argument e.g java -jar thisjar.jar tokenhere");
//            return;
//        }
        String token = prop.getProperty("token");
        IDiscordClient cli = BotUtils.getBuiltDiscordClient(token);

        /*
        // Commented out as you don't really want duplicate listeners unless you're intentionally writing your code 
        // like that.
        // Register a listener via the IListener interface
        cli.getDispatcher().registerListener(new IListener<MessageReceivedEvent>() {
            public void handle(MessageReceivedEvent event) {
                if(event.getMessage().getContent().startsWith(BotUtils.BOT_PREFIX + "test"))
                    BotUtils.sendMessage(event.getChannel(), "I am sending a message from an IListener listener");
            }
        });
        */

        // Register a listener via the EventSubscriber annotation which allows for organisation and delegation of events
        cli.getDispatcher().registerListener(new MyEvents());

        // Only login after all events are registered otherwise some may be missed.
        cli.login();


    }

}