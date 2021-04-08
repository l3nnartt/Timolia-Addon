package de.labymod.lennart.listener;

import de.labymod.lennart.addon;
import net.labymod.api.events.MessageReceiveEvent;

public class MessageMapReceiveListener implements MessageReceiveEvent {

    public static String latestMap = null;

    @Override
    public boolean onReceive(String s, String s1) {
        if (s1.contains("Mapvoting") && s1.contains("»") && s1.contains("beendet")) {
            String[] mapname = s.split("§6");
            String mapoutput = mapname[mapname.length-1];
            mapoutput = mapoutput.substring(0,mapoutput.length()-5);
            latestMap = mapoutput;

            if (addon.INSTANCE.mapAnswer) {
                addon.INSTANCE.mapAnswer = false;
                return true;
            }
        }
        return false;
    }
}