package de.labymod.lennart.autogglistener;

import de.labymod.lennart.TimoliaAddon;
import net.labymod.api.events.MessageReceiveEvent;
import net.minecraft.client.Minecraft;

public class MessageReceive1vs1Listener implements MessageReceiveEvent {
    @Override
    public boolean onReceive(String s, String s1) {
        if (!TimoliaAddon.getINSTANCE().isEnabledAutoGG1vs1()) return false;

        if (s1.contains("1vs1") && s1.contains("»")) {
            if (s1.contains("in Folge") || s1.contains("hat den Kampf gegen")) return false;
                if (s1.contains("gewonnen")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage(TimoliaAddon.getINSTANCE().getWin1vs1());
                    TimoliaAddon.getINSTANCE().setKillstreak(+1);
                } else if (s1.contains("verloren")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage(TimoliaAddon.getINSTANCE().getLose1vs1());
                    TimoliaAddon.getINSTANCE().setKillstreak(0);
                }
        } return false;

    }
}