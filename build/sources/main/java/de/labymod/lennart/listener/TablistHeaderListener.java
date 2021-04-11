package de.labymod.lennart.listener;

import de.labymod.lennart.addon;
import net.labymod.api.events.TabListEvent;
import net.minecraft.client.Minecraft;

import java.util.List;

public class TablistHeaderListener implements TabListEvent {
    @Override
    public void onUpdate(Type type, String header, String s1) {
        if (containsTimoliaServer(header)) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/v");
            addon.INSTANCE.mapAnswer = true;
        }
        MessageMapReceiveListener.latestMap = null;

        if (header.contains("pxlspace")) {
            addon.INSTANCE.pixelspace = true;
        }
    }

    private boolean containsTimoliaServer(String gamemode, String s) {
        return s.contains("§r§7 Du spielst auf §r§6§o" + gamemode);
    }

    private boolean containsTimoliaServer(String s) {
        List<String> serverList = addon.INSTANCE.addonConfig.getServers();
        for (String server : serverList) {

            if (containsTimoliaServer(server, s)) {
                return true;
            }
        }
        return false;
    }




}