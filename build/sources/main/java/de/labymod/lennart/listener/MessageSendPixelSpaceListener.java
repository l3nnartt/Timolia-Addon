package de.labymod.lennart.listener;

import de.labymod.lennart.addon;
import net.labymod.api.events.MessageSendEvent;
import net.labymod.main.LabyMod;

public class MessageSendPixelSpaceListener implements MessageSendEvent {
    @Override
    public boolean onSend(String message) {
        if (addon.INSTANCE.pixelspace) {
            if (message.equals("/stats")) {
                LabyMod.getInstance().displayMessageInChat("§f§1│§9 Timolia-Addon§1»§7 Du hast §" + addon.INSTANCE.placedBlocks + " Blöcke platziert");
            } return message.equals("/stats");
        } return false;
    }
}
