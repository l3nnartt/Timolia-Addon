package de.labymod.lennart.listener;

import de.labymod.lennart.TimoliaAddon;
import net.labymod.api.events.MessageReceiveEvent;

public class MessageReceivePixelSpacePlacedBlockListener implements MessageReceiveEvent {
    @Override
    public boolean onReceive(String s, String msg) {
        if (msg.contains("Timolia» Du hast einen Block platziert! In 20 Sekunden kannst du den nächsten bauen!")) {
            TimoliaAddon.getINSTANCE().addplacedBlocks();
        }
        return false;
    }
}