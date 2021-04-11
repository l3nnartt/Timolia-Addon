package de.labymod.lennart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.labymod.lennart.listener.MessageRecive4renaListener;
import de.labymod.lennart.config.AddonConfig;
import de.labymod.lennart.listener.*;
import de.labymod.lennart.modules.Enemy;
import de.labymod.lennart.modules.EnemyStats;
import de.labymod.lennart.modules.Map;
import de.labymod.lennart.modules.PxlSpace;
import de.labymod.lennart.pvp.MessageEnemyReceiveListener;
import de.labymod.lennart.pvp.MessageRecive1vs1Listener;
import net.labymod.api.LabyModAddon;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.ModuleCategoryRegistry;
import net.labymod.settings.elements.*;
import net.labymod.utils.Material;
import net.minecraft.util.ResourceLocation;
import java.util.List;

public class addon extends LabyModAddon {

    public int placedBlocks;
    public String[] servers;
    public static addon INSTANCE;
    public ModuleCategory timolia;
    public Gson gson;
    public AddonConfig addonConfig;
    public boolean mapAnswer = false;

    //Header Check
    public boolean pixelspace = false;
    public boolean castles = false;
    public boolean arena = false;
    public boolean jumpworld = false;
    public boolean mineception = false;
    public boolean splun = false;
    public boolean DNA = false;
    public boolean brainbow = false;
    public boolean chainreact = false;
    public boolean suspicious = false;
    public boolean tspiele = false;
    public boolean intime = false;

    //AutoGGEnabled Check
    public boolean enabledAutoGG1vs1;
    public boolean enabledAutoGG4rena;
    public boolean enabledAutoGGCastles;
    public boolean enabledAutoGGJumpWorld;
    public boolean enabledAutoGGMineception;
    public boolean enabledAutoGGSplun;
    public boolean enabledAutoGGSuspicious;
    public boolean enabledAutoGGDNA;
    public boolean enabledAutoGGInTime;
    public boolean enabledAutoGGBrainbow;
    public boolean enabledAutoGGTSpiele;
    private boolean enabledAutoGGChainReact;

    //AutoGG Message
    public String win1vs1;
    public String lose1vs1;
    public String match4rena;
    public String game4rena;
    public String gameCastles;
    public String gameJumpWorld;
    public String gameMineception;
    public String gameSplun;
    public String gameSuspicious;
    public String gameDNA;
    public String gameInTime;
    public String gameBrainbow;
    public String gameTSpiele;
    private String gameChainReact;

    @Override
    public void onEnable() {
        INSTANCE = this;
        timolia = new ModuleCategory("Timolia", true, new ControlElement.IconData(new ResourceLocation("icons/timolia/timolia128.png")));
        System.out.println("Timolia-Addon enabled");
        ModuleCategoryRegistry.loadCategory(timolia);

        gson = new GsonBuilder().setPrettyPrinting().create();
        addonConfig = AddonConfig.read();

        this.getApi().getEventManager().register(new TablistHeaderListener());
        this.getApi().getEventManager().register(new TablistHeaderPixelSpaceListener());
        this.getApi().getEventManager().register(new MessageSendEventListener());
        this.getApi().getEventManager().register(new MessageRecive1vs1Listener());
        this.getApi().getEventManager().register(new MessageRecive4renaListener());
        this.getApi().getEventManager().register(new MessageReceiveSplunListener());
        this.getApi().getEventManager().register(new MessageMapReceiveListener());
        this.getApi().getEventManager().register(new MessageEnemyReceiveListener());
        this.getApi().getEventManager().register(new MessageReceivePixelSpacePlacedBlockListener());
        this.getApi().registerModule(new Map());
        this.getApi().registerModule(new Enemy());
        this.getApi().registerModule(new EnemyStats());
        this.getApi().registerModule(new PxlSpace());
    }

