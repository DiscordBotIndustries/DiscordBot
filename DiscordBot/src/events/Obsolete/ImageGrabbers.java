

//Note: this file became obsolete as of 10/27/18



/*package events;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;

import java.awt.*;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class ImageGrabbers
{
    private File[] fa;
    private int random;
    private File f;
    private File temp;

    public ImageGrabbers()
    {
        String s = "/Users/cmonesmith20/Desktop/Memes2";
        f = new File(s);

        Boolean b;
        b = f.isDirectory();

        fa = f.listFiles();

        for (int i=0; i< fa.length; i++) {
           // System.out.println(fa[i]);
        }

        random = (int) ((Math.random() * fa.length) + 1);

        temp = fa[random];
    }

    public void grabImage(TextChannel c, Member member) {

        Message m = new MessageBuilder().append("Awful Meme:").build();
        c.sendFile(temp, m).queue();
    }
}*/