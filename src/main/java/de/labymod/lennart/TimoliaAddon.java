package de.labymod.lennart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.labymod.lennart.autogglistener.*;
import de.labymod.lennart.config.AddonConfig;
import de.labymod.lennart.group.GroupManager;
import de.labymod.lennart.group.GroupOnRender;
import de.labymod.lennart.karmatop.Authenticator;
import de.labymod.lennart.karmatop.KarmaListener;
import de.labymod.lennart.karmatop.KarmaUpdater;
import de.labymod.lennart.karmatop.WebsiteSettingsModule;
import de.labymod.lennart.listener.*;
import de.labymod.lennart.modules.*;
import de.labymod.lennart.pixelspace.MessageReceivePixelSpacePlacedBlockListener;
import de.labymod.lennart.pixelspace.MessageSendStats;
import de.labymod.lennart.pixelspace.MessageSendTop;
import net.labymod.api.LabyModAddon;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.ModuleCategoryRegistry;
import net.labymod.settings.elements.*;
import net.labymod.user.group.LabyGroup;
import net.labymod.utils.Material;
import net.minecraft.util.ResourceLocation;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TimoliaAddon extends LabyModAddon {

    private static TimoliaAddon instance;
    private final ExecutorService exService = Executors.newSingleThreadExecutor();

    private int killstreak;
    private ModuleCategory timolia;
    private Gson gson;
    private AddonConfig addonConfig;
    private String latestserver = null;
    private boolean listenForMap;
    private boolean karmaAnswer;
    private boolean enabledTeamBadge;
    private boolean enabledPxlSpaceStats;

    //Group
    private LabyGroup group;
    private final HashMap<UUID, Boolean> cachedTimoliaTeam = new HashMap<>();
    private GroupManager groupManager;


    //Header Check
    private boolean pixelspace;
    private boolean castles;
    private boolean arena;
    private boolean mineception;
    private boolean splun;
    private boolean brainbow;
    private boolean suspicious;
    private boolean tspiele;
    private boolean intime;
    private boolean dna;

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
        instance = this;
        authenticator = new Authenticator();
        timolia = new ModuleCategory("Timolia", true, new ControlElement.IconData(new ResourceLocation("icons/timolia/timolia128.png")));
        ModuleCategoryRegistry.loadCategory(timolia);

        gson = new GsonBuilder().setPrettyPrinting().create();
        addonConfig = AddonConfig.read();

        //Group
        groupManager = new GroupManager();
        api.getEventManager().register(new GroupOnRender());

        //AutoGG
        api.getEventManager().register(new MessageReceive1vs1Listener());
        api.getEventManager().register(new MessageReceive4renaListener());
        api.getEventManager().register(new MessageReceiveSplunListener());
        api.getEventManager().register(new MessageReceiveCastlesListener());
        api.getEventManager().register(new MessageReceiveBrainbowListener());
        api.getEventManager().register(new MessageReceiveInTimeListener());
        api.getEventManager().register(new MessageReceiveTSpieleListener());
        api.getEventManager().register(new MessageReceiveMineceptionListener());
        api.getEventManager().register(new MessageReceiveDNAListener());
        api.getEventManager().register(new MessageReceiveDNAListener());
        api.getEventManager().register(new MessageReceiveSuspiciousListener());

        //Automatic Karma Updater
        api.getEventManager().register(new KarmaListener());
        api.getEventManager().registerOnJoin(new KarmaUpdater());

        //pxlspace
        api.getEventManager().register(new MessageReceivePixelSpacePlacedBlockListener());
        api.getEventManager().register(new MessageSendStats());
        api.getEventManager().register(new MessageSendTop());

        //Other
        api.getEventManager().register(new MessageMapReceiveListener());
        api.getEventManager().register(new MessageEnemyReceiveListener());
        api.getEventManager().register(new TablistHeaderMapListener());
        api.getEventManager().register(new TablistHeaderListener());


        //Modules
        api.registerServerSupport(this, new ServerSupport());
        api.registerModule(new EnemyStats());
        System.out.println("Timolia-Addon enabled");
    }

    @Override
    public void loadConfig() {
        this.killstreak = getConfig().has("killstreak") ? getConfig().get("killstreak").getAsInt() : 0;
        this.enabledKarmaUpdater = !getConfig().has("enabledKarmaUpdater") || getConfig().get("enabledKarmaUpdater").getAsBoolean();
        this.enabledTeamBadge = !getConfig().has("enabledTeamBadge") || getConfig().get("enabledTeamBadge").getAsBoolean();
        this.enabledPxlSpaceStats = !getConfig().has("enabledPxlSpaceStats") || getConfig().get("enabledPxlSpaceStats").getAsBoolean();

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
    protected void fillSettings(List<SettingsElement> subSettings) {

        subSettings.add(new HeaderElement("Allgemein"));
        subSettings.add(new BooleanElement("KarmaUpdater", this, new ControlElement.IconData(Material.EXP_BOTTLE), "enabledKarmaUpdater", this.enabledKarmaUpdater));
        subSettings.add(new BooleanElement("PxlSpace Custom-Commands", this, new ControlElement.IconData(Material.BOOK_AND_QUILL), "enabledPxlSpaceStats", this.enabledPxlSpaceStats));
        subSettings.add(new BooleanElement("Team Badges (Restart required)", this, new ControlElement.IconData(Material.RECORD_4), "enabledTeamBadge", this.enabledTeamBadge));

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
        subSettings.add(new WebsiteSettingsModule("Website", "karmatop.de Website", "Website"));
    }

    private void configurSettings(List<SettingsElement> subSettings, ConfigItem... gamemodes) {
        for (ConfigItem gamemode : gamemodes) {
            subSettings.add(new HeaderElement(gamemode.getGamemode()));
            subSettings.add(new BooleanElement("AutoGG-" + gamemode.getGamemode(), this, gamemode.getIcon(), "enabledAutoGG" + gamemode.getGamemode(), gamemode.isConfigEnabledValue()));
            subSettings.add(new StringElement("GameGG", this, new ControlElement.IconData(Material.NAME_TAG), "game" + gamemode.getGamemode(), gamemode.getConfigMessage()));
        }
    }

    public static TimoliaAddon getInstance() {
        return instance;
    }

    public String getKillstreak() {
        return String.valueOf(killstreak);
    }

    public void setKillstreak(int killstreak) {
        this.killstreak = killstreak;
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

    public AddonConfig getAddonConfig() {
        return addonConfig;
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

    public boolean isEnabledAutoGG4rena() {
        return enabledAutoGG4rena;
    }

    public boolean isEnabledAutoGGCastles() {
        return enabledAutoGGCastles;
    }

    public boolean isEnabledAutoGGMineception() {
        return enabledAutoGGMineception;
    }

    public boolean isEnabledAutoGGSplun() {
        return enabledAutoGGSplun;
    }

    public boolean isEnabledAutoGGSuspicious() {
        return enabledAutoGGSuspicious;
    }

    public boolean isEnabledAutoGGDNA() {
        return enabledAutoGGDNA;
    }

    public boolean isEnabledAutoGGInTime() {
        return enabledAutoGGInTime;
    }

    public boolean isEnabledAutoGGBrainbow() {
        return enabledAutoGGBrainbow;
    }

    public boolean isEnabledAutoGGTSpiele() {
        return enabledAutoGGTSpiele;
    }

    public String getWin1vs1() {
        return win1vs1;
    }

    public String getLose1vs1() {
        return lose1vs1;
    }

    public String getMatch4rena() {
        return match4rena;
    }

    public String getGame4rena() {
        return game4rena;
    }

    public String getGameCastles() {
        return gameCastles;
    }

    public String getGameMineception() {
        return gameMineception;
    }

    public String getGameSplun() {
        return gameSplun;
    }

    public String getGameSuspicious() {
        return gameSuspicious;
    }

    public String getGameDNA() {
        return gameDNA;
    }

    public String getGameInTime() {
        return gameInTime;
    }

    public String getGameBrainbow() {
        return gameBrainbow;
    }

    public String getGameTSpiele() {
        return gameTSpiele;
    }

    public boolean isEnabledKarmaUpdater() {
        return enabledKarmaUpdater;
    }

    public boolean isEnabledTeamBadge() {
        return enabledTeamBadge;
    }

    public boolean isEnabledPxlSpaceStats() {
        return enabledPxlSpaceStats;
    }

    public HashMap<UUID, Boolean> getCachedTimoliaTeam() {
        return cachedTimoliaTeam;
    }

    public ExecutorService getExService() {
        return exService;
    }

    public Authenticator getAuthenticator() {
        return authenticator;
    }

    public LabyGroup getGroup() {
        return group;
    }

    public void setGroup(LabyGroup group) {
        this.group = group;
    }

    public GroupManager getGroupManager() {
        return groupManager;
    }
}