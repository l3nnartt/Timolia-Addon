package de.labymod.lennart.listener;

import de.labymod.lennart.addon;
import net.labymod.api.events.MessageSendEvent;
import net.labymod.main.LabyMod;

public class MessageSendPixelSpaceListener implements MessageSendEvent {

    @Override
    public boolean onSend(String message) {
        if (!addon.INSTANCE.pixelspace) return false;
        System.out.println(addon.INSTANCE.pixelspace);

        if (message.equalsIgnoreCase("/stats")) {
            LabyMod.getInstance().displayMessageInChat("§f§1│§9 Timolia-Addon§1»§7 Du hast §6" + addon.INSTANCE.placedBlocks + " §7Blöcke platziert");
        } return message.equalsIgnoreCase("/stats");
    }
}