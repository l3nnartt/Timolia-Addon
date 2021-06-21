package de.labymod.lennart.pxlspace;

import de.labymod.lennart.TimoliaAddon;
import net.labymod.api.events.MessageSendEvent;

public class MessageSendTop implements MessageSendEvent {
    @Override
    public boolean onSend(String message) {
        if (TimoliaAddon.getInstance().isEnabledPxlSpaceStats()) {
            if (!TimoliaAddon.getInstance().isPixelspace()) return false;
            if (message.equalsIgnoreCase("/top")) {

            }
            return message.equalsIgnoreCase("/top");
        }
        return false;
    }
}