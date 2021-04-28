package de.labymod.lennart.group;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import de.labymod.lennart.TimoliaAddon;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class GroupManager {

    public GroupManager() {
        fetchAndSet();
    }

    private void fetchAndSet() {
        TimoliaAddon.getINSTANCE().getExService().execute(() -> {
            try {
                String team = getURLContent("http://karmatop.de/addon/timoliateam.json");
                JsonArray object = new JsonParser().parse(team).getAsJsonArray();
                object.forEach(jsonElement -> {
                    String uuid = jsonElement.getAsString();
                    TimoliaAddon.getINSTANCE().setGroup(UUID.fromString(uuid));
                    TimoliaAddon.getINSTANCE().getCachedTimoliaTeam().put(UUID.fromString(uuid), false);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private String getURLContent(String url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) (new URL(url)).openConnection();
        con.setConnectTimeout(5000);
        con.connect();
        return IOUtils.toString(con.getInputStream(), "UTF-8");
    }

}