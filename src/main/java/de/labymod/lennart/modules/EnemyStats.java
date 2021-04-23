package de.labymod.lennart.modules;

import de.labymod.lennart.TimoliaAddon;
import de.labymod.lennart.listener.MessageEnemyReceiveListener;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.moduletypes.SimpleTextModule;
import net.labymod.settings.elements.ControlElement;
import net.minecraft.util.ResourceLocation;

public class EnemyStats extends SimpleTextModule {

    @Override
    public ModuleCategory getCategory() {
        return TimoliaAddon.getINSTANCE().getTimolia();
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
        return new String[]{
                "000",
                "000",
                "000",
                "000",
                "000",
                "000",
                "000",
                "000",
                "000",
                "000"
        };
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
        return new String[]{
                "Spiele gespielt",
                "Spiele gewonnen",
                "Knappe Wins",
                "eZ Wins",
                "W/L",
                "Spiele gespielt(Team)",
                "Spiele gewonnen(Team)",
                "Kills insgesamt(Team)",
                "Beste Platzierung(Team)",
                "Medaillen"
        };
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
        return "Stats deines Gegners(1vs1)";
    }

    @Override
    public String getControlName() {
        return getSettingName();
    }

    @Override
    public String getDescription() {
        return "Zeigt dir die Stats deines Gegners";
    }

    @Override
    public int getSortingId() {
        return 0;
    }
}