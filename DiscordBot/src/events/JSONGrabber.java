package events;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

//import com.google.gson.annotations.SerializedName;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONGrabber {

    private JSONObject json;
    private int random = getRandomNumberInRange(3, 25);
    private ArrayList<String> hotOrNot = new ArrayList<>();

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        URL is = new URL(url);
        HttpURLConnection myURLConnection = (HttpURLConnection) is.openConnection();
        String userCredentials = "Mozzila/5.0";
        //String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));

        myURLConnection.setRequestProperty("User-Agent", userCredentials);
        BufferedReader rd = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream(), Charset.forName("UTF-8")));
        String jsonText = readAll(rd);
        json = new JSONObject(jsonText);
        //System.out.println(json.getJSONObject("data").getJSONObject("children"));
        json = new JSONObject(jsonText.trim());
        Iterator<String> keys = json.keys();

        return json;
    }

    public String grabRedditLink() throws IOException, JSONException {
        Collections.addAll(hotOrNot, "hot", "new");
        String hotMaybe = hotOrNot.get(getRandomNumberInRange(0,1));
        json = readJsonFromUrl("https://www.reddit.com/r/MemeEconomy/" + hotMaybe + ".json");

        return getLink();
    }

    public String grabRedditLinkWink() throws IOException, JSONException {
        Collections.addAll(hotOrNot, "hot", "new");
        String hotMaybe = hotOrNot.get(getRandomNumberInRange(0,1));
        json = readJsonFromUrl("https://www.reddit.com/r/porn/" + hotMaybe + ".json");

        return getLink();
    }

    public String grabRedditLink(String subReddit) throws IOException, JSONException {
        Collections.addAll(hotOrNot, "hot", "new");
        String hotMaybe = hotOrNot.get(getRandomNumberInRange(0,1));
        json = readJsonFromUrl("https://www.reddit.com/r/" + subReddit + "/" + hotMaybe + ".json");
        return getLink();
    }

    public String getLink() throws IOException {

        String urlString = json.getJSONObject("data").getJSONArray("children").getJSONObject(random).getJSONObject("data").getString("url");
        String sub = urlString;
        if(truncate(sub, 19).equals("https://i.imgur.com")){
            //System.out.println("imgur");
        }
        System.out.println(urlString);

        return urlString;
    }

    public boolean getWhitlist(){
        boolean whitlist = false;
        try {
            whitlist = json.getJSONObject("data").getJSONArray("children").getJSONObject(random).getJSONObject("data").getBoolean("over_18");
        }catch (NullPointerException e){
            System.out.println("failed");
        }

        return whitlist;
    }

    public String getSubreddit(){

        String subredditString = json.getJSONObject("data").getJSONArray("children").getJSONObject(random).getJSONObject("data").getString("subreddit");
        return subredditString;
    }

    public String getTitle(){

        String title = json.getJSONObject("data").getJSONArray("children").getJSONObject(random).getJSONObject("data").getString("title");
        return title;
    }

    public String getSelftext(){

        String selftext = json.getJSONObject("data").getJSONArray("children").getJSONObject(random).getJSONObject("data").getString("selftext");
        return selftext;
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static String truncate(String value, int length)
    {
        if (value != null && value.length() > length)
            value = value.substring(0, length);
        return value;
    }
}