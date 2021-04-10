package de.labymod.lennart.modules;

import de.labymod.lennart.addon;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.minecraft.util.ResourceLocation;

public class PxlSpace extends SimpleModule{

    @Override
    public ModuleCategory getCategory() {
        return addon.INSTANCE.timolia;
    }

    @Override
    public String getDisplayName() {
        return "Platzierte Blöcke";
    }

    @Override
    public String getDisplayValue() {
        return String.valueOf(addon.INSTANCE.placedBlocks);
    }

    @Override
    public String getDefaultValue() {
        return "???";
    }

    @Override
    public ControlElement.IconData getIconData() {
        return new ControlElement.IconData(new ResourceLocation("icons/timolia/pxlspace128.png"));
    }

    @Override
    public void loadSettings() {

    }

    @Override
    public String getSettingName() {
        return "Pixel Space";
    }

    @Override
    public String getControlName() {
        return getSettingName();
    }

    @Override
    public String getDescription() {
        return "Zeigt dir an wie viele Blöcke du in PxlSpace gesetzt hast";
    }

    @Override
    public int getSortingId() {
        return 0;
    }
}
