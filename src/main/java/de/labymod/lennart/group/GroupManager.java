package de.labymod.lennart.group;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import de.labymod.lennart.LabyGroup;
import net.labymod.main.LabyMod;
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
        try {
            String team = getURLContent("http://karmatop.de/addon/vip.json");
            JsonArray object = new JsonParser().parse(team).getAsJsonArray();
            object.forEach(jsonElement -> {
                String uuid = jsonElement.getAsString();
                setGroup(UUID.fromString(uuid));
                LabyGroup.getInstance().getCachedVIP().put(UUID.fromString(uuid), false);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private net.labymod.user.group.LabyGroup getCustomGroup(int id, String name, char colorChar, Color color) {
        return new CustomGroup(id, name, colorChar, color);
    }

    public void setGroup(UUID uuid) {
        if (LabyGroup.getInstance().getGroup() == null)
            LabyGroup.getInstance().setGroup(getCustomGroup(164, "VIP", '5', new Color(1)));
        LabyMod.getInstance().getUserManager().getUser(uuid).setGroup(LabyGroup.getInstance().getGroup());
    }

    private String getURLContent(String url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) (new URL(url)).openConnection();
        con.setConnectTimeout(5000);
        con.connect();
        return IOUtils.toString(con.getInputStream(), "UTF-8");
    }
}