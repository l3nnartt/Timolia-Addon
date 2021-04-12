package de.labymod.lennart.autogglistener;

import de.labymod.lennart.addon;
import net.labymod.api.events.MessageReceiveEvent;
import net.minecraft.client.Minecraft;

public class MessageRecive1vs1Listener implements MessageReceiveEvent {
    @Override
    public boolean onReceive(String s, String s1) {

        if (!addon.INSTANCE.enabledAutoGG1vs1) return false;
        if (s1.contains("1vs1") && s1.contains("»")) {
            if (s1.contains("in Folge") || s1.contains("hat den Kampf gegen")) return false;
                if (s1.contains("gewonnen")) {
                    Minecraft.func_71410_x().field_71439_g.func_71165_d(addon.INSTANCE.win1vs1);
                    addon.INSTANCE.killstreak ++;
                } else if (s1.contains("verloren")) {
                    Minecraft.func_71410_x().field_71439_g.func_71165_d(addon.INSTANCE.lose1vs1);
                    addon.INSTANCE.killstreak = 0;
                }
        } return false;

    }
}