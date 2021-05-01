package de.labymod.lennart.group;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import de.labymod.lennart.TimoliaAddon;
import net.labymod.main.LabyMod;
import net.labymod.user.group.LabyGroup;
import org.apache.commons.io.IOUtils;

import java.awt.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class GroupManager {

    public GroupManager() {
        fetchAndSet();
    }

    private void fetchAndSet() {
        TimoliaAddon.getInstance().getExService().execute(() -> {
            try {
                String team = getURLContent("http://karmatop.de/addon/timoliateam.json");
                JsonArray object = new JsonParser().parse(team).getAsJsonArray();
                object.forEach(jsonElement -> {
                    String uuid = jsonElement.getAsString();
                    setGroup(UUID.fromString(uuid));
                    TimoliaAddon.getInstance().getCachedTimoliaTeam().put(UUID.fromString(uuid), false);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private LabyGroup getCustomGroup(int id, String name, char colorChar, Color color) {
        return new CustomGroup(id, name, colorChar, color);
    }

    public void setGroup(UUID uuid) {
        if (TimoliaAddon.getInstance().isEnabledTeamBadge()) {
            if (TimoliaAddon.getInstance().getGroup() == null)
                TimoliaAddon.getInstance().setGroup(getCustomGroup(145, "TimoliaTeam", '3', new Color(1)));
            LabyMod.getInstance().getUserManager().getUser(uuid).setGroup(TimoliaAddon.getInstance().getGroup());
        }
    }

    private String getURLContent(String url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) (new URL(url)).openConnection();
        con.setConnectTimeout(5000);
        con.connect();
        return IOUtils.toString(con.getInputStream(), "UTF-8");
    }
}