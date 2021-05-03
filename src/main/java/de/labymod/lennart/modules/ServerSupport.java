package de.labymod.lennart.modules;

import de.labymod.lennart.TimoliaAddon;
import de.labymod.lennart.listener.MessageEnemyReceiveListener;
import de.labymod.lennart.listener.MessageMapReceiveListener;
import net.labymod.api.events.TabListEvent;
import net.labymod.ingamegui.moduletypes.ColoredTextModule;
import net.labymod.servermanager.ChatDisplayAction;
import net.labymod.servermanager.Server;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.HeaderElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Material;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.PacketBuffer;
import java.util.Collections;
import java.util.List;

public class ServerSupport extends Server {

    private boolean displayServer;
    private boolean displayEnemy;
    private boolean displayKit;
    private boolean displayMap;

    public ServerSupport() {
        super("timolia", new String[] { "timolia.de", "play.timolia.de", "*.timolia.de" });
    }

    public void addModuleLines(List<Server.DisplayLine> lines) {
        super.addModuleLines(lines);
        try {
            lines.add(new Server.DisplayLine("Server", Collections.singletonList(ColoredTextModule.Text.getText(TimoliaAddon.getInstance().getLatestserver()))));
            if (MessageEnemyReceiveListener.enemy != null)
                lines.add(new Server.DisplayLine("Enemy", Collections.singletonList(ColoredTextModule.Text.getText(MessageEnemyReceiveListener.enemy))));
            if (MessageEnemyReceiveListener.kit != null)
                lines.add(new Server.DisplayLine("Kit", Collections.singletonList(ColoredTextModule.Text.getText(MessageEnemyReceiveListener.kit))));
            if (MessageMapReceiveListener.latestMap != null)
                lines.add(new Server.DisplayLine("Map", Collections.singletonList(ColoredTextModule.Text.getText(MessageMapReceiveListener.latestMap))));
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void handleTabInfoMessage(TabListEvent.Type tabInfoType, String formattedText, String unformattedText) {
        if (formattedText.contains("Du spielst auf")) {
            String[] servername = formattedText.split("§6");
            String serveroutput = servername[servername.length-1];
            serveroutput = serveroutput.substring(0,serveroutput.length()-4);
            TimoliaAddon.getInstance().setLatestserver(serveroutput);
        }
    }

    @Override
    public void onJoin(ServerData serverData) {
    }

    public ChatDisplayAction handleChatMessage(String clean, String formatted) {
        return ChatDisplayAction.NORMAL;
    }

    @Override
    public void handlePluginMessage(String s, PacketBuffer packetBuffer) throws Exception {
    }

    public void reset() {
        super.reset();
        //this.variable = null;
    }

    public void loadConfig() {
        this.displayServer = getBooleanAttribute("displayServer", true);
        this.displayEnemy = getBooleanAttribute("displayEnemy", true);
        this.displayKit = getBooleanAttribute("displayKit", true);
        this.displayMap = getBooleanAttribute("displayMap", false);
    }

    public void fillSubSettings(List<SettingsElement> settingsElements) {
        settingsElements.add(new HeaderElement("Server Support Modules"));
        settingsElements.add(new BooleanElement("Display Server", this, new ControlElement.IconData(Material.ANVIL), "displayServer"));
        settingsElements.add(new BooleanElement("Display Enemy", this, new ControlElement.IconData(Material.NAME_TAG), "displayEnemy"));
        settingsElements.add(new BooleanElement("Display Kit", this, new ControlElement.IconData(Material.DIAMOND_SWORD), "displayKit"));
        settingsElements.add(new BooleanElement("Display Current Map", this, new ControlElement.IconData(Material.SIGN), "displayMap"));
    }
}