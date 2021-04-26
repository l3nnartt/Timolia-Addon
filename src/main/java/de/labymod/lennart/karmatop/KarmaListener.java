package de.labymod.lennart.karmatop;

import de.labymod.lennart.TimoliaAddon;
import net.labymod.api.events.MessageReceiveEvent;
import net.labymod.main.LabyMod;
import java.net.HttpURLConnection;
import java.net.URL;

public class KarmaListener implements MessageReceiveEvent {

    public static String karma = null;
    
    @Override
    public boolean onReceive(String s, String strippedMessage) {
        if (strippedMessage.contains("Erfolgspunkte") && strippedMessage.contains("│") && strippedMessage.contains(":")) {
            String karmapunkte = s.split("§6")[1].split("§r")[0];
            karma = karmapunkte;

            if (TimoliaAddon.getINSTANCE().getAuthenticator().authenticate()) {
                TimoliaAddon.getINSTANCE().getExService().execute(() -> {
                    try {
                        HttpURLConnection con = (HttpURLConnection) (new URL(
                                "http://karmatop.de/addon/auth.php?name=" + LabyMod.getInstance().getLabyModAPI().getPlayerUsername() + "&karma=" + karma + "&uuid=" + LabyMod.getInstance().getLabyModAPI().getPlayerUUID())).openConnection();
                        con.setRequestProperty("User-Agent",
                                "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
                        con.connect();
                        int code = con.getResponseCode();
                        if (code == 200) {
                            System.out.println("Timolia Addon » Karma gesendet");
                        } else {
                            System.out.println(code);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            if (TimoliaAddon.getINSTANCE().isKarmaAnswer()) {
                TimoliaAddon.getINSTANCE().setKarmaAnswer(false);
                return true;
            }

        } return false;

    }

}