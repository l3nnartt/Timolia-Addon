package de.labymod.lennart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.labymod.lennart.autogglistener.*;
import de.labymod.lennart.config.AddonConfig;
import de.labymod.lennart.group.CustomGroup;
import de.labymod.lennart.group.GroupManager;
import de.labymod.lennart.karmatop.Authenticator;
import de.labymod.lennart.karmatop.KarmaListener;
import de.labymod.lennart.karmatop.KarmaUpdater;
import de.labymod.lennart.karmatop.WebsiteSettingsModule;
import de.labymod.lennart.listener.*;
import de.labymod.lennart.modules.*;
import net.labymod.api.LabyModAddon;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.ModuleCategoryRegistry;
import net.labymod.settings.elements.*;
import net.labymod.user.group.LabyGroup;
import net.labymod.utils.Material;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TimoliaAddon extends LabyModAddon {
    private final ExecutorService exService = Executors.newSingleThreadExecutor();
    private int placedBlocks;
    private int killstreak;
    private String[] servers;
    private static TimoliaAddon INSTANCE;
    private ModuleCategory timolia;
    private Gson gson;
    private AddonConfig addonConfig;
    private String latestserver = null;
    private boolean listenForMap = false;
    private boolean karmaAnswer = false;
    private boolean enabledTeamBadge = false;

    //Group
    private LabyGroup group;

    //Header Check
    private boolean pixelspace = false;
    private boolean castles = false;
    private boolean arena = false;
    private boolean mineception = false;
    private boolean splun = false;
    private boolean brainbow = false;
    private boolean suspicious = false;
    private boolean tspiele = false;
    private boolean intime = false;
    private boolean dna = false;

    //Sonstiges
    private boolean enabledKarmaUpdater;

    //AutoGGEnabled Check
    private boolean enabledAutoGG1vs1;
    private boolean enabledAutoGG4rena;
    private boolean enabledAutoGGCastles;
    private boolean enabledAutoGGMineception;
    private boolean enabledAutoGGSplun;
    private boolean enabledAutoGGSuspicious;
    private boolean enabledAutoGGDNA;
    private boolean enabledAutoGGInTime;
    private boolean enabledAutoGGBrainbow;
    private boolean enabledAutoGGTSpiele;

    //AutoGG Message
    private String win1vs1;
    private String lose1vs1;
    private String match4rena;
    private String game4rena;
    private String gameCastles;
    private String gameMineception;
    private String gameSplun;
    private String gameSuspicious;
    private String gameDNA;
    private String gameInTime;
    private String gameBrainbow;
    private String gameTSpiele;

    //Authenticator
    private Authenticator authenticator;

    @Override
    public void onEnable() {
        INSTANCE = this;
        authenticator = new Authenticator();
        timolia = new ModuleCategory("Timolia", true, new ControlElement.IconData(new ResourceLocation("icons/timolia/timolia128.png")));
        System.out.println("Timolia-Addon enabled");
        ModuleCategoryRegistry.loadCategory(timolia);

        gson = new GsonBuilder().setPrettyPrinting().create();
        addonConfig = AddonConfig.read();

        //Group
        new GroupManager();

        this.getApi().getEventManager().register(new TablistHeaderMapListener());
        this.getApi().getEventManager().register(new TablistHeaderListener());
        this.getApi().getEventManager().register(new MessageSendEventListener());

        this.getApi().getEventManager().register(new MessageReceive1vs1Listener());
        this.getApi().getEventManager().register(new MessageReceive4renaListener());
        this.getApi().getEventManager().register(new MessageReceiveSplunListener());
        this.getApi().getEventManager().register(new MessageReceiveCastlesListener());
        this.getApi().getEventManager().register(new MessageReceiveBrainbowListener());
        this.getApi().getEventManager().register(new MessageReceiveInTimeListener());
        this.getApi().getEventManager().register(new MessageReceiveTSpieleListener());
        this.getApi().getEventManager().register(new MessageReceiveMineceptionListener());
        this.getApi().getEventManager().register(new MessageReceiveDNAListener());
        this.getApi().getEventManager().register(new MessageReceiveDNAListener());
        this.getApi().getEventManager().register(new MessageReceiveSuspiciousListener());

        this.getApi().getEventManager().register(new KarmaListener());
        this.getApi().getEventManager().registerOnJoin(new KarmaUpdater());

        this.getApi().getEventManager().register(new MessageMapReceiveListener());
        this.getApi().getEventManager().register(new MessageEnemyReceiveListener());
        this.getApi().getEventManager().register(new MessageReceivePixelSpacePlacedBlockListener());
        this.getApi().registerModule(new Map());
        this.getApi().registerModule(new Winstreak());
        this.getApi().registerModule(new Enemy());
        this.getApi().registerModule(new EnemyStats());
        this.getApi().registerModule(new PxlSpace());
        this.getApi().registerModule(new Server());
        this.getApi().registerModule(new Kit());
    }

    @Override
    public void loadConfig() {
        this.placedBlocks = getConfig().has("placedBlocks") ? getConfig().get("placedBlocks").getAsInt() : 0;
        this.killstreak = getConfig().has("killstreak") ? getConfig().get("killstreak").getAsInt() : 0;
        this.enabledKarmaUpdater = !getConfig().has("enabledKarmaUpdater") || getConfig().get("enabledKarmaUpdater").getAsBoolean();
        this.enabledTeamBadge = !getConfig().has("enabledTeamBadge") || getConfig().get("enabledTeamBadge").getAsBoolean();

        this.enabledAutoGG1vs1 = !getConfig().has("enabledAutoGG1vs1") || getConfig().get("enabledAutoGG1vs1").getAsBoolean();
        this.win1vs1 = getConfig().has("win1vs1") ? getConfig().get("win1vs1").getAsString() : "gg";
        this.lose1vs1 = getConfig().has("lose1vs1") ? getConfig().get("lose1vs1").getAsString() : "gg";

        this.enabledAutoGG4rena = !getConfig().has("enabledAutoGG4rena") || getConfig().get("enabledAutoGG4rena").getAsBoolean();
        this.match4rena = getConfig().has("match4rena") ? getConfig().get("match4rena").getAsString() : "gg";
        this.game4rena = getConfig().has("game4rena") ? getConfig().get("game4rena").getAsString() : "gg";

        this.enabledAutoGGCastles = !getConfig().has("enabledAutoGGCastles") || getConfig().get("enabledAutoGGCastles").getAsBoolean();
        this.gameCastles = getConfig().has("gameCastles") ? getConfig().get("gameCastles").getAsString() : "gg";

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
                new ConfigItem("Mineception", this.gameMineception, this.enabledAutoGGMineception, new ControlElement.IconData(Material.RECORD_8)),
                new ConfigItem("Splun", this.gameSplun, this.enabledAutoGGSplun, new ControlElement.IconData(Material.STAINED_CLAY)),
                new ConfigItem("Suspicious", this.gameSuspicious, this.enabledAutoGGSuspicious, new ControlElement.IconData(Material.IRON_DOOR)),
                new ConfigItem("DNA", this.gameDNA, this.enabledAutoGGDNA, new ControlElement.IconData(Material.GLASS)),
                new ConfigItem("InTime", this.gameInTime, this.enabledAutoGGInTime, new ControlElement.IconData(Material.WATCH)),
                new ConfigItem("Brainbow", this.gameBrainbow, this.enabledAutoGGBrainbow, new ControlElement.IconData(Material.BOW)),
                new ConfigItem("TSpiele", this.gameTSpiele, this.enabledAutoGGTSpiele, new ControlElement.IconData(Material.BEACON))
        );

        subSettings.add(new HeaderElement("Sonstiges"));
        subSettings.add(new BooleanElement("Auto KarmaUpdater", this, new ControlElement.IconData(Material.LEVER), "enabledKarmaUpdater", this.enabledKarmaUpdater));
        subSettings.add(new BooleanElement("Team Badges(Restart required)", this, new ControlElement.IconData(Material.LEVER), "enabledTeamBadge", this.enabledTeamBadge));

        subSettings.add(new WebsiteSettingsModule("Website", "karmatop.de Website", "Website"));
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

    public LabyGroup getCustomGroup(int id, String name, char colorChar, Color color) {
        return (LabyGroup)new CustomGroup(id, name, colorChar, color);
    }

    public void setGroup(UUID uuid) {
        if (this.isEnabledTeamBadge()) {
            if (this.group == null)
                this.group = getCustomGroup(169, "TimoliaTeam", 'c', new Color(5));
            this.api.getUserManager().getUser(uuid).setGroup(this.group);
        }
    }

    public int getPlacedBlocks() {
        return placedBlocks;
    }

    public void setPlacedBlocks(int placedBlocks) {
        this.placedBlocks = placedBlocks;
    }

    public int getKillstreak() {
        return killstreak;
    }

    public void setKillstreak(int killstreak) {
        this.killstreak = killstreak;
    }

    public String[] getServers() {
        return servers;
    }

    public void setServers(String[] servers) {
        this.servers = servers;
    }

    public static TimoliaAddon getINSTANCE() {
        return INSTANCE;
    }

    public static void setINSTANCE(TimoliaAddon INSTANCE) {
        TimoliaAddon.INSTANCE = INSTANCE;
    }

    public ModuleCategory getTimolia() {
        return timolia;
    }

    public void setTimolia(ModuleCategory timolia) {
        this.timolia = timolia;
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public AddonConfig getAddonConfig() {
        return addonConfig;
    }

    public void setAddonConfig(AddonConfig addonConfig) {
        this.addonConfig = addonConfig;
    }

    public boolean isListenForMap() {
        return listenForMap;
    }

    public void setListenForMap(boolean listenForMap) {
        this.listenForMap = listenForMap;
    }

    public String getLatestserver() {
        return latestserver;
    }

    public void setLatestserver(String latestserver) {
        this.latestserver = latestserver;
    }

    public boolean isKarmaAnswer() {
        return karmaAnswer;
    }

    public void setKarmaAnswer(boolean karmaAnswer) {
        this.karmaAnswer = karmaAnswer;
    }

    public boolean isPixelspace() {
        return pixelspace;
    }

    public void setPixelspace(boolean pixelspace) {
        this.pixelspace = pixelspace;
    }

    public boolean isCastles() {
        return castles;
    }

    public void setCastles(boolean castles) {
        this.castles = castles;
    }

    public boolean isArena() {
        return arena;
    }

    public void setArena(boolean arena) {
        this.arena = arena;
    }

    public boolean isMineception() {
        return mineception;
    }

    public void setMineception(boolean mineception) {
        this.mineception = mineception;
    }

    public boolean isSplun() {
        return splun;
    }

    public void setSplun(boolean splun) {
        this.splun = splun;
    }

    public boolean isBrainbow() {
        return brainbow;
    }

    public void setBrainbow(boolean brainbow) {
        this.brainbow = brainbow;
    }

    public boolean isSuspicious() {
        return suspicious;
    }

    public void setSuspicious(boolean suspicious) {
        this.suspicious = suspicious;
    }

    public boolean isTspiele() {
        return tspiele;
    }

    public void setTspiele(boolean tspiele) {
        this.tspiele = tspiele;
    }

    public boolean isIntime() {
        return intime;
    }

    public void setIntime(boolean intime) {
        this.intime = intime;
    }

    public boolean isDna() {
        return dna;
    }

    public void setDna(boolean dna) {
        this.dna = dna;
    }

    public boolean isEnabledAutoGG1vs1() {
        return enabledAutoGG1vs1;
    }

    public void setEnabledAutoGG1vs1(boolean enabledAutoGG1vs1) {
        this.enabledAutoGG1vs1 = enabledAutoGG1vs1;
    }

    public boolean isEnabledAutoGG4rena() {
        return enabledAutoGG4rena;
    }

    public void setEnabledAutoGG4rena(boolean enabledAutoGG4rena) {
        this.enabledAutoGG4rena = enabledAutoGG4rena;
    }

    public boolean isEnabledAutoGGCastles() {
        return enabledAutoGGCastles;
    }

    public void setEnabledAutoGGCastles(boolean enabledAutoGGCastles) {
        this.enabledAutoGGCastles = enabledAutoGGCastles;
    }

    public boolean isEnabledAutoGGMineception() {
        return enabledAutoGGMineception;
    }

    public void setEnabledAutoGGMineception(boolean enabledAutoGGMineception) {
        this.enabledAutoGGMineception = enabledAutoGGMineception;
    }

    public boolean isEnabledAutoGGSplun() {
        return enabledAutoGGSplun;
    }

    public void setEnabledAutoGGSplun(boolean enabledAutoGGSplun) {
        this.enabledAutoGGSplun = enabledAutoGGSplun;
    }

    public boolean isEnabledAutoGGSuspicious() {
        return enabledAutoGGSuspicious;
    }

    public void setEnabledAutoGGSuspicious(boolean enabledAutoGGSuspicious) {
        this.enabledAutoGGSuspicious = enabledAutoGGSuspicious;
    }

    public boolean isEnabledAutoGGDNA() {
        return enabledAutoGGDNA;
    }

    public void setEnabledAutoGGDNA(boolean enabledAutoGGDNA) {
        this.enabledAutoGGDNA = enabledAutoGGDNA;
    }

    public boolean isEnabledAutoGGInTime() {
        return enabledAutoGGInTime;
    }

    public void setEnabledAutoGGInTime(boolean enabledAutoGGInTime) {
        this.enabledAutoGGInTime = enabledAutoGGInTime;
    }

    public boolean isEnabledAutoGGBrainbow() {
        return enabledAutoGGBrainbow;
    }

    public void setEnabledAutoGGBrainbow(boolean enabledAutoGGBrainbow) {
        this.enabledAutoGGBrainbow = enabledAutoGGBrainbow;
    }

    public boolean isEnabledAutoGGTSpiele() {
        return enabledAutoGGTSpiele;
    }

    public void setEnabledAutoGGTSpiele(boolean enabledAutoGGTSpiele) {
        this.enabledAutoGGTSpiele = enabledAutoGGTSpiele;
    }

    public String getWin1vs1() {
        return win1vs1;
    }

    public void setWin1vs1(String win1vs1) {
        this.win1vs1 = win1vs1;
    }

    public String getLose1vs1() {
        return lose1vs1;
    }

    public void setLose1vs1(String lose1vs1) {
        this.lose1vs1 = lose1vs1;
    }

    public String getMatch4rena() {
        return match4rena;
    }

    public void setMatch4rena(String match4rena) {
        this.match4rena = match4rena;
    }

    public String getGame4rena() {
        return game4rena;
    }

    public void setGame4rena(String game4rena) {
        this.game4rena = game4rena;
    }

    public String getGameCastles() {
        return gameCastles;
    }

    public void setGameCastles(String gameCastles) {
        this.gameCastles = gameCastles;
    }

    public String getGameMineception() {
        return gameMineception;
    }

    public void setGameMineception(String gameMineception) {
        this.gameMineception = gameMineception;
    }

    public String getGameSplun() {
        return gameSplun;
    }

    public void setGameSplun(String gameSplun) {
        this.gameSplun = gameSplun;
    }

    public String getGameSuspicious() {
        return gameSuspicious;
    }

    public void setGameSuspicious(String gameSuspicious) {
        this.gameSuspicious = gameSuspicious;
    }

    public String getGameDNA() {
        return gameDNA;
    }

    public void setGameDNA(String gameDNA) {
        this.gameDNA = gameDNA;
    }

    public String getGameInTime() {
        return gameInTime;
    }

    public void setGameInTime(String gameInTime) {
        this.gameInTime = gameInTime;
    }

    public String getGameBrainbow() {
        return gameBrainbow;
    }

    public void setGameBrainbow(String gameBrainbow) {
        this.gameBrainbow = gameBrainbow;
    }

    public String getGameTSpiele() {
        return gameTSpiele;
    }

    public void setGameTSpiele(String gameTSpiele) {
        this.gameTSpiele = gameTSpiele;
    }

    public boolean isEnabledKarmaUpdater() {
        return enabledKarmaUpdater;
    }

    public void setEnabledKarmaUpdater(boolean enabledKarmaUpdater) {
        this.enabledKarmaUpdater = enabledKarmaUpdater;
    }

    public boolean isEnabledTeamBadge() {
        return enabledTeamBadge;
    }

    public void setEnabledTeamBadge(boolean enabledTeamBadge) {
        this.enabledTeamBadge = enabledTeamBadge;
    }

    public ExecutorService getExService() {
        return exService;
    }

    public Authenticator getAuthenticator() {
        return authenticator;
    }

}