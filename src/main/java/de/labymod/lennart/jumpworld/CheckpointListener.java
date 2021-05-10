package de.labymod.lennart.jumpworld;

import net.labymod.api.events.MessageReceiveEvent;
import net.labymod.main.LabyMod;

public class CheckpointListener implements MessageReceiveEvent {
    String zeit = "zeit";
    @Override
    public boolean onReceive(String s, String s1) {
        if(s.contains("Checkpoint erreicht!") || (s.contains("JumpWorld»"))) {
            LabyMod.getInstance().displayMessageInChat("Checkpoint erreicht (Zeit)");
            System.out.println("CP");
        } return false;
    }
}