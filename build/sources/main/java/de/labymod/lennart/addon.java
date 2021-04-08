package de.labymod.lennart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.labymod.lennart.config.AddonConfig;
import de.labymod.lennart.listener.MessageMapReceiveListener;
import de.labymod.lennart.listener.MessageReceiveListener;
import de.labymod.lennart.listener.TablistHeaderListener;
import de.labymod.lennart.modules.Map;
import net.labymod.api.LabyModAddon;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.ModuleCategoryRegistry;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.settings.elements.StringElement;
import net.labymod.utils.Material;
import net.minecraft.util.ResourceLocation;
import java.util.List;

public class addon extends LabyModAddon {

    public String[] servers;
    public boolean enabledAutoGG1vs1;
    public String win1vs1;
    public String lose1vs1;
    public static addon INSTANCE;
    public ModuleCategory timolia;
    public Gson gson;
    public AddonConfig addonConfig;
    public boolean mapAnswer = false;

    @Override
    public void onEnable() {

        INSTANCE = this;
        timolia = new ModuleCategory("Timolia", true, new ControlElement.IconData(new ResourceLocation("icons/timolia/timolia128.png")));
        System.out.println("Timolia-Addon enabled");
        ModuleCategoryRegistry.loadCategory(timolia);

        gson = new GsonBuilder().setPrettyPrinting().create();
        addonConfig = AddonConfig.read();

        this.getApi().getEventManager().register(new TablistHeaderListener());
        this.getApi().getEventManager().register(new MessageReceiveListener());
        this.getApi().getEventManager().register(new MessageMapReceiveListener());
        this.getApi().registerModule(new Map());
    }

    @Override
    public void loadConfig() {

        this.enabledAutoGG1vs1 = !getConfig().has("enabledAutoGG1vs1") || getConfig().get("enabledAutoGG1vs1").getAsBoolean();
        this.win1vs1 = getConfig().has("win1vs1") ? getConfig().get("win1vs1").getAsString() : "gg";
        this.lose1vs1 = getConfig().has("lose1vs1") ? getConfig().get("lose1vs1").getAsString() : "gg";
    }

    @Override
    protected void fillSettings( List<SettingsElement> subSettings ) {

        subSettings.add(new BooleanElement("AutoGG-1vs1", this, new ControlElement.IconData(Material.LEVER), "enabledAutoGG1vs1", this.enabledAutoGG1vs1));
        subSettings.add(new StringElement("WinGG", this, new ControlElement.IconData(Material.EMPTY_MAP), "win1vs1", this.win1vs1));
        subSettings.add(new StringElement("LoseGG", this, new ControlElement.IconData(Material.EMPTY_MAP), "lose1vs1", this.lose1vs1));
    }
}