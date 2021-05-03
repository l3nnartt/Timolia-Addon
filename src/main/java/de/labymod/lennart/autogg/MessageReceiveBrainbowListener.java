package de.labymod.lennart.autogg;

import de.labymod.lennart.TimoliaAddon;
import net.labymod.api.events.MessageReceiveEvent;
import net.minecraft.client.Minecraft;

public class MessageReceiveBrainbowListener implements MessageReceiveEvent {
    @Override
    public boolean onReceive(String s, String s1) {

        if (!TimoliaAddon.getInstance().isEnabledAutoGGBrainbow()) return false;

        if (TimoliaAddon.getInstance().isBrainbow()) {
            if (s1.contains("Noch") && s1.contains("bis zum nächsten Rang!")) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage(TimoliaAddon.getInstance().getGameBrainbow());
            }
        }
        return false;
    }
}