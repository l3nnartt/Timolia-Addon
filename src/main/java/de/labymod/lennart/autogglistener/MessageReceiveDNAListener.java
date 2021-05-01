package de.labymod.lennart.autogglistener;

import de.labymod.lennart.TimoliaAddon;
import net.labymod.api.events.MessageReceiveEvent;
import net.minecraft.client.Minecraft;

public class MessageReceiveDNAListener implements MessageReceiveEvent {
    @Override
    public boolean onReceive(String s, String s1) {

        if (!TimoliaAddon.getInstance().isEnabledAutoGGDNA()) return false;

        if (TimoliaAddon.getInstance().isDna()) {
            if (s1.contains("Noch") && s1.contains("bis zum nächsten Rang!")) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage(TimoliaAddon.getInstance().getGameDNA());
            }
        }
        return false;
    }
}