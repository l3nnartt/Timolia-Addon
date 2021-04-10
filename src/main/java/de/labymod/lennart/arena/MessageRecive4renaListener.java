package de.labymod.lennart.arena;

import de.labymod.lennart.addon;
import net.labymod.api.events.MessageReceiveEvent;
import net.minecraft.client.Minecraft;

public class MessageRecive4renaListener implements MessageReceiveEvent {
    @Override
    public boolean onReceive(String s, String s1) {

        if (!addon.INSTANCE.enabledAutoGG4rena) return false;

        if (s1.contains("4rena") && s1.contains("»")) {
            if (s1.contains("Team") && s1.contains("gewinnt diese Runde")) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("@" + addon.INSTANCE.match4rena);
            }
            else if (s1.contains("gewinnt diese Runde")) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage(addon.INSTANCE.match4rena);
            }
        }

        if (s1.contains("Noch") && s1.contains("bis zum nächsten Rang!")) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage(addon.INSTANCE.game4rena);
        }
        return false;
    }
}