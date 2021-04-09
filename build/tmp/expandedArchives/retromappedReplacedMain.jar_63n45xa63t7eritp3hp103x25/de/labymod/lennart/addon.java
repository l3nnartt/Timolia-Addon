package de.labymod.lennart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.labymod.lennart.config.AddonConfig;
import de.labymod.lennart.listener.*;
import de.labymod.lennart.modules.Enemy;
import de.labymod.lennart.modules.EnemyStats;
import de.labymod.lennart.modules.Map;
import net.labymod.api.LabyModAddon;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.ModuleCategoryRegistry;
import net.labymod.settings.elements.*;
import net.labymod.utils.Material;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class addon extends LabyModAddon {

    public String[] servers;
    public boolean enabledAutoGG1vs1;
    public boolean enabledAutoGG4rena;
    public String win1vs1;
    public String lose1vs1;
    public String match4rena;
    public String game4rena;
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
        this.getApi().getEventManager().register(new MessageRecive1vs1Listener());
        this.getApi().getEventManager().register(new MessageRecive4renaListener());
        this.getApi().getEventManager().register(new MessageMapReceiveListener());
        this.getApi().getEventManager().register(new MessageEnemyReceiveListener());
        this.getApi().registerModule(new Map());
        this.getApi().registerModule(new Enemy());
        this.getApi().registerModule(new EnemyStats());
        //this.getApi().registerModule(new PlayerStats());
    }

    @Override
    public void loadConfig() {
        this.enabledAutoGG1vs1 = !getConfig().has("enabledAutoGG1vs1") || getConfig().get("enabledAutoGG1vs1").getAsBoolean();
        this.win1vs1 = getConfig().has("win1vs1") ? getConfig().get("win1vs1").getAsString() : "gg";
        this.lose1vs1 = getConfig().has("lose1vs1") ? getConfig().get("lose1vs1").getAsString() : "gg";

        this.enabledAutoGG4rena = !getConfig().has("enabledAutoGG4rena") || getConfig().get("enabledAutoGG4rena").getAsBoolean();
        this.match4rena = getConfig().has("match4rena") ? getConfig().get("match4rena").getAsString() : "gg";
        this.game4rena = getConfig().has("game4rena") ? getConfig().get("game4rena").getAsString() : "gg";
    }

    @Override
    protected void fillSettings( List<SettingsElement> subSettings ) {
        subSettings.add(new HeaderElement("1vs1"));
        subSettings.add(new BooleanElement("AutoGG-1vs1", this, new ControlElement.IconData(Material.CHAINMAIL_CHESTPLATE), "enabledAutoGG1vs1", this.enabledAutoGG1vs1));
        subSettings.add(new StringElement("1vs1-WinGG", this, new ControlElement.IconData(Material.EMPTY_MAP), "win1vs1", this.win1vs1));
        subSettings.add(new StringElement("1vs1-LoseGG", this, new ControlElement.IconData(Material.PAPER), "lose1vs1", this.lose1vs1));

        subSettings.add(new HeaderElement("4rena"));
        subSettings.add(new BooleanElement("AutoGG-4rena", this, new ControlElement.IconData(Material.DIAMOND_SWORD), "enabledAutoGG4rena", this.enabledAutoGG4rena));
        subSettings.add(new StringElement("4rena-MatchGG", this, new ControlElement.IconData(Material.EMPTY_MAP), "match4rena", this.match4rena));
        subSettings.add(new StringElement("4rena-GameGG", this, new ControlElement.IconData(Material.PAPER), "game4rena", this.game4rena));
    }
}