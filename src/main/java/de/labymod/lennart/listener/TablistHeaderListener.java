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
            TimoliaAddon.getINSTANCE().setLatestserver(serveroutput);
        }

        if (s.contains("pxlspace")) {
            TimoliaAddon.getINSTANCE().setPixelspace(true);
        } else if (s.contains("games")) {
            TimoliaAddon.getINSTANCE().setPixelspace(false);
        }

        if (s.contains("4rena")) {
            TimoliaAddon.getINSTANCE().setArena(true);
        } else if (s.contains("games")) {
            TimoliaAddon.getINSTANCE().setArena(false);
        }

        if (s.contains("splun")) {
            TimoliaAddon.getINSTANCE().setSplun(true);
        } else if (s.contains("games")) {
            TimoliaAddon.getINSTANCE().setSplun(false);
        }

        if (s.contains("castles")) {
            TimoliaAddon.getINSTANCE().setCastles(true);
        } else if (s.contains("games")) {
            TimoliaAddon.getINSTANCE().setCastles(false);
        }

        if (s.contains("suspicious")) {
            TimoliaAddon.getINSTANCE().setSuspicious(true);
        } else if (s.contains("games")) {
            TimoliaAddon.getINSTANCE().setSuspicious(false);
        }

        if (s.contains("brainbow")) {
            TimoliaAddon.getINSTANCE().setBrainbow(true);
        } else if (s.contains("games")) {
            TimoliaAddon.getINSTANCE().setBrainbow(false);
        }

        if (s.contains("intime")) {
            TimoliaAddon.getINSTANCE().setIntime(true);
        } else if (s.contains("games")) {
            TimoliaAddon.getINSTANCE().setIntime(false);
        }

        if (s.contains("tspiele")) {
            TimoliaAddon.getINSTANCE().setTspiele(true);
        } else if (s.contains("games")) {
            TimoliaAddon.getINSTANCE().setTspiele(false);
        }

        if (s.contains("dna")) {
            TimoliaAddon.getINSTANCE().setDna(true);
        } else if (s.contains("games")) {
            TimoliaAddon.getINSTANCE().setDna(false);
        }

        if (s.contains("mineception")) {
            TimoliaAddon.getINSTANCE().setMineception(true);
        } else if (s.contains("games")) {
            TimoliaAddon.getINSTANCE().setMineception(false);
        }

    }
}