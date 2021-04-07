package de.labymod.lennart;

import de.labymod.lennart.listener.MessageReceiveListener;
import net.labymod.api.LabyModAddon;
import net.labymod.main.LabyMod;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Consumer;
import net.labymod.utils.ServerData;

import java.util.List;

public class addon extends LabyModAddon {

    @Override
    public void onEnable() {
        System.out.println("Addon enabled yeah");

        this.getApi().getEventManager().register(new MessageReceiveListener());
    }

    @Override
    public void loadConfig() {

    }

    @Override
    protected void fillSettings(final List<SettingsElement> list) {



    }

}