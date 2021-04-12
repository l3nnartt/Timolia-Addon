package de.labymod.lennart.autogglistener;

import de.labymod.lennart.addon;
import net.labymod.api.events.MessageReceiveEvent;
import net.minecraft.client.Minecraft;

public class MessageRecive4renaListener implements MessageReceiveEvent {
    @Override
    public boolean onReceive(String s, String s1) {

        if (Minecraft.func_71410_x().field_71439_g.field_71075_bZ.field_75101_c) return false;

        if (!addon.INSTANCE.enabledAutoGG4rena) return false;

        if (s1.contains("4rena") && s1.contains("»")) {
            if (s1.contains("Team") && s1.contains("gewinnt diese Runde")) {
                Minecraft.func_71410_x().field_71439_g.func_71165_d("@" + addon.INSTANCE.match4rena);
            }
            else if (s1.contains("gewinnt diese Runde")) {
                Minecraft.func_71410_x().field_71439_g.func_71165_d(addon.INSTANCE.match4rena);
            }
        }

        if (addon.INSTANCE.arena) {
            if (s1.contains("Noch") && s1.contains("bis zum nächsten Rang!")) {
                Minecraft.func_71410_x().field_71439_g.func_71165_d(addon.INSTANCE.game4rena);
            }
        } return false;
        
    }
}