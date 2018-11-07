package events.Risk;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Characters {

    private ArrayList characters = new ArrayList();
    private ArrayList nameArray;
    private ArrayList player;
    ArrayList<ArrayList<String>> md = new ArrayList<ArrayList<String>>();

    public Characters(){

    }

    public void startGame(String name1, String name2, String name3, String name4, TextChannel tc){
        nameArray = new ArrayList();
        nameArray.add(name1);
        nameArray.add(name2);
        nameArray.add(name3);
        nameArray.add(name4);

        md.add(nameArray);

        /*for(ArrayList<String> r : md){
            for(int i = 0;i < r.size(); i++){
                System.out.println(r.get(i) + "\t");
            }
        }
        addSoldiers(characters, tc);*/

        nameArray = new ArrayList();
        nameArray.add(name1);
        nameArray.add(name2);
        nameArray.add(name3);
        nameArray.add(name4);

        for(int i = 0; i < 4; i++) {
            player = new ArrayList();
            player.add(nameArray.get(i));
            characters.add(player);
        }
        addSoldiers(characters, tc);
    }

    public void addSoldiers(ArrayList characters, TextChannel tc){


        int startSoldiers = 10000;
        int i = 0;
        ArrayList temp = null;

        while(i < characters.size()){

                temp = (ArrayList) characters.get(i);
                temp.add(startSoldiers);
                temp = new ArrayList();
                player.clear();

                player.add(temp);
                i++;

            /*temp = new ArrayList();
            player.clear();
            
            player.add(temp);
            i++;*/
        }


        System.out.println(characters.toString());
        logStartRisk(characters, tc);
    }

    public void getRandomSoldiers(Member user){
        Random r = new Random();
        int Low = 100;
        int High = 1000;
        int Result = r.nextInt(High-Low) + Low;
        int newSoldiers = Result;
        int oldSoldiers;
        int i = 0;
        ArrayList temp;
        System.out.println("happening at least");

        while(i < 3){
            temp = new ArrayList();
            temp = (ArrayList) characters.get(i);
            oldSoldiers = (int) player.get(1);
            temp.add(newSoldiers + oldSoldiers);
            player.clear();
            player.add(temp);
            i++;
        }
    }



    public void logStartRisk(ArrayList characters, TextChannel tc){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder ebl = new EmbedBuilder();
        ebl.setTitle("You Have Started a Risk Game!");
        ebl.setColor(Color.decode("#FF0000"));
        ebl.addField("Player 1: ", String.valueOf(characters.get(0)), false);
        ebl.addField("Player 2: ", String.valueOf(characters.get(1)), false);
        ebl.addField("Player 3: ", String.valueOf(characters.get(2)), false);
        ebl.addField("Player 4: ", String.valueOf(characters.get(3)), false);
        tc.sendMessage(ebl.build()).queue();
    }
}



/*public void getNation(TextChannel tc, ArrayList characters){

        Nations n = new Nations();
        String nation;
        int i = 0;
        ArrayList temp;

        while(i < 3){
            //nation = n.getNation();
            //temp = new ArrayList();
            temp = (ArrayList) characters.get(i);
            //temp.add(nation);
            player.clear();
            player.add(temp);
            i++;
        }
        logStartRisk(characters, tc);
        System.out.println(characters);
    }*/
