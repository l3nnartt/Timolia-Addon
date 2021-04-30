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
                LabyMod.getInstance().displayMessageInChat("§1│ §9Timolia-Addon§1» §7Befehl in Arbeit!");
            } return message.equalsIgnoreCase("/top");
        } return false;
    }
}
