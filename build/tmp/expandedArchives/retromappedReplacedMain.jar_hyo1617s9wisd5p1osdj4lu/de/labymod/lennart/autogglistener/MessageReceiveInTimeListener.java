package de.labymod.lennart.autogglistener;

import de.labymod.lennart.TimoliaAddon;
import net.labymod.api.events.MessageReceiveEvent;
import net.minecraft.client.Minecraft;

public class MessageReceiveInTimeListener implements MessageReceiveEvent {
    @Override
    public boolean onReceive(String s, String s1) {

        if (!TimoliaAddon.getINSTANCE().isEnabledAutoGGInTime()) return false;

        if (TimoliaAddon.getINSTANCE().isIntime()) {
            if (s1.contains("Noch") && s1.contains("bis zum nächsten Rang!")) {
                Minecraft.func_71410_x().field_71439_g.func_71165_d(TimoliaAddon.getINSTANCE().getGameInTime());
            }
        } return false;

    }
}