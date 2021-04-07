package de.labymod.lennart.listener;

import net.labymod.api.events.MessageReceiveEvent;
import net.minecraft.client.Minecraft;

public class MessageReceiveListener implements MessageReceiveEvent {

    @Override
    public boolean onReceive(String s, String s1) {
        if (s1.contains("»") && s1.contains("1vs1")) {
            if (s1.contains("gewonnen")) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("gg");
            } else if (s1.contains("verloren")) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("gg");
            }
        }
        return false;
    }
}