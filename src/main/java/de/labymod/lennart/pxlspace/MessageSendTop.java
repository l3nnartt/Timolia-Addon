package de.labymod.lennart.pxlspace;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.labymod.lennart.TimoliaAddon;
import net.labymod.api.events.MessageSendEvent;
import net.labymod.main.LabyMod;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MessageSendTop implements MessageSendEvent {

    @Override
    public boolean onSend(String message) {
        if (TimoliaAddon.getInstance().isEnabledPxlSpaceStats()) {
            if (!TimoliaAddon.getInstance().isPixelspace()) return false;
            if (message.equalsIgnoreCase("/top")) {

                LabyMod.getInstance().displayMessageInChat("§1│ §9Timolia-Addon§1» §7Die Top 10 Spieler in PxlSpace mit dem Timolia Addon");

                if (TimoliaAddon.getInstance().getAuthenticator().authenticate()) {
                    TimoliaAddon.getInstance().getExService().execute(() -> {
                        try {
                            JsonObject content = getURLContent("http://hosting151773.a2e37.netcup.net/lennart/timolia/addon/pxl-top.php").getAsJsonObject();
                            for (JsonElement jsonElement : content.getAsJsonArray()) {
                                String name = jsonElement.getAsJsonObject().get("name").getAsString();
                                String total = jsonElement.getAsJsonObject().get("total").getAsString();
                                LabyMod.getInstance().displayMessageInChat("1│ &7" + name + " mit " + total + "Blöcken");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }

            } return message.equalsIgnoreCase("/top");
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



