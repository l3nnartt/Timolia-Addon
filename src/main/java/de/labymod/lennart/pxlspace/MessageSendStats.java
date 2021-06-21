package de.labymod.lennart.pxlspace;

import de.labymod.lennart.TimoliaAddon;
import net.labymod.api.events.MessageSendEvent;
import net.labymod.main.LabyMod;

public class MessageSendStats implements MessageSendEvent {

    @Override
    public boolean onSend(final String message) {
        if (TimoliaAddon.getInstance().isEnabledPxlSpaceStats()) {
            if (!TimoliaAddon.getInstance().isPixelspace()) return false;
            String[] args = message.split(" ");

            if (message.equalsIgnoreCase("/stats")) {
                //LabyMod.getInstance().displayMessageInChat("§f§6╠ §d✦ §rMagenta: §6BLOCKS");
                LabyMod.getInstance().displayMessageInChat("§1│ §9Timolia-Addon§1» §7Du hast bereits §6" + TimoliaAddon.getInstance().getPlacedBlocks() + "§7 platziert!");
            } return message.equalsIgnoreCase("/stats");
        } return false;
    }
}