import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionAddEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionRemoveEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

import java.util.List;

class BotUtils {

    static String BOT_PREFIX = "/";

    static IDiscordClient getBuiltDiscordClient(String token) {

        return new ClientBuilder()
                .withToken(token)
                .build();

    }


    static void sendMessage(IChannel channel, String message) {


        RequestBuffer.request(() -> {
            try {
                channel.sendMessage(message);
            } catch (DiscordException e) {
                System.err.println("Message could not be sent with error: ");
                e.printStackTrace();
            }
        });
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
