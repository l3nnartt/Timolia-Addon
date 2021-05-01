package de.labymod.lennart.pixelspace;

import de.labymod.lennart.TimoliaAddon;
import net.labymod.api.events.MessageSendEvent;
import net.labymod.main.LabyMod;

public class MessageSendTop implements MessageSendEvent {
    @Override
    public boolean onSend(String message) {
        if (TimoliaAddon.getINSTANCE().isEnabledPxlSpaceStats()) {
            if (!TimoliaAddon.getINSTANCE().isPixelspace()) return false;
            if (message.equalsIgnoreCase("/top")) {
                LabyMod.getInstance().displayMessageInChat("§1│ §9Timolia-Addon§1» §7Die Top §610 §7Spieler mit den meisten platzierten Blöcken");
                LabyMod.getInstance().displayMessageInChat("§1│ §61 §7- Keksbier: §6BLOCKS §7Blöcke");
                LabyMod.getInstance().displayMessageInChat("§1│ §62 §7- FlauschiegesBiest: §6BLOCKS §7Blöcke");
            } return message.equalsIgnoreCase("/top");
        } return false;
    }
}
