package de.labymod.lennart.karmatop;

import de.labymod.lennart.addon;
import net.labymod.api.events.TabListEvent;
import net.minecraft.client.Minecraft;

public class KarmaTablistListener implements TabListEvent {

    @Override
    public void onUpdate(Type type, String header, String s1) {
        System.out.println("server gewechselt");
        Minecraft.func_71410_x().field_71439_g.func_71165_d("/karma");
        addon.INSTANCE.karmaAnswer = true;
        System.out.println("karma message sended");
    }

}