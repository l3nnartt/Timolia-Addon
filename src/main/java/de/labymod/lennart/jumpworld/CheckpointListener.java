package de.labymod.lennart.jumpworld;

import de.labymod.lennart.TimoliaAddon;
import net.labymod.api.events.MessageReceiveEvent;
import net.labymod.main.LabyMod;

public class CheckpointListener implements MessageReceiveEvent {
    @Override
    public boolean onReceive(String s, String s1) {

        //Checkpoints
        if (TimoliaAddon.getInstance().isEnabledAutoGG1vs1()) {
            if (s1.contains("Jumpworld") && s1.contains("»")) {
                if (s1.contains("Checkpoint erreicht!")) {
                    System.out.println("CP");
                    LabyMod.getInstance().displayMessageInChat("Hallo Welt!");
                }
            }
        } return false;
    }
}