package de.labymod.lennart.pixelspace;

import de.labymod.lennart.TimoliaAddon;
import net.labymod.api.events.MessageSendEvent;
import net.labymod.main.LabyMod;

public class MessageSendStats implements MessageSendEvent {
    @Override
    public boolean onSend(final String message) {
        if (TimoliaAddon.getInstance().isEnabledPxlSpaceStats()) {
            if (!TimoliaAddon.getInstance().isPixelspace()) return false;
            if (message.equalsIgnoreCase("/stats")) {
                LabyMod.getInstance().displayMessageInChat("§1│ §9Timolia-Addon§1» §7Du hast bereits §6" + "BLOCKS" + "§7 platziert!");
            }
            return message.equalsIgnoreCase("/stats");
        }
        return false;
    }
}