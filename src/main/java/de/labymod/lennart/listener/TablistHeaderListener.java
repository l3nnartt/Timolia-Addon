package de.labymod.lennart.listener;

import de.labymod.lennart.TimoliaAddon;
import net.labymod.api.events.TabListEvent;

public class TablistHeaderListener implements TabListEvent {

    @Override
    public void onUpdate(Type type, String s, String s1) {
        if (s.contains("Du spielst auf")) {
            String[] servername = s.split("§6");
            String serveroutput = servername[servername.length-1];
            serveroutput = serveroutput.substring(0,serveroutput.length()-4);
            TimoliaAddon.getInstance().setLatestserver(serveroutput);
        }

        if (s.contains("pxlspace")) {
            TimoliaAddon.getInstance().setPixelspace(true);
        } else if (s.contains("games")) {
            TimoliaAddon.getInstance().setPixelspace(false);
        }

        if (s.contains("4rena")) {
            TimoliaAddon.getInstance().setArena(true);
        } else if (s.contains("games")) {
            TimoliaAddon.getInstance().setArena(false);
        }

        if (s.contains("splun")) {
            TimoliaAddon.getInstance().setSplun(true);
        } else if (s.contains("games")) {
            TimoliaAddon.getInstance().setSplun(false);
        }

        if (s.contains("castles")) {
            TimoliaAddon.getInstance().setCastles(true);
        } else if (s.contains("games")) {
            TimoliaAddon.getInstance().setCastles(false);
        }

        if (s.contains("suspicious")) {
            TimoliaAddon.getInstance().setSuspicious(true);
        } else if (s.contains("games")) {
            TimoliaAddon.getInstance().setSuspicious(false);
        }

        if (s.contains("brainbow")) {
            TimoliaAddon.getInstance().setBrainbow(true);
        } else if (s.contains("games")) {
            TimoliaAddon.getInstance().setBrainbow(false);
        }

        if (s.contains("intime")) {
            TimoliaAddon.getInstance().setIntime(true);
        } else if (s.contains("games")) {
            TimoliaAddon.getInstance().setIntime(false);
        }

        if (s.contains("tspiele")) {
            TimoliaAddon.getInstance().setTspiele(true);
        } else if (s.contains("games")) {
            TimoliaAddon.getInstance().setTspiele(false);
        }

        if (s.contains("dna")) {
            TimoliaAddon.getInstance().setDna(true);
        } else if (s.contains("games")) {
            TimoliaAddon.getInstance().setDna(false);
        }

        if (s.contains("mineception")) {
            TimoliaAddon.getInstance().setMineception(true);
        } else if (s.contains("games")) {
            TimoliaAddon.getInstance().setMineception(false);
        }
    }
}