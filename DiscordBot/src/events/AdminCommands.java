package events;

import main.Info;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.utils.Checks;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AdminCommands extends ListenerAdapter {

    private int counter = 0;
    private Role harass;
    private Member user;


    public void onMessageReceived(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        Role Harass = event.getGuild().getRoleById("507011090576834571");
        int size = event.getGuild().getMembersWithRoles(Harass).size();
        Member[] riley = event.getGuild().getMembersWithRoles(Harass).toArray(new Member[0]);
        user = event.getMessage().getMember();
        TextChannel text = event.getTextChannel();

        if(args[0].equalsIgnoreCase(Info.PREFIX + "phelp")){
            if(args.length <= 1) {
                Logs.helpAdminCommands(event.getMessage().getTextChannel(),user);
            }else{
                Logs.sendErrorMessageHelp(event.getMessage().getTextChannel(), user);
            }
        }

        //Mute
        if (args[0].equalsIgnoreCase(Info.PREFIX + "mute")) {
            //if(event.getAuthor().)
            if (args.length <= 1) {
                Logs.sendErrorMessageMute(event.getMessage().getChannel(), event.getMember());
            } else {
                Member target = event.getMessage().getMentionedMembers().get(0);

                Role muted = event.getGuild().getRolesByName("Muted", true).get(0);

                if (canInteract(event.getMember(), target, event.getMessage().getTextChannel())) {
                    event.getGuild().getController().addRolesToMember(target, muted).queue();

                    if (args.length >= 3) {
                        String reason = "nothing";
                        for (int i = 2; i < args.length; i++) {
                            reason += args[2] + " ";
                        }
                        Logs.logMute(target, event.getMember(), reason, event.getMessage().getChannel());
                    } else {
                        Logs.logMute(target, event.getMember(), "", event.getMessage().getChannel());
                    }
                } else {
                    //Logs.sendErrorMessagePerms(event.getMessage().getChannel(), event.getMember());
                }
            }
        }

        //Unmute
        if (args[0].equalsIgnoreCase(Info.PREFIX + "unmute")) {
            if (args.length <= 1) { //Makes sure its the correct length
                Logs.sendErrorMessageUnmute(event.getMessage().getChannel(), event.getMember());
            } else {
                Member target = event.getMessage().getMentionedMembers().get(0);
                if (canInteract(event.getMember(), target, event.getMessage().getTextChannel())) {

                    Role muted = event.getGuild().getRolesByName("Muted", true).get(0);

                    event.getGuild().getController().removeSingleRoleFromMember(target, muted).queue();
                    Logs.logUnmute(target, event.getMember(), event.getMessage().getChannel());
                }
            }
        }

        //Tempmute
        if (args[0].equalsIgnoreCase(Info.PREFIX + "tempmute")) {
            if (args.length <= 2) {
                Logs.sendErrorMessageMute(event.getTextChannel(), event.getMember());
            } else {
                Member target = event.getMessage().getMentionedMembers().get(0);
                if (canInteract(event.getMember(), target, event.getMessage().getTextChannel())) {
                    tempmute(target, parseTimeAmount(args[2]), parseTimeUnit(args[2]));
                    if (args.length > 3) {
                        String reason = "";
                        for (int i = 3; i < args.length; i++) {
                            reason += args[i] + " ";
                        }
                        Logs.logTempmute(target, event.getMember(), reason, event.getGuild().getTextChannelById("451208880626728961"), args[2]);
                    } else {
                        Logs.logTempmute(target, event.getMember(), "", event.getGuild().getTextChannelById("451208880626728961"), args[2]);
                    }
                }
            }
        }

        //Harass
        if (args[0].equalsIgnoreCase(Info.PREFIX + "harass")) {
            if (args.length <= 2) {
                //Logs.sendErrorMessageMute(event.getTextChannel(), event.getMember());
            } else {
                Member target = event.getMessage().getMentionedMembers().get(0);
                if (canInteract(event.getMember(), target, event.getMessage().getTextChannel())) {
                    tempHarass(target, parseTimeAmount(args[2]), parseTimeUnit(args[2]));
                    if (args.length > 3) {
                        String reason = "";
                        for (int i = 3; i < args.length; i++) {
                            reason += args[i] + " ";
                        }
                        Logs.logHarass(user, event.getMessage().getMentionedMembers().get(0), reason, event.getGuild().getTextChannelById("451208880626728961"), args[2]);
                    } else {
                        Logs.logHarass(user, event.getMessage().getMentionedMembers().get(0), "", event.getGuild().getTextChannelById("451208880626728961"), args[2]);
                    }
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (riley[i] == user) {
                event.getChannel().sendMessage(user.getEffectiveName() + " ur gay").queue();
            }
        }

        for(int i = 0; i < args.length; i++){
            if(args[i].equals("<@!502623581650550784>")){
                event.getChannel().sendMessage(" no u").queue();
            }
        }

        //Unharass
        if (args[0].equalsIgnoreCase(Info.PREFIX + "unharass")) {
            if (args.length <= 1) { //Makes sure its the correct length
                Logs.sendErrorMessageUnharass(event.getMessage().getChannel(), event.getMember());
            } else {
                Member target = event.getMessage().getMentionedMembers().get(0);
                if (canInteract(event.getMember(), target, event.getMessage().getTextChannel())) {

                    Role harassed = event.getGuild().getRolesByName("ur gay", true).get(0);

                    event.getGuild().getController().removeSingleRoleFromMember(target, harassed).queue();
                    Logs.logUnharass(target, event.getMember(), event.getMessage().getChannel());
                }
            }
        }

        //BotTalk
        if (args[0].equalsIgnoreCase(Info.PREFIX + "owner")) {
            if (args.length <= 1) {
                //Makes sure its the correct length
                if (canInteract(event.getMember(), event.getGuild().getOwner(), event.getMessage().getTextChannel())) {
                    Scanner scan = new Scanner(System.in);
                    String saySomething = scan.nextLine();

                    event.getChannel().sendMessage(saySomething).queue();
                }else{
                    Logs.sendErrorMessageInteract(event.getMessage().getTextChannel());
                }
            }
        }


        //Purge
        if (args[0].equalsIgnoreCase(Info.PREFIX + "purge")) {
            if(canInteract(user, user, event.getMessage().getTextChannel())) {
                if (args.length == 2) {
                    String amountOfMessagesString = args[1];
                    int amountOfMessages = 1;
                    try {
                        amountOfMessages = Integer.parseInt(amountOfMessagesString) + 1;
                    }catch (java.lang.NumberFormatException v){
                        Logs.sendErrorMessagePurgeNumber(event.getMessage().getTextChannel(), user);
                        return;
                    }
                    if (amountOfMessages < 50) {
                        List<Message> msgs = event.getChannel().getHistory().retrievePast(amountOfMessages).complete();
                        event.getChannel().purgeMessages(msgs);
                    }
                } else if (args.length == 1) {
                    int amountOfMessages = 6;
                    List<Message> msgs = event.getChannel().getHistory().retrievePast(amountOfMessages).complete();
                    event.getChannel().purgeMessages(msgs);
                } else {
                    Logs.sendErrorMessagePurge(event.getChannel(), user);
                }
            }
        }

    }

    public boolean canInteract(Member issuer, Member target, TextChannel channel) {
        Checks.notNull(issuer, "Issuer Member");
        Checks.notNull(target, "Target Member");

        Guild guild = issuer.getGuild();
        if (!guild.equals(target.getGuild()))
            throw new IllegalArgumentException("Provided members must both be Member objects of the same Guild!");
        if (guild.getOwner().equals(issuer)) {
            return true;
        }
        if (guild.getOwner().equals(target)) {
            return false;
        }
        java.util.List<Role> issuerRoles = issuer.getRoles();
        List<Role> targetRoles = target.getRoles();
        Logs.sendErrorMessagePerms(channel, user);
//        Logs.sendErrorMessageInteract(channel);
        return !(issuerRoles).isEmpty() && ((targetRoles).isEmpty());
    }

    public int parseTimeAmount(String time) {
        TimeUnit unit = TimeUnit.SECONDS;
        char[] t = time.toCharArray();
        int breakPoint = 0;
        String amount = "";
        int parsedAmount = 0;

        for (int i = 0; i < t.length; i++) {
            if (t[i] == 's') {
                unit = TimeUnit.SECONDS;
                breakPoint = i;
                break;
            } else if (t[i] == 'm') {
                unit = TimeUnit.MINUTES;
            } else if (t[i] == 'h') {
                unit = TimeUnit.HOURS;
            } else if (t[i] == 'd') {
                unit = TimeUnit.DAYS;
            }
        }

        for (int i = 0; i < breakPoint; i++) {
            amount += t[i];
        }
        parsedAmount = Integer.parseInt(amount);
        return parsedAmount;
    }

    public TimeUnit parseTimeUnit(String time) {
        TimeUnit unit = TimeUnit.SECONDS;
        char[] t = time.toCharArray();
        int breakPoint = 0;
        String amount = "";
        int parsedAmount = 0;

        for (int i = 0; i < t.length; i++) {
            if (t[i] == 's') {
                unit = TimeUnit.SECONDS;
                breakPoint = i;
                break;
            } else if (t[i] == 'm') {
                unit = TimeUnit.MINUTES;
            } else if (t[i] == 'h') {
                unit = TimeUnit.HOURS;
            } else if (t[i] == 'd') {
                unit = TimeUnit.DAYS;
            }
        }
        return unit;
    }

    private void tempmute(Member target, int time, TimeUnit unit) {
        Role muted = target.getGuild().getRolesByName("muted", true).get(0);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                counter++;
                target.getGuild().getController().addSingleRoleToMember(target, muted).queue();
                if (counter == 2) {
                    target.getGuild().getController().removeSingleRoleFromMember(target, muted).queue();
                    this.cancel();
                }
            }
        };
        switch (unit) {
            case SECONDS:
                timer.schedule(task, 0, time * 1000);
                break;

            case MINUTES:
                timer.schedule(task, 0, (time * 1000) * 60);
                break;

            case HOURS:
                timer.schedule(task, 0, ((time * 1000) * 60) * 60);
                break;

            case DAYS:
                timer.schedule(task, 0, (((time * 1000) * 60) * 60) * 24);
                break;
        }
    }
    private void tempHarass(Member target, int time, TimeUnit unit){
        Role harrass = target.getGuild().getRolesByName("ur gay", true).get(0);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                counter++;
                target.getGuild().getController().addSingleRoleToMember(target, harrass).queue();
                //event.getChannel().sendMessage(name + " ur gay").queue();
                    //Member riley = event.getMessage().getGuild().getMemberById("398271391964725249");
                if(counter == 2){
                    target.getGuild().getController().removeSingleRoleFromMember(target, harrass).queue();
                    this.cancel();
                }
            }
        };
        switch (unit) {
            case SECONDS:
                timer.schedule(task, 0, time * 1000);
                break;

            case MINUTES:
                timer.schedule(task, 0, (time * 1000) * 60);
                break;

            case HOURS:
                timer.schedule(task, 0, ((time * 1000) * 60) * 60);
                break;

            case DAYS:
                timer.schedule(task, 0, (((time * 1000) * 60) * 60) * 24);
                break;
        }
    }
}
