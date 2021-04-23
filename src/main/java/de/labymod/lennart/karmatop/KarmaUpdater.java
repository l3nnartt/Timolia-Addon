package de.labymod.lennart.karmatop;

import de.labymod.lennart.TimoliaAddon;
import net.labymod.utils.ServerData;
import net.minecraft.client.Minecraft;

import java.util.function.Consumer;

public class KarmaUpdater implements Consumer<ServerData> {

    @Override
    public void accept(ServerData serverData) {
        if(serverData.getIp().toLowerCase().contains("timolia.de")) {
            System.out.println("Karma abfrage");
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/karma");
            TimoliaAddon.getINSTANCE().setKarmaAnswer(true);
            System.out.println("karma message sended");
        }
    }
}