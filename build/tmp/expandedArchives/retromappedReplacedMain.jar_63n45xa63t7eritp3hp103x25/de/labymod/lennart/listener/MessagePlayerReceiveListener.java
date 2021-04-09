package de.labymod.lennart.listener;

import de.labymod.lennart.stats.EnemyStats1vs1;
import net.labymod.api.events.MessageReceiveEvent;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;

public class MessagePlayerReceiveListener implements MessageReceiveEvent {
    public static String player = null;
    public static EnemyStats1vs1 stats = null;
    private boolean listenForStats = false;
    private List<String> latestStats = new ArrayList<>();

    @Override
    public boolean onReceive(String s, String strippedMessage) {
        if (strippedMessage.contains("1vs1") && strippedMessage.contains("»")) {
            if (strippedMessage.contains("Kampf") && strippedMessage.contains("beginnt")) {

                String enemyname = s.split("§6")[1].split("§7")[0];
                enemyname = enemyname.substring(0, enemyname.length()-2);
                System.out.println(enemyname);
                player = enemyname;

                if (player.contains("&") && player.contains(" ")) {
                    return false;
                }
                Minecraft.func_71410_x().field_71439_g.func_71165_d("/stats");
                listenForStats = true;
            }
            else if (strippedMessage.contains("Du") && strippedMessage.contains("hast den Kampf gegen")) {
                player = null;
            }
        }

        if (strippedMessage.contains("░▒▓Stats von")) {
            latestStats.clear();
        }

        else if (strippedMessage.contains("╚═════")) {
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
