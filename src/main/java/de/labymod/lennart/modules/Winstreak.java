package de.labymod.lennart.modules;

import de.labymod.lennart.TimoliaAddon;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.minecraft.util.ResourceLocation;

public class Winstreak extends SimpleModule{

    @Override
    public ModuleCategory getCategory() {
        return TimoliaAddon.getInstance().getTimolia();
    }

    @Override
    public String getDisplayName() {
        return "Winstreak";
    }

    @Override
    public String getDisplayValue() {
        return String.valueOf(TimoliaAddon.getInstance().getKillstreak());
    }

    @Override
    public String getDefaultValue() {
        return "???";
    }

    @Override
    public ControlElement.IconData getIconData() {
        return new ControlElement.IconData(new ResourceLocation("icons/timolia/enemy128.png"));
    }

    @Override
    public void loadSettings() {

    }

    @Override
    public String getSettingName() {
        return "Killstreak";
    }

    @Override
    public String getControlName() {
        return getSettingName();
    }

    @Override
    public String getDescription() {
        return "Zeigt dir deine aktuelle Killstreak an";
    }

    @Override
    public int getSortingId() {
        return 0;
    }
}