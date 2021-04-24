package de.labymod.lennart.listener;

import de.labymod.lennart.TimoliaAddon;
import net.labymod.api.events.TabListEvent;
import net.minecraft.client.Minecraft;

import java.util.List;


public class TablistHeaderMapListener implements TabListEvent {

    @Override
    public void onUpdate(Type type, String header, String s1) {
        if (containsTimoliaServer(header)) {
            Minecraft.func_71410_x().field_71439_g.func_71165_d("/v");
            TimoliaAddon.getINSTANCE().setMapAnswer(true);
        }
        MessageMapReceiveListener.latestMap = null;
    }

    private boolean containsTimoliaServer(String gamemode, String s) {
        return s.contains("§r§7 Du spielst auf §r§6§o" + gamemode);
    }

    private boolean containsTimoliaServer(String s) {
        List<String> serverList = TimoliaAddon.getINSTANCE().getAddonConfig().getServers();
        for (String server : serverList) {

            if (containsTimoliaServer(server, s)) {
                return true;
            }
        }
        return false;
    }

}