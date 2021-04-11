package de.labymod.lennart.listener;

import de.labymod.lennart.addon;
import net.labymod.api.events.MessageSendEvent;
import net.labymod.main.LabyMod;

public class MessageSendEventListener implements MessageSendEvent {

    @Override
    public boolean onSend(final String message) {
        if (!addon.INSTANCE.pixelspace) return false;
            if (message.equals("/stats")) {
                LabyMod.getInstance().displayMessageInChat("§1│ §9Timolia-Addon» §7Du hast bereits §6" + addon.INSTANCE.placedBlocks + "§7 platziert!");
            } return message.equals("/stats");
    }

}