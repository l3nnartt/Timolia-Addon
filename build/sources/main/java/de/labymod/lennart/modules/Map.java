package de.labymod.lennart.modules;

import de.labymod.lennart.addon;
import de.labymod.lennart.listener.MessageMapReceiveListener;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Material;
import net.minecraft.util.ResourceLocation;

public class Map extends SimpleModule {


    @Override
    public ModuleCategory getCategory() {
        return addon.INSTANCE.timolia;
    }

    @Override
    public String getDisplayName() {
        return "Map";
    }

    @Override
    public String getDisplayValue() {
        return MessageMapReceiveListener.latestMap;
    }

    @Override
    public String getDefaultValue() {
        return "???";
    }

    @Override
    public ControlElement.IconData getIconData() {
        return new ControlElement.IconData(new ResourceLocation("icons/timolia/map128.png"));
    }

    @Override
    public void loadSettings() {

    }

    @Override
    public String getSettingName() {
        return "Current Map";
    }

    @Override
    public String getControlName() {
        return getSettingName();
    }

    @Override
    public String getDescription() {
        return "Displays the current map you're playing on";
    }

    @Override
    public int getSortingId() {
        return 0;
    }
}
