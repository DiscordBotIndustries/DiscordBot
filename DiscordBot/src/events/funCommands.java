package events;

import com.samuelmaddock.strawpollwrapper.DupCheckType;
import com.samuelmaddock.strawpollwrapper.StrawPoll;
import main.Info;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageHistory;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.json.JSONException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

//import com.google.api.client.googleapis.json.GoogleJsonResponseException;
//import com.google.api.client.http.HttpRequest;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.services.youtube.YouTube;
//import com.google.api.services.youtube.model.PlaylistItemListResponse;
//import com.google.api.services.youtube.model.SearchListResponse;
//import com.google.api.services.youtube.model.SearchResult;



public class funCommands extends ListenerAdapter {

    private boolean inGame = false;
    private boolean didTheyDraw = false;
    private int oldDraw;
    private int draw;
    private int botDraw;
    private int newDraw;
    private Member user;
    private Member player;
    private StrawPoll poll;
    private boolean isPoll = false;
    private int numOfOptions = 0;

    public void onMessageReceived(MessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        String[] pollParameters = event.getMessage().getContentRaw().split(", ");
        user = event.getMessage().getMember();
        int online = 0;

        if(args[0].equalsIgnoreCase(Info.PREFIX + "fhelp")){
            if(args.length <= 1) {
                Logs.helpFunCommands(event.getMessage().getTextChannel(),user);
            }else{
                Logs.sendErrorMessageHelp(event.getMessage().getTextChannel(), user);
            }
        }

        //Hello There
        if(args[0].equalsIgnoreCase(Info.PREFIX + "hello")){
            if(!event.getMember().getUser().isBot() && event.getMember().getNickname() != null) {
                event.getChannel().sendMessage("Hello " + event.getMember().getNickname() + "!").queue();
            }else if(!event.getMember().getUser().isBot()){
                event.getChannel().sendMessage("Hello " + event.getMember().getUser().getName() + "!").queue();
            }
        }

        //Online
        if(args[0].equalsIgnoreCase(Info.PREFIX + "online") && !event.getMember().getUser().isBot()){
            for (int i = 0; i < event.getGuild().getMembers().size(); i++) {
                if (event.getGuild().getMembers().get(i).getOnlineStatus() == OnlineStatus.ONLINE || event.getGuild().getMembers().get(i).getOnlineStatus() == OnlineStatus.DO_NOT_DISTURB) {
                    online++;
                }
            }
            event.getChannel().sendMessage("There are: " + online + " people online, there are: " + event.getGuild().getMembers().size() + " members in this discord!").queue();
        }


        //Size
        if(args[0].equalsIgnoreCase(Info.PREFIX + "size") && !event.getMember().getUser().isBot()){
            if(args.length <= 1) {
                int size = (int) (Math.random() * (68 - 1)) + 1;
                String numEquals = "";
                event.getChannel().sendMessage("``Pulls Pants Down...``").queueAfter(0, TimeUnit.SECONDS);
                event.getChannel().sendMessage("``Looks down...``").queueAfter(1, TimeUnit.SECONDS);
                event.getChannel().sendMessage("``Has existential crisis...``").queueAfter(2, TimeUnit.SECONDS);
                for (int i = 0; i < size; i++) {
                    numEquals += "=";
                }
                event.getChannel().sendMessage("Hey " + event.getAuthor().getName() + " your size is: 8" + numEquals + "D").queueAfter(3, TimeUnit.SECONDS);
                if (size <= 30) {
                    event.getChannel().sendMessage("man that's a chode").queueAfter(4, TimeUnit.SECONDS);
                } else if (size >= 32 && size <= 59 || size == 31) {
                    event.getChannel().sendMessage("not bad").queueAfter(4, TimeUnit.SECONDS);
                } else {
                    event.getChannel().sendMessage("Big Black Clocks").queueAfter(4, TimeUnit.SECONDS);
                }
            }else if(args.length <= 2){
                String name = "";
                name = event.getMessage().getMentionedMembers().get(0).getUser().getName();
                event.getChannel().sendMessage("``Enters Bathroom...``").queueAfter(0, TimeUnit.SECONDS);
                event.getChannel().sendMessage("``Sees feet in stall...``").queueAfter(1, TimeUnit.SECONDS);
                event.getChannel().sendMessage("``Peaks over the stall door...``").queueAfter(2, TimeUnit.SECONDS);

                int size = (int) (Math.random() * 68) + 1;
                String numEquals = "";

                for (int i = 0; i < size; i++) {
                    numEquals += "=";
                }
                event.getChannel().sendMessage("Oh, hey, " + name + ",your size is: 8" + numEquals + "D").queueAfter(4, TimeUnit.SECONDS);
                if (size <= 30) {
                    event.getChannel().sendMessage("``chode``").queueAfter(5, TimeUnit.SECONDS);
                } else if (size >= 32 && size <= 59 || size == 31) {
                    event.getChannel().sendMessage("``that is a good sized dong``").queueAfter(5, TimeUnit.SECONDS);
                } else {
                    event.getChannel().sendMessage("``Big Black Clock``").queueAfter(5, TimeUnit.SECONDS);
                }
            }
        }

        //Death
        if(args[0].equalsIgnoreCase(Info.PREFIX + "death") && !event.getMember().getUser().isBot()){
            ArrayList<String> ded = new ArrayList<>();

            String token1 = "";

            // for-each loop for calculating heat index of May - October

            // create Scanner inFile1
            Scanner inFile1 = null;
            try {
                inFile1 = new Scanner(new File("C:\\Users\\Cam\\Dropbox\\Monesmith, Cam\\APCS Source Code\\MyPrograms\\DiscordBot\\src\\events\\TextFiles\\death.txt")).useDelimiter(",\\s*");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            ArrayList<String> temps = new ArrayList<String>();

            // while loop
            while (inFile1.hasNext()) {
                // find next line
                token1 = inFile1.next();
                temps.add(token1);
            }
            inFile1.close();

            String[] tempsArray = temps.toArray(new String[0]);

            try {
                //Collections.addAll(ded, "", " death", " grim reaper", " cliff jump no parachute", " white man in hood", " neck rope", " hitler shower", " toaster bath", " wood on wood", " demi lavato", " NOT in man box", " man box", " self immolation", " born^-1", " low ground", " American school system", " Japanese pilot", " gay in Iran", " plug fork", " wrist knife", " F", " 1800s black people", " self-homocide", " spontaneous combustion", " atomic explosion", " lack of will to live", " did you feel my bone", " blender", " sparky seat");
                String name = "";
                int random = getRandomNumberInRange(1, temps.size() - 1);
                try {
                    if (args.length <= 1) {
                        name = event.getMessage().getAuthor().getName();
                        event.getChannel().sendMessage(name + " go commit " + temps.get(random)).queueAfter(0, TimeUnit.SECONDS);
                    } else if (args.length <= 2) {
                        name = event.getMessage().getMentionedMembers().get(0).getUser().getName();
                        event.getChannel().sendMessage(name + ", go commit " + temps.get(random)).queueAfter(0, TimeUnit.SECONDS);
                    }
                }
                catch (IndexOutOfBoundsException e) {
                    Logs.sendErrorMessageDeath(event.getMessage().getChannel(), event.getMember());
                }
            }
            catch(IllegalArgumentException u){
                event.getChannel().sendMessage("that one was too bigg, try again!").queueAfter(0, TimeUnit.SECONDS);
            }
        }

        //Meme
        if(args[0].equalsIgnoreCase(Info.PREFIX + "meme") && !event.getMember().getUser().isBot()){
            //ImageGrabbers ig = new ImageGrabbers();
            //ig.grabImage(event.getMessage().getTextChannel(), user);
            if (args.length <= 1) {
                JSONGrabber image = null;
                image = new JSONGrabber();

                String url = "";
                String title = "gay";
                try {
                    url = image.grabRedditLink();
                    title = image.getTitle();
                } catch (IOException f) {
                    f.printStackTrace();
                }

                System.out.println(url);
                Logs.sendMeme(event.getMessage().getChannel(), user, url, title);
            } else if ((args.length <= 2)) {
                try {
                    String subReddit = args[1];
                    JSONGrabber image = null;
                    image = new JSONGrabber();
                    String url = "";
                    String subRedditRecieved = "";
                    String title = "gay";
                    boolean whitelist = image.getWhitlist();

                    System.out.println(subReddit);

                    try {
                        url = image.grabRedditLink(subReddit);
                        subRedditRecieved = image.getSubreddit();
                        title = image.getTitle();
                        whitelist = image.getWhitlist();
                        System.out.println(url);
                        if(whitelist){
                            //url = "https://cdn.discordapp.com/attachments/287584145612210178/496357961740845058/TodoDeku-1.png";
                        }
                        Logs.sendMemeWithSubreddit(event.getMessage().getChannel(), user, url, subRedditRecieved, title);
                    } catch (IOException f) {
                        Logs.sendErrorMessageSubredditv2(event.getMessage().getChannel(), user);
                    }


                } catch (JSONException c) {
                    //Logs.sendErrorMessageSubreddit(event.getMessage().getChannel(), user);
                    Logs.sendErrorMessageSubredditv2(event.getMessage().getChannel(), user);
                }
            } else {
                Logs.sendErrorMessageSubreddit(event.getMessage().getChannel(), user);
            }
        }

        //copypasaa
        if(args[0].equalsIgnoreCase(Info.PREFIX + "copypasta") && !event.getMember().getUser().isBot()){
            if (args.length <= 1) {
                try {
                    JSONGrabber image = null;
                    image = new JSONGrabber();

                    String text = "";
                    try {
                        String url = image.grabRedditLink("copypasta");
                        text = image.getSelftext();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    event.getChannel().sendMessage(text).queue();
                }catch (java.lang.IllegalArgumentException x){
                    Logs.sendCopypastaError(event.getMessage().getChannel(), user);
                }
            }else {
                Logs.sendCopypastaError(event.getMessage().getChannel(), user);
            }
        }

        //Drunk
        if(args[0].equalsIgnoreCase(Info.PREFIX + "drunk") && !event.getMember().getUser().isBot()) {
            if (args.length <= 1) {
                ArrayList<String> actions = new ArrayList<>();
                Collections.addAll(actions, "listening to Hardbass", "being SUPER gay", "mocking riley", "shooting himself", "committing Oo()0f", "wrestling ur mom", "becoming god", "slowly dying inside", "BECOMING RILEY");
                event.getChannel().sendMessage("``Walks into bar...``").queueAfter(0, TimeUnit.SECONDS);
                event.getChannel().sendMessage("``Walks up to bartender...``").queueAfter(1, TimeUnit.SECONDS);
                event.getChannel().sendMessage("``\"Gimme a milk,\" he says loudly...``").queueAfter(2, TimeUnit.SECONDS);
                event.getChannel().sendMessage("``Takes a FAT swig...``").queueAfter(3, TimeUnit.SECONDS);
                event.getChannel().sendMessage("``I am drunk...``").queueAfter(4, TimeUnit.SECONDS);
                event.getChannel().sendMessage(event.getMember().getEffectiveName() + " is " + actions.get(getRandomNumberInRange(0, 8))).queueAfter(5, TimeUnit.SECONDS);
            } else {
                Logs.sendErrorMessageDrunk(event.getMessage().getTextChannel(), user);
            }
        }

        //Wink
        if(args[0].equalsIgnoreCase(Info.PREFIX + "wink") && !event.getMember().getUser().isBot()){
            if (args.length == 2) {
                try {
                    String subReddit = args[1];
                    JSONGrabber image = null;
                    image = new JSONGrabber();
                    String url = "";

                    System.out.println(subReddit + "-" + user.getUser().getName());

                    try {
                        try {
                            url = image.grabRedditLink(subReddit);
                            event.getChannel().sendMessage("here is ur image ;)\n" + /*"https://www.reddit.com/r/NoFap/\n" + "gottem"*/url).queue();
                        } catch (IOException f) {
                            System.out.println("this one2");
                            Logs.sendErrorMessageWink(event.getMessage().getChannel(), user);
                        }
                    }catch (java.lang.IndexOutOfBoundsException b){
                        System.out.println("this one1");
                        Logs.sendErrorMessageWink(event.getMessage().getTextChannel(), user);
                    }
                } catch (org.json.JSONException x) {
                    System.out.println("this one");
                    Logs.sendErrorMessageWink(event.getMessage().getChannel(), user);
                }
            }else if(args.length == 1){
                try {
                    JSONGrabber image = null;
                    image = new JSONGrabber();
                    String url = "";

                    System.out.println(user.getUser().getName());

                    try {
                        try {
                            url = image.grabRedditLinkWink();
                            event.getChannel().sendMessage("here is ur image ;)\n" + /*"https://www.reddit.com/r/NoFap/\n" + "gottem"*/url).queue();
                        } catch (IOException f) {
                            System.out.println("this one2");
                            Logs.sendErrorMessageWink(event.getMessage().getChannel(), user);
                        }
                    }catch (java.lang.IndexOutOfBoundsException b){
                        System.out.println("this one1");
                        Logs.sendErrorMessageWink(event.getMessage().getTextChannel(), user);
                    }
                } catch (org.json.JSONException x) {
                    System.out.println("this one");
                    Logs.sendErrorMessageWink(event.getMessage().getChannel(), user);
                }
            }else if(args.length == 3){
                try {
                    JSONGrabber image = null;
                    image = new JSONGrabber();
                    String url = "";

                    System.out.println(user.getUser().getName());

                    try {
                        try {
                            url = image.grabRedditLinkWink();
                                //event.getMember().getUser().openPrivateChannel().queue(channel -> {

                            //});
                        } catch (IOException f) {
                            System.out.println("this one2");
                            Logs.sendErrorMessageWink(event.getMessage().getChannel(), user);
                        }
                    }catch (java.lang.IndexOutOfBoundsException b){
                        System.out.println("this one1");
                        Logs.sendErrorMessageWink(event.getMessage().getTextChannel(), user);
                    }
                } catch (org.json.JSONException x) {
                    System.out.println("this one");
                    Logs.sendErrorMessageWink(event.getMessage().getChannel(), user);
                }
            }else{
                System.out.println("this one 3");
                Logs.sendErrorMessageWink(event.getMessage().getChannel(), user);
            }
        }

        //Polls
        if(args[0].equalsIgnoreCase(Info.PREFIX + "poll") && !event.getMember().getUser().isBot()){

                poll = new StrawPoll();
                poll.setTitle(pollParameters[0].substring(5));
                for(int i = 1; i < pollParameters.length; i++) {
                    poll.addOptions(pollParameters[i]);
                }
                poll.setDupCheck(DupCheckType.NORMAL);
                poll.setIsMulti(false);
                numOfOptions = pollParameters.length-1;
                poll.create();
                event.getChannel().sendMessage(poll.getPollURL()).queue();
                isPoll = true;
        }
        if(isPoll){
            if(args[0].equalsIgnoreCase(Info.PREFIX + "votes") && !event.getMember().getUser().isBot()) {
                if (args.length <= 1) {
                    poll.update();
                    Logs.numVotes(event.getMessage().getTextChannel(), user, poll.getOptions(), poll.getVotes(), numOfOptions);
                }
            }
        }else if((args[0].equalsIgnoreCase(Info.PREFIX + "votes") && !event.getMember().getUser().isBot() && !isPoll && !event.getMember().getUser().isBot())){
            Logs.sendErrorMessageNoPoll(event.getMessage().getTextChannel(), user);
        }

        //quote
        if(args[0].equalsIgnoreCase(Info.PREFIX + "quote") && !event.getMember().getUser().isBot()){
            if (args.length <= 1) {
                MessageHistory mh = event.getGuild().getTextChannelById("490328336707485698").getHistory();
                String quote = mh.retrievePast(57).complete().get(getRandomNumberInRange(1, 56)).getContentRaw();
                Logs.sendQuote(event.getMessage().getTextChannel(), user, quote);
            }else{
                Logs.sendErrorMessageQuote(event.getMessage().getTextChannel(), user);
            }
        }

        //Blackjack
        if(args[0].equalsIgnoreCase(Info.PREFIX + "bstart") && !event.getMember().getUser().isBot()){
            inGame = true;
            Logs.sendBlackJackStart(event.getMessage().getTextChannel(), event.getMember());
            player = user;
        }
        if(inGame && !event.getMember().getUser().isBot() && event.getMember().equals(player)){
            if(didTheyDraw) {
                if(args[0].equalsIgnoreCase(Info.PREFIX + "stay") && args.length <= 1) {
                    while (botDraw <= 17) {
                        botDraw += getRandomNumberInRange(1, 10);
                    }
                    compareDraw(event.getMessage().getTextChannel(), user);

                }
                if (args[0].equalsIgnoreCase(Info.PREFIX + "hit")) {
                    newDraw = getRandomNumberInRange(1, 10);
                    oldDraw += newDraw;
                    draw = oldDraw + newDraw;
                    compareDraw21(event.getMessage().getTextChannel(), user);
                    compareBot(event.getMessage().getTextChannel(), user);
                    if(inGame) {
                        Logs.sendBlackJackHit(event.getMessage().getTextChannel(), event.getMember(), newDraw, oldDraw);
                    }
                }
            }/*else{
                System.out.println("this one tho");
                Logs.sendErrorMessageWink(event.getMessage().getTextChannel(), user);
            }*/
            if(args[0].equalsIgnoreCase(Info.PREFIX + "draw") && !didTheyDraw) {
                if (args.length <= 1) {
                    draw = getRandomNumberInRange(1, 10);
                    //botDraw = getRandomNumberInRange(1, 10);
                    Logs.sendBlackJackDraw(event.getMessage().getTextChannel(), event.getMember(), draw);
                    didTheyDraw = true;
                    oldDraw = draw;
                } else {

                    Logs.sendErrorMessageWink(event.getMessage().getChannel(), user);
                }
            }
        }
    }

    private void compareDraw21(TextChannel channel, Member user){
        if(oldDraw == 21){
            Logs.sendBlackJackWin21(channel, user, oldDraw);
            finishBlackjack();
        }else if(oldDraw > 21){
            Logs.sendBlackJackLoss(channel, user, oldDraw, botDraw);
            finishBlackjack();
        }
    }

    private void compareBot(TextChannel channel, Member user){
        if(botDraw > 21){
            Logs.sendBlackJackWinBot(channel, user, oldDraw);
        }
        if(!(botDraw > 17 && botDraw < 21)){
            botDraw += getRandomNumberInRange(1, 10);
        }
        if(botDraw == 21) {
            Logs.sendBlackJackLoss21(channel, user, oldDraw);
            finishBlackjack();
        }
    }

    private void compareDraw(TextChannel channel, Member user){
        if(oldDraw > 21){
            Logs.sendBlackJackLoss(channel, user, oldDraw, botDraw);
            finishBlackjack();
        }else if(oldDraw > botDraw){
            Logs.sendBlackJackWin(channel, user, oldDraw, botDraw);
            finishBlackjack();
        }else if(botDraw > oldDraw){
            Logs.sendBlackJackLoss(channel, user, oldDraw, botDraw);
            finishBlackjack();
        }
    }

    private void finishBlackjack(){
        didTheyDraw = false;
        inGame = false;
        oldDraw = 0;
        newDraw = 0;
        draw = 0;
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
