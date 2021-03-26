package de.labymod.lennart;

import net.labymod.api.LabyModAddon;
import net.labymod.api.events.MessageSendEvent;
import net.labymod.main.LabyMod;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Consumer;
import net.labymod.utils.ServerData;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;

public class addon extends LabyModAddon {

    @Override
    public void onEnable() {
        System.out.println("Addon enabled");

        this.getApi().getEventManager().registerOnJoin(new Consumer<ServerData>() {
            public void accept(final ServerData serverData) {
                LabyMod.getInstance().displayMessageInChat("§6"+ serverData.getIp());
            }
        });

        this.getApi().getEventManager().register(new MessageSendEvent() {
            public boolean onSend(final String message) {
                return message.startsWith("-");
            }
        });
    }

    @Override
    public void loadConfig() {

    }

    @Override
    protected void fillSettings(final List<SettingsElement> list) {

    }

}