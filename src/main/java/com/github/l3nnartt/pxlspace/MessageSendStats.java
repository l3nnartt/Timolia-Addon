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

public class MessageSendStats implements MessageSendEvent {

    @Override
    public boolean onSend(final String message) {
        if (TimoliaAddon.getInstance().isEnabledPxlSpaceStats()) {
            if (!TimoliaAddon.getInstance().isPixelspace()) return false;
            String[] args = message.split(" ");
            if (message.equalsIgnoreCase("/stats")) {
                if (TimoliaAddon.getInstance().getAuthenticator().authenticate()) {
                    TimoliaAddon.getInstance().getExService().execute(() -> {
                        try {
                            JsonArray content = getURLContent("http://karmatop.de/addon/pxl-api.php?name=" + LabyMod.getInstance().getPlayerName()).getAsJsonArray();
                            for (JsonElement jsonElement : content) {
                                LabyMod.getInstance().displayMessageInChat("§1│ §7" + jsonElement);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            } return message.equalsIgnoreCase("/stats");
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

}