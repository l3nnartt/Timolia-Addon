package de.labymod.lennart.modules;

import de.labymod.lennart.addon;
import de.labymod.lennart.listener.MessageEnemyReceiveListener;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.moduletypes.SimpleTextModule;
import net.labymod.settings.elements.ControlElement;
import net.minecraft.util.ResourceLocation;

public class EnemyStats extends SimpleTextModule {

    @Override
    public ModuleCategory getCategory() {
        return addon.INSTANCE.timolia;
    }

    @Override
    public String[] getValues() {
        if (MessageEnemyReceiveListener.stats != null) {
         return MessageEnemyReceiveListener.stats.getEnemyStatsValues();
        }
        else {
            return new String[0];
        }
    }

    @Override
    public String[] getDefaultValues() {
        return new String[0];
    }

    @Override
    public String[] getKeys() {
        if (MessageEnemyReceiveListener.stats != null) {
            return MessageEnemyReceiveListener.stats.getEnemyStatsName();
        }
        else {
            return new String[0];
        }
    }

    @Override
    public String[] getDefaultKeys() {
        return new String[0];
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
        return "Stats of Enemy";
    }

    @Override
    public String getControlName() {
        return getSettingName();
    }

    @Override
    public String getDescription() {
        return "display stats of the enemy";
    }

    @Override
    public int getSortingId() {
        return 0;
    }
}
