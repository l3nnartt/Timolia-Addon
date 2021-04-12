package de.labymod.lennart.listener;

import de.labymod.lennart.addon;
import net.labymod.api.events.TabListEvent;

public class TablistHeaderListener implements TabListEvent {

    @Override
    public void onUpdate(Type type, String s, String s1) {

        if (s.contains("pxlspace")) {
            addon.INSTANCE.pixelspace = true;
        } else if (s.contains("games")) {
            addon.INSTANCE.pixelspace = false;
        }

        if (s.contains("4rena")) {
            addon.INSTANCE.arena = true;
        } else if (s.contains("games")) {
            addon.INSTANCE.arena = false;
        }

        if (s.contains("splun")) {
            addon.INSTANCE.splun = true;
        } else if (s.contains("games")) {
            addon.INSTANCE.splun = false;
        }

        if (s.contains("castles")) {
            addon.INSTANCE.castles = true;
        } else if (s.contains("games")) {
            addon.INSTANCE.castles = false;
        }

        if (s.contains("suspicious")) {
            addon.INSTANCE.suspicious = true;
        } else if (s.contains("games")) {
            addon.INSTANCE.suspicious = false;
        }

        if (s.contains("brainbow")) {
            addon.INSTANCE.brainbow = true;
        } else if (s.contains("games")) {
            addon.INSTANCE.brainbow = false;
        }

        if (s.contains("intime")) {
            addon.INSTANCE.intime = true;
        } else if (s.contains("games")) {
            addon.INSTANCE.intime = false;
        }

        if (s.contains("tspiele")) {
            addon.INSTANCE.tspiele = true;
        } else if (s.contains("games")) {
            addon.INSTANCE.tspiele = false;
        }

        if (s.contains("dna")) {
            addon.INSTANCE.dna = true;
        } else if (s.contains("games")) {
            addon.INSTANCE.dna = false;
        }

        if (s.contains("mineception")) {
            addon.INSTANCE.mineception = true;
        } else if (s.contains("games")) {
            addon.INSTANCE.mineception = false;
        }

    }
}