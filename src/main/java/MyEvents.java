import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionAddEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionRemoveEvent;
import sx.blah.discord.handle.impl.obj.ReactionEmoji;
import sx.blah.discord.handle.obj.IMessage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class MyEvents {
    long messageID;
    HashMap<String, String> emodjiList = new HashMap<>();
    long owner;
    String emodjiString;
    boolean debug;

    @EventSubscriber
    public void onReady(ReadyEvent event) throws IOException {
        owner = Long.parseLong(MainRunner.prop.getProperty("owner"));
        emodjiString = MainRunner.prop.getProperty("emoji");
        messageID = Long.parseLong(MainRunner.prop.getProperty("messageID"));
        debug = Boolean.parseBoolean(MainRunner.prop.getProperty("debug"));
        String[] emodjiArray = emodjiString.split(",");
        for (int i = 0; i < emodjiArray.length; i++) {
            String[] p = emodjiArray[i].split("#");
            emodjiList.put(p[0], p[1]);
        }
        IMessage message = event.getClient().getMessageByID(messageID);
        int reactionsSize = event.getClient().getMessageByID(messageID).getReactions().size();
        if (reactionsSize < emodjiList.size()) {
            for (Map.Entry<String, String> entry : emodjiList.entrySet()) {
                String emodji = entry.getKey();
                ReactionEmoji e = ReactionEmoji.of(emodji);
                message.addReaction(e);
            }
        }
        System.out.println("You can invite your bot, by this URL: https://discordapp.com/oauth2/authorize?client_id=" + event.getClient().getOurUser().getLongID() + "&scope=bot");
        event.getClient().getUserByID(owner).getOrCreatePMChannel().sendMessage("Bot Ready - " + LocalDateTime.now());
    }

    @EventSubscriber
    public void onReactionAddEvent(ReactionAddEvent event) {
        if (event.getMessage().getLongID() == messageID) {
            String name = event.getReaction().getEmoji().toString();
            if(debug)System.out.println(name);// Дебаг для новых Эмодзи
            for (Map.Entry<String, String> entry : emodjiList.entrySet()) {
                String emodjiForCheck = entry.getKey();
                String roleForChange = entry.getValue();
                BotUtils.checkAndAddRole(event, name, emodjiForCheck, roleForChange);
            }
        }
    }

    @EventSubscriber
    public void onReactionRemoveEvent(ReactionRemoveEvent event) {
        if (event.getMessage().getLongID() == messageID) {
            String name = event.getReaction().getEmoji().toString();
            for (Map.Entry<String, String> entry : emodjiList.entrySet()) {
                String emodjiForCheck = entry.getKey();
                String roleForChange = entry.getValue();
                BotUtils.checkAndRemoveRole(event, name, emodjiForCheck, roleForChange);
            }
        }
    }

    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event) {
//        if (event.getMessage().getContent().startsWith(BotUtils.BOT_PREFIX + "test"))
//            BotUtils.sendMessage(event.getChannel(), "Что делать если я лох");
    }
}