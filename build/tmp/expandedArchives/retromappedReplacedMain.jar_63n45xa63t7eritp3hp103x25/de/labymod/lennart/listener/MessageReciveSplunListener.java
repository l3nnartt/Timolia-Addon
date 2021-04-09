package de.labymod.lennart.listener;

import de.labymod.lennart.addon;
import net.labymod.api.events.MessageReceiveEvent;
import net.minecraft.client.Minecraft;

public class MessageReciveSplunListener implements MessageReceiveEvent {
    @Override
    public boolean onReceive(String s, String s1) {
        if (!addon.INSTANCE.enabledAutoGGSplun) return false;
        if (s1.contains("§f│") && s1.contains("Pixel")) {
            Minecraft.func_71410_x().field_71439_g.func_71165_d(addon.INSTANCE.winSplun);
        } return false;
    }
}