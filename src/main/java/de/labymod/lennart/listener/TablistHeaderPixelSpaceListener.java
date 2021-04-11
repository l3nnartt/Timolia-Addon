package de.labymod.lennart.listener;

import de.labymod.lennart.addon;
import net.labymod.api.events.TabListEvent;

public class TablistHeaderPixelSpaceListener implements TabListEvent {
    @Override
    public void onUpdate(Type type, String s, String s1) {
        if (s.contains("pxlspace")) {
            addon.INSTANCE.pixelspace = true;
        } else if (s.contains("games")) {
            addon.INSTANCE.pixelspace = false;
        }
    }
}