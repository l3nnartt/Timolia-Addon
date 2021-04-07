package de.labymod.lennart;

import com.google.gson.JsonObject;
import de.labymod.lennart.listener.MessageReceiveListener;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.settings.elements.StringElement;
import net.labymod.utils.Material;

import java.util.List;

public class addon extends LabyModAddon {

    public boolean enabledAutoGG1vs1;
    public String win1vs1;
    public String lose1vs1;
    public static addon INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        System.out.println("Timolia-Addon enabled");

        this.getApi().getEventManager().register(new MessageReceiveListener());
    }

    @Override
    public void loadConfig() {

        this.enabledAutoGG1vs1 = getConfig().has( "enabledAutoGG1vs1" ) ? getConfig().get( "enabledAutoGG1vs1" ).getAsBoolean() : true;
        this.win1vs1 = getConfig().has( "win1vs1" ) ? getConfig().get( "win1vs1" ).getAsString() : "gg";
        this.lose1vs1 = getConfig().has( "lose1vs1" ) ? getConfig().get( "lose1vs1" ).getAsString() : "gg";

    }

    @Override
    protected void fillSettings( List<SettingsElement> subSettings ) {

        subSettings.add(new BooleanElement("AutoGG-1vs1", this, new ControlElement.IconData(Material.LEVER), "enabledAutoGG1vs1", this.enabledAutoGG1vs1));
        subSettings.add(new StringElement("WinGG", this, new ControlElement.IconData(Material.EMPTY_MAP ), "win1vs1", this.win1vs1));
        subSettings.add(new StringElement("LoseGG", this, new ControlElement.IconData(Material.EMPTY_MAP ), "lose1vs1", this.lose1vs1));

    }

}