package de.labymod.lennart.pixelspace;

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
                //args[1]

                //LabyMod.getInstance().displayMessageInChat("§f§6░▒▓§bStats von L3nnart_§6▓▒░");
                //LabyMod.getInstance().displayMessageInChat("§f§6╔═════");
                //LabyMod.getInstance().displayMessageInChat("§f§6╠ §f✦ §rWhite: §6BLOCKS");
                //LabyMod.getInstance().displayMessageInChat("§f§6╠ §6✦ §rOrange: §6BLOCKS");
                //LabyMod.getInstance().displayMessageInChat("§f§6╠ §d✦ §rMagenta: §6BLOCKS");
                //LabyMod.getInstance().displayMessageInChat("§f§6╠ §9✦ §rLightBlue: §6BLOCKS");
                //LabyMod.getInstance().displayMessageInChat("§f§6╠ §e✦ §rYellow: §6BLOCKS");
                //LabyMod.getInstance().displayMessageInChat("§f§6╠ §a✦ §rLime: §6BLOCKS");
                //LabyMod.getInstance().displayMessageInChat("§f§6╠ §5✦ §rPink: §6BLOCKS");
                //LabyMod.getInstance().displayMessageInChat("§f§6╠ §8✦ §rGray: §6BLOCKS");
                //LabyMod.getInstance().displayMessageInChat("§f§6╠ §7✦ §rLightGray: §6BLOCKS");
                //LabyMod.getInstance().displayMessageInChat("§f§6╠ §3✦ §rCyan: §6BLOCKS");
                //LabyMod.getInstance().displayMessageInChat("§f§6╠ §5✦ §rPurple: §6BLOCKS");
                //LabyMod.getInstance().displayMessageInChat("§f§6╠ §1✦ §rBlue: §6BLOCKS");
                //LabyMod.getInstance().displayMessageInChat("§f§6╠ §6✦ §rBrown: §6BLOCKS");
                //LabyMod.getInstance().displayMessageInChat("§f§6╠ §2✦ §rGreen: §6BLOCKS");
                //LabyMod.getInstance().displayMessageInChat("§f§6╠ §4✦ §rRed: §6BLOCKS");
                //LabyMod.getInstance().displayMessageInChat("§f§6╠ §0✦ §rBlack: §6BLOCKS");
                //LabyMod.getInstance().displayMessageInChat("§f§6╚═════");

                LabyMod.getInstance().displayMessageInChat("§1│ §9Timolia-Addon§1» §7Du hast bereits §6" + TimoliaAddon.getInstance().getPlacedBlocks() + "§7 platziert!");
            } return message.equalsIgnoreCase("/stats");
        } return false;
    }
}