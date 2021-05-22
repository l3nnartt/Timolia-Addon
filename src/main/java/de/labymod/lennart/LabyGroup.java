package de.labymod.lennart;

import de.labymod.lennart.group.GroupManager;
import de.labymod.lennart.group.GroupOnRender;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.SettingsElement;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class LabyGroup extends LabyModAddon {

    private static LabyGroup instance;

    //Group
    private net.labymod.user.group.LabyGroup group;
    private final HashMap<UUID, Boolean> cachedVIP = new HashMap<>();
    private GroupManager groupManager;

    @Override
    public void onEnable() {
        instance = this;

        //Group
        groupManager = new GroupManager();
        api.getEventManager().register(new GroupOnRender());

        System.out.println("Timolia-Addon enabled");
    }

    @Override
    public void loadConfig() {
    }

    @Override
    protected void fillSettings(List<SettingsElement> subSettings) {
    }

    public static LabyGroup getInstance() {
        return instance;
    }

    public HashMap<UUID, Boolean> getCachedVIP() {
        return cachedVIP;
    }

    public net.labymod.user.group.LabyGroup getGroup() {
        return group;
    }

    public void setGroup(net.labymod.user.group.LabyGroup group) {
        this.group = group;
    }

    public GroupManager getGroupManager() {
        return groupManager;
    }
}