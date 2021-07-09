package com.github.l3nnartt.pxlspace;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.github.l3nnartt.TimoliaAddon;
import net.labymod.api.events.MessageSendEvent;
import net.labymod.main.LabyMod;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class MessageSendStats implements MessageSendEvent {

    String player = null;

    @Override
    public boolean onSend(final String message) {
        if (TimoliaAddon.getInstance().isEnabledPxlSpaceStats()) {
            if (!TimoliaAddon.getInstance().isPixelspace()) return false;
            String[] args = message.split(" ");
            if (message.startsWith("/stats")) {
                if (args.length > 1) {
                    player = args[1];
                } else {
                    player = LabyMod.getInstance().getPlayerName();
                }
                if (TimoliaAddon.getInstance().getAuthenticator().authenticate()) {
                    TimoliaAddon.getInstance().getExService().execute(() -> {
                        try {
                            JsonArray content = getURLContent("http://karmatop.de/addon/pxl-api.php?name=" + player).getAsJsonArray();
                            createStats(content);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            } return message.startsWith("/stats");
        } return false;
    }

    public JsonElement getURLContent(String url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) (new URL(url)).openConnection();
        con.setConnectTimeout(5000);
        con.connect();
        String jsonString = IOUtils.toString(con.getInputStream(), StandardCharsets.UTF_8);
        JsonParser parser = new JsonParser();
        return  parser.parse(jsonString);
    }

    public void createStats(JsonArray content) {
        JsonElement stats = content.get(0);
        LabyMod.getInstance().displayMessageInChat("§6░▒▓§bStats von " + player + " §6▓▒░");
        LabyMod.getInstance().displayMessageInChat("§6╔═════");
        for (Map.Entry<String, JsonElement> entry : stats.getAsJsonObject().entrySet()) {
            if (!entry.getKey().equals("total") && !entry.getKey().equals("name") && !entry.getKey().equals("uuid")) {
                LabyMod.getInstance().displayMessageInChat("§6╠ §" + convertColor(entry.getKey()) + "✦ §f" + capitalize(entry.getKey()) + ": §7" + entry.getValue().getAsString());
            }
        }
        LabyMod.getInstance().displayMessageInChat("§6╚═════");
    }

    public String convertColor(String name){
        String colorCode = "f";
        switch (name){
            case "brown":
            case "orange":
                colorCode = "6";
                break;
            case "magenta":
            case "purple":
                colorCode = "5";
                break;
            case "lightBlue":
                colorCode = "b";
                break;
            case "yellow":
                colorCode = "e";
                break;
            case "lime":
                colorCode = "a";
                break;
            case "pink":
                colorCode = "d";
                break;
            case "gray":
                colorCode = "8";
                break;
            case "silver":
                colorCode = "7";
                break;
            case "cyan":
                colorCode = "3";
                break;
            case "blue":
                colorCode = "9";
                break;
            case "green":
                colorCode = "2";
                break;
            case "red":
                colorCode = "c";
                break;
            case "black":
                colorCode = "0";
                break;
            default:
                colorCode = "f";
        }
        return colorCode;
    }
    public static String capitalize(String str){
        if(str == null) {
            return null;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}