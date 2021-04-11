package de.labymod.lennart.listener;

import de.labymod.lennart.addon;
import net.labymod.api.events.MessageReceiveEvent;
import net.minecraft.client.Minecraft;

public class MessageReceiveSplunListener implements MessageReceiveEvent {
    @Override
    public boolean onReceive(String s, String s1) {

        if (!addon.INSTANCE.enabledAutoGGSplun) return false;

        if (addon.INSTANCE.splun) {
            if (s1.contains("Noch") && s1.contains("bis zum nächsten Rang!")) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage(addon.INSTANCE.gameSplun);
            }
        } return false;

    }
}
