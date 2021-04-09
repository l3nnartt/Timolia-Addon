package de.labymod.lennart.modules;

import de.labymod.lennart.addon;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.moduletypes.SimpleTextModule;
import net.labymod.settings.elements.ControlElement;
import net.minecraft.util.ResourceLocation;

public class PlayerStats extends SimpleTextModule {

    @Override
    public ModuleCategory getCategory() {
        return addon.INSTANCE.timolia;
    }

    @Override
    public String[] getValues() {
        return new String[0];
    }

    @Override
    public String[] getDefaultValues() {
        return new String[0];
    }

    @Override
    public String[] getKeys() {
        return new String[0];
    }

    @Override
    public String[] getDefaultKeys() {
        return new String[0];
    }

    @Override
    public ControlElement.IconData getIconData() {
        return new ControlElement.IconData(new ResourceLocation("icons/timolia/stats128.png"));
    }

    @Override
    public void loadSettings() {

    }

    @Override
    public String getSettingName() {
        return "Own Stats";
    }

    @Override
    public String getControlName() {
        return getSettingName();
    }

    @Override
    public String getDescription() {
        return "Displays your own Stats";
    }

    @Override
    public int getSortingId() {
        return 0;
    }
}
