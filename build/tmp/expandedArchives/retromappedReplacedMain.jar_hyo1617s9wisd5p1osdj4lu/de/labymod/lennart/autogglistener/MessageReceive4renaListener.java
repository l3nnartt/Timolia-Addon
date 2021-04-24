package de.labymod.lennart.autogglistener;

import de.labymod.lennart.TimoliaAddon;
import net.labymod.api.events.MessageReceiveEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;

public class MessageReceive4renaListener implements MessageReceiveEvent {
    @Override
    public boolean onReceive(String s, String s1) {

        if (!TimoliaAddon.getINSTANCE().isArena()) return false;
        if (Minecraft.func_71410_x().field_71439_g.field_71071_by.func_146028_b(Item.func_150899_d(345))) return false;
        if (!TimoliaAddon.getINSTANCE().isEnabledAutoGG4rena()) return false;

        if (s1.contains("4rena") && s1.contains("»")) {
            if (s1.contains("Team") && s1.contains("gewinnt diese Runde")) {
                Minecraft.func_71410_x().field_71439_g.func_71165_d("@" + TimoliaAddon.getINSTANCE().getMatch4rena());
            }
            else if (s1.contains("gewinnt diese Runde")) {
                Minecraft.func_71410_x().field_71439_g.func_71165_d(TimoliaAddon.getINSTANCE().getMatch4rena());
            }
        }

        if (TimoliaAddon.getINSTANCE().isArena()) {
            if (s1.contains("Noch") && s1.contains("bis zum nächsten Rang!")) {
                Minecraft.func_71410_x().field_71439_g.func_71165_d(TimoliaAddon.getINSTANCE().getGame4rena());
            }
        } return false;
        
    }
}