package de.labymod.lennart.listener;

import de.labymod.lennart.stats.EnemyStats1vs1;
import net.labymod.api.events.MessageReceiveEvent;
import net.minecraft.client.Minecraft;
import java.util.ArrayList;
import java.util.List;

public class MessageEnemyReceiveListener implements MessageReceiveEvent {

    public static String enemy = null;
    public static String kit = null;
    public static EnemyStats1vs1 stats = null;
    private boolean listenForStats = false;
    private List<String> latestStats = new ArrayList<>();

    @Override
    public boolean onReceive(String s, String strippedMessage) {
        if (strippedMessage.contains("1vs1") && strippedMessage.contains("»")) {

            if (strippedMessage.contains("Kit") && strippedMessage.contains("Einstellungen")) {

                String kitname = s.split("§6")[1].split("§8")[0];
                kitname = kitname.substring(0, kitname.length()-1);
                kit = kitname;

            }

            if (strippedMessage.contains("Kampf") && strippedMessage.contains("beginnt")) {

                String enemyname = s.split("§6")[1].split("§7")[0];
                enemyname = enemyname.substring(0, enemyname.length()-2);
                enemy = enemyname;

                if (enemy.contains("&") && enemy.contains(" ")) {
                    return false;
                }
                Minecraft.func_71410_x().field_71439_g.func_71165_d("/stats " + enemy);
                listenForStats = true;
            }

            else if (strippedMessage.contains("den Kampf gegen")) {
                stats = null;
                enemy = null;
                kit = null;
            }
        }

        if (strippedMessage.contains("░▒▓Stats von")) {
            latestStats.clear();
        } else if (strippedMessage.contains("╚═════")) {
            listenForStats = false;
            stats = new EnemyStats1vs1(latestStats);
            return true;
        }

        if (listenForStats) {
            latestStats.add(strippedMessage);
            return true;
        }
        return false;
    }
}