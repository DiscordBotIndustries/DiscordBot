package events.TextResponse;

import events.Logs;
import main.Info;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


public class TextResponder extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {
        Member riley = event.getMessage().getGuild().getMemberById("398271391964725249");
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        String[] ussrWords = new String[]{"communism", "commie", "stalin", "marx", "ussr"};
        String[] rileyWords = new String[]{"riley", "wywee"};
        Member user = event.getMember();

        if(args[0].equalsIgnoreCase(Info.PREFIX + "help")){
            if(args.length <= 1) {
                Logs.help(event.getMessage().getTextChannel(),user);
            }else{
                Logs.sendErrorMessageHelp(event.getMessage().getTextChannel(), user);
            }
        }

        if(args[0].equalsIgnoreCase(Info.PREFIX + "thelp")){
            if(args.length <= 1) {
                Logs.helpTextResponse(event.getMessage().getTextChannel(),user);
            }else{
                Logs.sendErrorMessageHelp(event.getMessage().getTextChannel(), user);
            }
        }

        //Wywee
        if (args[0].equalsIgnoreCase(Info.PREFIX + "teleport") || args[0].equalsIgnoreCase(Info.PREFIX + "wywee") && !event.getMember().getUser().isBot()) {
            event.getChannel().sendMessage("Nothin personal kid: ").queue();
            event.getChannel().sendMessage("https://cdn.discordapp.com/attachments/451208880626728961/490989247453462538/IMG_0114.JPG").queue();
        }

        //wood
        if (args[0].equalsIgnoreCase(Info.PREFIX + "wood") && !event.getMember().getUser().isBot()) {
            event.getChannel().sendMessage("https://cdn.discordapp.com/attachments/287584145612210178/496357961740845058/TodoDeku-1.png").queue();
        }

        //Text response for anything
        for (int i = 0; i < ussrWords.length; i++) {
            if (args[0].toLowerCase().contains(ussrWords[i]) && !event.getMember().getUser().isBot()) {
                event.getChannel().sendMessage("https://www.youtube.com/watch?v=U06jlgpMtQs").queue();
            }
        }
        /*for (int i = 0; i < rileyWords.length; i++) {
               if (args[0].toLowerCase().contains(rileyWords[i]) && !event.getMember().getUser().isBot()) {
                   event.getChannel().sendMessage("https://cdn.discordapp.com/attachments/451208880626728961/504985013914304523/emoji.png").queue();
               }
           }*/
        //}
    }
}