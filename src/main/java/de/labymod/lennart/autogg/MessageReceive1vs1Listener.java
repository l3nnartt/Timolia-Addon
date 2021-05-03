package de.labymod.lennart.autogg;

import de.labymod.lennart.TimoliaAddon;
import net.labymod.api.events.MessageReceiveEvent;
import net.minecraft.client.Minecraft;

public class MessageReceive1vs1Listener implements MessageReceiveEvent {
    @Override
    public boolean onReceive(String s, String s1) {
        if (!TimoliaAddon.getInstance().isEnabledAutoGG1vs1()) return false;

        if (s1.contains("1vs1") && s1.contains("»")) {
            if (s1.contains("in Folge") || s1.contains("hat den Kampf gegen")) return false;
                if (s1.contains("gewonnen")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage(TimoliaAddon.getInstance().getWin1vs1());
                    //TimoliaAddon.getInstance().setKillstreak(+1);
                } else if (s1.contains("verloren")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage(TimoliaAddon.getInstance().getLose1vs1());
                    //TimoliaAddon.getInstance().setKillstreak(0);
                }
        }
        return false;
    }
}