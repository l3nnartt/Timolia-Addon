package de.labymod.lennart.listener;

import de.labymod.lennart.TimoliaAddon;
import net.labymod.api.events.MessageSendEvent;
import net.labymod.main.LabyMod;

public class MessageSendEventListener implements MessageSendEvent {
    @Override
    public boolean onSend(final String message) {
        if (TimoliaAddon.getINSTANCE().isEnabledPxlSpaceStats()) {
            if (!TimoliaAddon.getINSTANCE().isPixelspace()) return false;
            if (message.equalsIgnoreCase("/stats")) {
                LabyMod.getInstance().displayMessageInChat("§1│ §9Timolia-Addon» §7Du hast bereits §6" + TimoliaAddon.getINSTANCE().getPlacedBlocks() + "§7 platziert!");
            } return message.equalsIgnoreCase("/stats");
        } return false;
    }
}