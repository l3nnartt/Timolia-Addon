package de.labymod.lennart.karmatop;

import de.labymod.lennart.TimoliaAddon;
import net.labymod.api.events.MessageReceiveEvent;
import net.labymod.main.LabyMod;

import java.net.HttpURLConnection;
import java.net.URL;

public class KarmaListener implements MessageReceiveEvent {

    public static String karma = null;
    public String playerName = LabyMod.getInstance().getPlayerName();
    
    @Override
    public boolean onReceive(String s, String strippedMessage) {
        if (strippedMessage.contains("Erfolgspunkte:") && strippedMessage.contains("│")) {
            String karmapunkte = s.split("§6")[1].split("§r")[0];
            karma = karmapunkte;

            System.out.println(karma);
            System.out.println(playerName);
            TimoliaAddon.getINSTANCE().getAuthenticator().authenticate();

            TimoliaAddon.getINSTANCE().getExService().execute(() -> {
                try {
                    HttpURLConnection con = (HttpURLConnection) (new URL(
                            "http://karmatop.de/addon/auth.php?name=" + LabyMod.getInstance().getLabyModAPI().getPlayerUsername() + "&karma=" + karma + "&uuid=" + LabyMod.getInstance().getLabyModAPI().getPlayerUUID())).openConnection();
                    con.connect();
                    int code = con.getResponseCode();
                    if (code == 200) {
                        System.out.println("HDCapes » PING SUCCESFULLY");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            if (TimoliaAddon.getINSTANCE().isKarmaAnswer()) {
                TimoliaAddon.getINSTANCE().setKarmaAnswer(false);
                return false;
            }

        } return false;

    }

}