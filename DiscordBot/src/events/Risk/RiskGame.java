package events.Risk;

import main.Info;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class RiskGame extends ListenerAdapter {

    private String name1, name2, name3, name4;

    public void onMessageReceived(MessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        TextChannel tc = event.getMessage().getTextChannel();
        Member user = event.getMember();
        Characters char1 = new Characters();

        //Startgame
        if(args[0].equalsIgnoreCase(Info.PREFIX + "risk")){
            if(args.length >= 4){
                if(event.getMessage().getMentionedMembers().size() >= 4) {
                    setName1(event.getMessage().getMentionedMembers().get(0).getUser().getName());
                    setName2(event.getMessage().getMentionedMembers().get(1).getUser().getName());
                    setName3(event.getMessage().getMentionedMembers().get(2).getUser().getName());
                    setName4(event.getMessage().getMentionedMembers().get(3).getUser().getName());

                    System.out.println(name1);
                    System.out.println(name2);
                    System.out.println(name3);
                    System.out.println(name4);

                    char1.startGame(name1, name2, name3, name4, tc);

                }else{
                    sendErrorMessageMention(tc, user);
                }
            }else{
                sendErrorMessageMention(tc, user);
            }
        }


        //attack
        if(args[0].equalsIgnoreCase(Info.PREFIX + "attack")){
            if(args.length >= 2) {

            }
        }

        //Get Soldiers
        if(args[0].equalsIgnoreCase(Info.PREFIX + "recruit")){
            if(args.length >= 2) {
                char1.getRandomSoldiers(event.getMember());
            }
        }
    }

    public void sendErrorMessageMention(TextChannel channel, Member member){
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Invalid Usage Error");
        eb.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.addField("Valid usage: /risk {user1} {user2} {user3} {user4}","", false);
        channel.sendMessage(eb.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public String getName4() {
        return name4;
    }

    public void setName4(String name4) {
        this.name4 = name4;
    }
}
