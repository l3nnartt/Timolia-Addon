package com.github.l3nnartt;

import static net.labymod.settings.elements.ControlElement.*;

public class ConfigItem {

    private String gamemode;
    private String configMessage;
    private boolean configEnabledValue;
    private IconData icon;

    public ConfigItem(String gamemode, String configMessage, boolean configEnabledValue, IconData icon) {
        this.gamemode = gamemode;
        this.configMessage = configMessage;
        this.configEnabledValue = configEnabledValue;
        this.icon = icon;
    }


    public String getGamemode() {
        return gamemode;
    }

    public String getConfigMessage() {
        return configMessage;
    }

    public boolean isConfigEnabledValue() {
        return configEnabledValue;
    }

    public IconData getIcon() {
        return icon;
    }
}