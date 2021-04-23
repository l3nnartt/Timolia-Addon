package de.labymod.lennart.karmatop;

import de.labymod.lennart.addon;
import net.labymod.api.events.MessageReceiveEvent;
import net.minecraft.client.Minecraft;

public class KarmaListener implements MessageReceiveEvent {

    public static String karma = null;
    //String playerName = Minecraft.getMinecraft().thePlayer.getName();
    
    @Override
    public boolean onReceive(String s, String strippedMessage) {
        if (strippedMessage.contains("Erfolgspunkte:") && strippedMessage.contains("│")) {
            String karmapunkte = s.split("§6")[1].split("§r")[0];
            karma = karmapunkte;

            System.out.println(karma);
            //System.out.println(playerName);

            if (addon.INSTANCE.karmaAnswer) {
                addon.INSTANCE.karmaAnswer = false;
                return false;
            }

        } return false;

    }

}