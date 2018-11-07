package events.Risk;


import java.util.*;

public class Nations {

    private int nation;
    private String[] nationNames = new String[]{"UnitedStates", "SouthAmerica", "GreatBritain", "Asia", "Africa", "Europe"};
    private String yourNation;
    private int max = 5;
    private int random;

    /*public String getNation() {
        nation = (int) (Math.random() * 6)+ 1;

        if(nation == 1){
            yourNation = nationNames[0];
        }else if(nation == 2){
            yourNation = nationNames[1];
        }else if(nation == 3){
            yourNation = nationNames[2];
        }else if(nation == 4){
            yourNation = nationNames[3];
        }else if(nation == 5){
            yourNation = nationNames[4];
        }else if(nation == 6){
            yourNation = nationNames[5];
        }
        return yourNation;
    }*/

    public void setNation(int nation) {
        this.nation = nation;
    }

    /*public Nations() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= 10; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i = 0; i < 5; i++) {
            nation = list.get(i);
        }
    }*/
}
