import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionAddEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionRemoveEvent;
import sx.blah.discord.handle.impl.obj.Role;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RateLimitException;
import sx.blah.discord.util.RequestBuffer;

import java.util.List;

class BotUtils {

    // Constants for use throughout the bot
    static String BOT_PREFIX = "/";

    // Handles the creation and getting of a IDiscordClient object for a token
    static IDiscordClient getBuiltDiscordClient(String token) {

        // The ClientBuilder object is where you will attach your params for configuring the instance of your bot.
        // Such as withToken, setDaemon etc
        return new ClientBuilder()
                .withToken(token)
                .build();

    }

    // Helper functions to make certain aspects of the bot easier to use.
    static void sendMessage(IChannel channel, String message) {

        // This might look weird but it'll be explained in another page.
        RequestBuffer.request(() -> {
            try {
                channel.sendMessage(message);
            } catch (DiscordException e) {
                System.err.println("Message could not be sent with error: ");
                e.printStackTrace();
            }
        });

        /*
        // The below example is written to demonstrate sending a message if you want to catch the RLE for logging purposes
        RequestBuffer.request(() -> {
            try{
                channel.sendMessage(message);
            } catch (RateLimitException e){
                System.out.println("Do some logging");
                throw e;
            }
        });
        */

    }

    public static void checkAndAddRole(ReactionAddEvent event, String emodji, String emodjiForCheck, String roleForChange) {
        if (emodji.contains(emodjiForCheck)) {
            List<IRole> role = event.getGuild().getRolesByName(roleForChange);
            event.getUser().addRole(role.get(0));
            String user = event.getUser().getName();
            String discriminator = event.getUser().getDiscriminator();
            System.out.println("For User: " + user + "#" +discriminator+ ", Role: \"" + roleForChange + "\" Added!");
        }
    }

    public static void checkAndRemoveRole(ReactionRemoveEvent event, String emodji, String emodjiForCheck, String roleForChange) {
        if (emodji.contains(emodjiForCheck)) {
            List<IRole> role = event.getGuild().getRolesByName(roleForChange);
            event.getUser().removeRole(role.get(0));
            String user = event.getUser().getName();
            String discriminator = event.getUser().getDiscriminator();
            System.out.println("For User: " + user + "#" +discriminator+ ", Role: \"" + roleForChange + "\" Removed!");
        }
    }
}
