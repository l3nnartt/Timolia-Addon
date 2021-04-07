package de.labymod.lennart.listener;

import de.labymod.lennart.addon;
import net.labymod.api.events.MessageReceiveEvent;
import net.minecraft.client.Minecraft;

public class MessageReceiveListener implements MessageReceiveEvent {

    @Override
    public boolean onReceive(String s, String s1) {
        if (!addon.INSTANCE.enabledAutoGG1vs1) return false;
        if (s1.contains("»") && s1.contains("1vs1") || s1.contains("Splun")) {
            if (s1.contains("gewonnen")) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage(addon.INSTANCE.win1vs1);
            } else if (s1.contains("verloren")) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage(addon.INSTANCE.lose1vs1);
            }
        } return false;
    }

}