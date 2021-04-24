package de.labymod.lennart.autogglistener;

import de.labymod.lennart.TimoliaAddon;
import net.labymod.api.events.MessageReceiveEvent;
import net.minecraft.client.Minecraft;

public class MessageReceiveCastlesListener implements MessageReceiveEvent {
    @Override
    public boolean onReceive(String s, String s1) {

        if (!TimoliaAddon.getINSTANCE().isEnabledAutoGGCastles()) return false;

        if (TimoliaAddon.getINSTANCE().isCastles()) {
            if (s1.contains("Noch") && s1.contains("bis zum nächsten Rang!")) {
                Minecraft.func_71410_x().field_71439_g.func_71165_d(TimoliaAddon.getINSTANCE().getGameCastles());
            }
        } return false;

    }
}