    @Override
    public void loadConfig() {
        this.placedBlocks = getConfig().has("placedBlocks") ? getConfig().get("placedBlocks").getAsInt() : 0;

        this.enabledAutoGG1vs1 = !getConfig().has("enabledAutoGG1vs1") || getConfig().get("enabledAutoGG1vs1").getAsBoolean();
        this.win1vs1 = getConfig().has("win1vs1") ? getConfig().get("win1vs1").getAsString() : "gg";
        this.lose1vs1 = getConfig().has("lose1vs1") ? getConfig().get("lose1vs1").getAsString() : "gg";

        this.enabledAutoGG4rena = !getConfig().has("enabledAutoGG4rena") || getConfig().get("enabledAutoGG4rena").getAsBoolean();
        this.match4rena = getConfig().has("match4rena") ? getConfig().get("match4rena").getAsString() : "gg";
        this.game4rena = getConfig().has("game4rena") ? getConfig().get("game4rena").getAsString() : "gg";

        this.enabledAutoGGCastles = !getConfig().has("enabledAutoGGCastles") || getConfig().get("enabledAutoGGCastles").getAsBoolean();
        this.gameCastles = getConfig().has("gameCastles") ? getConfig().get("game4rena").getAsString() : "gg";

        this.enabledAutoGGJumpWorld = !getConfig().has("enabledAutoGGJumpWorld") || getConfig().get("enabledAutoGGJumpWorld").getAsBoolean();
        this.gameJumpWorld = getConfig().has("gameJumpWorld") ? getConfig().get("gameJumpWorld").getAsString() : "gg";

        this.enabledAutoGGMineception = !getConfig().has("enabledAutoGGMineception") || getConfig().get("enabledAutoGGMineception").getAsBoolean();
        this.gameMineception = getConfig().has("gameMineception") ? getConfig().get("gameMineception").getAsString() : "gg";

        this.enabledAutoGGSplun = !getConfig().has("enabledAutoGGSplun") || getConfig().get("enabledAutoGGSplun").getAsBoolean();
        this.gameSplun = getConfig().has("gameSplun") ? getConfig().get("gameSplun").getAsString() : "gg";

        this.enabledAutoGGSuspicious = !getConfig().has("enabledAutoGGSuspicious") || getConfig().get("enabledAutoGGSuspicious").getAsBoolean();
        this.gameSuspicious = getConfig().has("gameSuspicious") ? getConfig().get("gameSuspicious").getAsString() : "Red is Sus";

        this.enabledAutoGGDNA = !getConfig().has("enabledAutoGGDNA") || getConfig().get("enabledAutoGGDNA").getAsBoolean();
        this.gameDNA = getConfig().has("gameDNA") ? getConfig().get("gameDNA").getAsString() : "gg";

        this.enabledAutoGGInTime = !getConfig().has("enabledAutoGGInTime") || getConfig().get("enabledAutoGGInTime").getAsBoolean();
        this.gameInTime = getConfig().has("gameInTime") ? getConfig().get("gameInTime").getAsString() : "gg";

        this.enabledAutoGGBrainbow = !getConfig().has("enabledAutoGGBrainbow") || getConfig().get("enabledAutoGGBrainbow").getAsBoolean();
        this.gameBrainbow = getConfig().has("gameBrainbow") ? getConfig().get("gameBrainbow").getAsString() : "gg";

        this.enabledAutoGGTSpiele = !getConfig().has("enabledAutoGGTSpiele") || getConfig().get("enabledAutoGGTSpiele").getAsBoolean();
        this.gameTSpiele = getConfig().has("gameTSpiele") ? getConfig().get("gameTSpiele").getAsString() : "gg";

        this.enabledAutoGGChainReact = !getConfig().has("enabledAutoGGChainReact") || getConfig().get("enabledAutoGGChainReact").getAsBoolean();
        this.gameChainReact = getConfig().has("gameChainReact") ? getConfig().get("gameChainReact").getAsString() : "gg";
    }

    @Override
    protected void fillSettings( List<SettingsElement> subSettings ) {

        subSettings.add(new HeaderElement("1vs1"));
        subSettings.add(new BooleanElement("AutoGG-1vs1", this, new ControlElement.IconData(Material.CHAINMAIL_CHESTPLATE), "enabledAutoGG1vs1", this.enabledAutoGG1vs1));
        subSettings.add(new StringElement("1vs1-WinGG", this, new ControlElement.IconData(Material.NAME_TAG), "win1vs1", this.win1vs1));
        subSettings.add(new StringElement("1vs1-LoseGG", this, new ControlElement.IconData(Material.NAME_TAG), "lose1vs1", this.lose1vs1));

        subSettings.add(new HeaderElement("4rena"));
        subSettings.add(new BooleanElement("AutoGG-4rena", this, new ControlElement.IconData(Material.DIAMOND_SWORD), "enabledAutoGG4rena", this.enabledAutoGG4rena));
        subSettings.add(new StringElement("MatchGG", this, new ControlElement.IconData(Material.NAME_TAG), "match4rena", this.match4rena));
        subSettings.add(new StringElement("GameGG", this, new ControlElement.IconData(Material.NAME_TAG), "game4rena", this.game4rena));

        configurSettings(subSettings,
                new ConfigItem("Castles", this.gameCastles, this.enabledAutoGGCastles, new ControlElement.IconData(Material.BANNER)),
                new ConfigItem("JumpWorld", this.gameJumpWorld, this.enabledAutoGGJumpWorld, new ControlElement.IconData(Material.GOLD_BOOTS)),
                new ConfigItem("Mineception", this.gameMineception, this.enabledAutoGGMineception, new ControlElement.IconData(Material.RECORD_8)),
                new ConfigItem("Splun", this.gameSplun, this.enabledAutoGGSplun, new ControlElement.IconData(Material.STAINED_CLAY)),
                new ConfigItem("Suspicious", this.gameSuspicious, this.enabledAutoGGSuspicious, new ControlElement.IconData(Material.IRON_DOOR)),
                new ConfigItem("DNA", this.gameDNA, this.enabledAutoGGDNA, new ControlElement.IconData(Material.GLASS)),
                new ConfigItem("InTime", this.gameInTime, this.enabledAutoGGInTime, new ControlElement.IconData(Material.WATCH)),
                new ConfigItem("Brainbow", this.gameBrainbow, this.enabledAutoGGBrainbow, new ControlElement.IconData(Material.BOW)),
                new ConfigItem("TSpiele", this.gameDNA, this.enabledAutoGGTSpiele, new ControlElement.IconData(Material.BEACON)),
                new ConfigItem("ChainReact", this.gameChainReact, this.enabledAutoGGChainReact, new ControlElement.IconData(Material.EYE_OF_ENDER))
        );
    }

    private void configurSettings(List<SettingsElement> subSettings, ConfigItem... gamemodes) {

        for (ConfigItem gamemode : gamemodes) {
            subSettings.add(new HeaderElement(gamemode.getGamemode()));
            subSettings.add(new BooleanElement("AutoGG-" + gamemode.getGamemode(), this, gamemode.getIcon(), "enabledAutoGG" + gamemode.getGamemode(), gamemode.isConfigEnabledValue()));
            subSettings.add(new StringElement("GameGG", this, new ControlElement.IconData(Material.NAME_TAG), "game" + gamemode.getGamemode(), gamemode.getConfigMessage()));
        }
    }

    public void addplacedBlocks() {
        placedBlocks++;
        getConfig().addProperty("placedBlocks", placedBlocks);
        this.saveConfig();
        this.loadConfig();
    }

}