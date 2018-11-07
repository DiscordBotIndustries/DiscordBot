package events;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Logs {

    public static void sendErrorMessageDeath(MessageChannel channel, Member member){
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Invalid Usage");
        eb.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription("{} = Required, () = Not Required");
        eb.addField("Invalid usage: /death (user)","", false);
        channel.sendMessage(eb.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }

    public static void sendErrorMessageSubreddit(MessageChannel channel, Member member){
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Invalid Usage");
        eb.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription("{} = Required, () = Not Required");
        eb.addField("Invalid usage: /meme (subreddit)","", false);
        //eb.addField("make sure it is the correct subreddit name", "", false);
        channel.sendMessage(eb.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }

    public static void sendErrorMessageSubredditv2(MessageChannel channel, Member member){
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Invalid Usage");
        eb.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription("{} = Required, () = Not Required");
        eb.addField("subreddit is either banned, deleted, or does not exist","", false);
        //eb.addField("make sure it is the correct subreddit name", "", false);
        channel.sendMessage(eb.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }

    public static void sendMemeWithSubreddit(MessageChannel channel, Member member, String url, String subRedditRecieved, String title){
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(title);
        eb.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription("This is a Meme from: " + subRedditRecieved + ", yay!");
        eb.setImage(url);
        eb.addField(url, "", false);
        channel.sendMessage(eb.build()).queue();
    }

    public static void sendMeme(MessageChannel channel, Member member, String url, String title){
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(title);
        eb.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription("This is a Meme from: ... oh wait, u didnt put in a subreddit /shrug");
        eb.setImage(url);
        eb.addField(url, "", false);
        channel.sendMessage(eb.build()).queue();
    }

    public static void sendQuote(MessageChannel channel, Member member, String quote){
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Here is a quality quote for you:");
        eb.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription(quote);
        //eb.addField(quote, "", false);
        channel.sendMessage(eb.build()).queue();
    }

    public static void sendCopypastaError(MessageChannel channel, Member member){
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Invalid Usage");
        eb.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription("{} = Required, () = Not Required" +
                "\n Note: it may be correct, sometimes the actual post is too long");
        eb.addField("Correct Usage: /copypasta","", false);
        //eb.addField("make sure it is the correct subreddit name", "", false);
        channel.sendMessage(eb.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }

    public static void helpFunCommands(MessageChannel channel, Member member) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Fun Commands");
        eb.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#00FF00"));
        eb.setDescription("{} = Required, () = Not Required");
        eb.addField("``Text Responses:``\n" +
                "/hello - simply says hello there :)\n" +
                "/online - tells you how many people are online\n", "", false);
        eb.addField("``Joke Responses:``\n" +
                "/size (user) - checks to see a size\n" +
                "/drunk - gets u DRUNK AF\n" +
                "/death (user) - gives u a way to die yay!\n" +
                "/quote - grabs a random quote from out of context", "", false);
        eb.addField("``Pulling From InterWebs: ``\n" +
                "/copypasta - grabs a random copypasta from the interwebs\n" +
                "/wink - /meme but for more devious purposes\n" +
                "/meme (subreddit) - pulls a meme from MemeEconomy if you do not add a subreddit", "", false);
        eb.addField("``Games!:`` \n" +
                "/bstart - blackjack - once started use the commands /draw, /hit, and /stay\n" +
                "/poll {title}, {option1} {option2} - creates strawpoll with specified amount of options", "", false);
        channel.sendMessage(eb.build()).queue();
    }

    public static void helpAdminCommands(MessageChannel channel, Member member) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Admin Commands");
        eb.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#00FF00"));
        eb.setDescription("{} = Required, () = Not Required");
        eb.addField("``Over Time``\n" +
                "/tempmute {user} {time period} - mutes for specified period of time)\n" +
                "/harass {user} {time period} - bot harasses user\n", "", false);
        eb.addField("``Undoes cruel punishments``\n" +
                "/unmute {user} - unmutes a user\n" +
                "/unharass {user} - stops harassing a user\n", "", false);
        eb.addField("``FOR EVER HAHA``\n" +
                "/mute {user} - mutes someone for ever\n", "", false);
        eb.addField("``Secret!:`` \n" +
                "/owner - (enter devious things here)\n" +
                "/purge (number of messages) - deletes specified number of messages", "", false);
        channel.sendMessage(eb.build()).queue();
    }

    public static void helpTextResponse(MessageChannel channel, Member member) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Text Response");
        eb.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#00FF00"));
        eb.setDescription("{} = Required, () = Not Required");
        eb.addField("``Just Responses:``\n" +
                "/wywee or /teleport - WYWEEEEEE\n" +
                "/wood - u know...\n", "", false);
        channel.sendMessage(eb.build()).queue();
    }

    public static void help(MessageChannel channel, Member member) {
        EmbedBuilder eb = new EmbedBuilder();
        //eb.setTitle("help");
        eb.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#00FF00"));
        //eb.setDescription("{} = Required, () = Not Required");
        eb.addField("Subsections of Commands:\n" +
                "/fhelp - Gives help for fun commands\n" +
                "/ahelp - Gives help for admin commands\n" +
                "/thelp - Gives help for all the text responses", "", false);
        channel.sendMessage(eb.build()).queue();
    }

    public static void sendErrorMessageHelp(MessageChannel channel, Member member) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Invalid Usage");
        eb.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription("{} = Required, () = Not Required");
        eb.addField("Invalid usage: /help", "", false);
        channel.sendMessage(eb.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }

    public static void sendErrorMessageMute(MessageChannel channel, Member member) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Invalid Usage");
        eb.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription("{} = Required, () = Not Required");
        eb.addField("Invalid usage: /mute {user} (reason)", "", false);
        channel.sendMessage(eb.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }

    public static void sendErrorMessageInteract(TextChannel channel) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Can't Interact");
        //eb.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription("Sorry but your not high enough rank to use that!");
        //eb.addField("Invalid usage: /mute {user} (reason)", "", false);
        channel.sendMessage(eb.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }

    public static void sendBlackJackStart(TextChannel channel, Member memeber) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("You have started blackjack!");
        eb.setAuthor(memeber.getUser().getName(), memeber.getUser().getAvatarUrl(), memeber.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription("Welcome! do /draw to start!");
        //eb.addField("Invalid usage: /mute {user} (reason)", "", false);
        channel.sendMessage(eb.build()).queue();
    }

    public static void sendBlackJackDraw(TextChannel channel, Member memeber, int drawNum) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("``shuffles deck``");
        eb.setAuthor(memeber.getUser().getName(), memeber.getUser().getAvatarUrl(), memeber.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription("Here is your hand: " + drawNum);
        eb.addField("do you want to /stay? or /hit?", "", false);
        channel.sendMessage(eb.build()).queue();
    }

    public static void sendBlackJackHit(TextChannel channel, Member memeber, int newDraw, int oldDraw) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("You have hit!");
        eb.setAuthor(memeber.getUser().getName(), memeber.getUser().getAvatarUrl(), memeber.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription("Here is your new card: " + newDraw + ", and your total: " + (oldDraw));
        eb.addField("do you want to /stay? or /hit?", "", false);
        channel.sendMessage(eb.build()).queue();
    }

    public static void sendBlackJackWin21(TextChannel channel, Member memeber, int oldDraw) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("You won!!");
        eb.setAuthor(memeber.getUser().getName(), memeber.getUser().getAvatarUrl(), memeber.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription("You got " + oldDraw);
        eb.addField("congrats!", "", false);
        channel.sendMessage(eb.build()).queue();
    }

    public static void sendBlackJackWin(TextChannel channel, Member memeber, int oldDraw, int botDraw) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("You won!!");
        eb.setAuthor(memeber.getUser().getName(), memeber.getUser().getAvatarUrl(), memeber.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription("You got " + oldDraw + ", and my hand was a " + botDraw);
        eb.addField("congrats!", "", false);
        channel.sendMessage(eb.build()).queue();
    }

    public static void sendBlackJackWinBot(TextChannel channel, Member memeber, int oldDraw) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("You won!!");
        eb.setAuthor(memeber.getUser().getName(), memeber.getUser().getAvatarUrl(), memeber.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription("You got " + oldDraw + ", but the bot went over 21 nerd");
        eb.addField("congrats!", "", false);
        channel.sendMessage(eb.build()).queue();
    }

    public static void sendBlackJackLoss21(TextChannel channel, Member memeber, int oldDraw) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("You Lost to a bot...");
        eb.setAuthor(memeber.getUser().getName(), memeber.getUser().getAvatarUrl(), memeber.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription("You got " + oldDraw + ", and  that darn bot got a 21...");
        eb.addField("too bad!", "", false);
        channel.sendMessage(eb.build()).queue();
    }

    public static void sendBlackJackLoss(TextChannel channel, Member memeber, int oldDraw, int botDraw) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("You Lost to a bot...");
        eb.setAuthor(memeber.getUser().getName(), memeber.getUser().getAvatarUrl(), memeber.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription("You got " + oldDraw + ", and  that darn bot got a: " + botDraw);
        eb.addField("too bad!", "", false);
        channel.sendMessage(eb.build()).queue();
    }

    public static void sendErrorMessageWink(MessageChannel channel, Member member) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Invalid Usage");
        eb.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription("{} = Required, () = Not Required \n" +
                "Note: the subreddit you typed in may just not exist or is banned...");
        eb.addField("Invalid usage: /wink {subreddit}", "", false);
        channel.sendMessage(eb.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }

    public static void sendErrorMessageUnmute(MessageChannel channel, Member member) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Invalid Usage");
        eb.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription("{} = Required, () = Not Required");
        eb.addField("Invalid usage: /unmute {user}", "", false);
        channel.sendMessage(eb.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }

    public static void numVotes(MessageChannel channel, Member member, List options, List votes, int numOfOptions) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Votes:");
        eb.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        for(int i = 0; i < numOfOptions; i++) {
            eb.addField(options.get(i) + " - " + votes.get(i), "", false);
        }
        channel.sendMessage(eb.build()).queue();
    }

    public static void sendErrorMessageUnharass(MessageChannel channel, Member member) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Invalid Usage");
        eb.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription("{} = Required, () = Not Required");
        eb.addField("Invalid usage: /unharass {user}", "", false);
        channel.sendMessage(eb.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }

    public static void sendErrorMessageNoPoll(MessageChannel channel, Member member) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("No Poll Yet!");
        eb.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription("Oo()0f");
        eb.addField("There is no poll yet! Create one using /poll {title}, {options} (more than two)", "", false);
        channel.sendMessage(eb.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }

    public static void sendErrorMessageDrunk(MessageChannel channel, Member member) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Invalid Usage");
        eb.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription("{} = Required, () = Not Required");
        eb.addField("Invalid usage: /drunk", "", false);
        channel.sendMessage(eb.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }

    public static void sendErrorMessagePerms(MessageChannel channel, Member member) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Invalid Perms");
        eb.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.addField("You do not have the perms for that!", "", false);
        channel.sendMessage(eb.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }

    public static void sendErrorMessageQuote(MessageChannel channel, Member member) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Invalid Usage");
        eb.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription("{} = Required, () = Not Required");
        eb.addField("Invalid Usage: /quote", "", false);
        channel.sendMessage(eb.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }

    public static void sendErrorMessagePurgeNumber(MessageChannel channel, Member member) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Invalid Usage");
        eb.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription("put in a number!");
        //eb.addField("put in a number!", "", false);
        channel.sendMessage(eb.build()).queue();
    }

    public static void sendErrorMessagePurge(MessageChannel channel, Member member) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Invalid Perms");
        eb.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        eb.setColor(Color.decode("#AA3939"));
        eb.setDescription("{} = Required, () = Not Required");
        eb.addField("Correct Usage: /purge (number of messages)", "", false);
        channel.sendMessage(eb.build()).queue();
    }

    public static void logMute(Member muted, Member muter, String reason, MessageChannel channel) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder ebl = new EmbedBuilder();
        ebl.setTitle("Mute");
        ebl.setAuthor(muter.getUser().getName(), muter.getUser().getAvatarUrl(), muter.getUser().getAvatarUrl());
        ebl.setColor(Color.decode("#2e9cca"));
        ebl.addField("Muted user: ", muted.getAsMention(), false);
        ebl.addField("Muter: ", muter.getAsMention(), false);
        //ebl.addField("Reason: ", reason, false);
        ebl.addField("Date: ", sdf.format(date), false);
        ebl.addField("Time: ", stf.format(date), false);
        channel.sendMessage(ebl.build()).queue();
    }

    public static void logUnmute(Member muted, Member muter, MessageChannel channel) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder ebl = new EmbedBuilder();
        ebl.setTitle("Unmute");
        ebl.setAuthor(muter.getUser().getName(), muter.getUser().getAvatarUrl(), muter.getUser().getAvatarUrl());
        ebl.setColor(Color.decode("#2e9cca"));
        ebl.addField("Unmuted user: ", muted.getAsMention(), false);
        ebl.addField("Unmuter: ", muter.getAsMention(), false);
        ebl.addField("Date: ", sdf.format(date), false);
        ebl.addField("Time: ", stf.format(date), false);
        channel.sendMessage(ebl.build()).queue();
    }

    public static void logUnharass(Member muted, Member muter, MessageChannel channel) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder ebl = new EmbedBuilder();
        ebl.setTitle("Unharass");
        ebl.setAuthor(muter.getUser().getName(), muter.getUser().getAvatarUrl(), muter.getUser().getAvatarUrl());
        ebl.setColor(Color.decode("#2e9cca"));
        ebl.addField("Unharassed user: ", muted.getAsMention(), false);
        ebl.addField("Unharasser: ", muter.getAsMention(), false);
        ebl.addField("Date: ", sdf.format(date), false);
        ebl.addField("Time: ", stf.format(date), false);
        channel.sendMessage(ebl.build()).queue();
    }

    public static void logTempmute(Member muted, Member muter, String reason, MessageChannel channel, String Time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder ebl = new EmbedBuilder();
        ebl.setTitle("Tempmute");
        ebl.setColor(Color.decode("#2e9cca"));
        ebl.addField("Tempmutted user: ", muted.getAsMention(), false);
        ebl.addField("Muter: ", muter.getAsMention(), false);
        //ebl.addField("Reason: ", reason, false);
        ebl.addField("Amount of Time: ", Time, false);
        ebl.addField("Date: ", sdf.format(date), false);
        ebl.addField("Time: ", stf.format(date), false);
        channel.sendMessage(ebl.build()).queue();
    }

    public static void logHarass(Member muted, Member muter, String reason, MessageChannel channel, String Time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder ebl1 = new EmbedBuilder();
        ebl1.setTitle("Harassed");
        ebl1.setColor(Color.decode("#2e9cca"));
        ebl1.addField("Harrassed user: ", muted.getAsMention(), false);
        ebl1.addField("Harasser: ", muter.getAsMention(), false);
        ebl1.addField("Amount of Time: ", Time, false);
        ebl1.addField("Date: ", sdf.format(date), false);
        ebl1.addField("Time: ", stf.format(date), false);
        channel.sendMessage(ebl1.build()).queue();
    }
}
