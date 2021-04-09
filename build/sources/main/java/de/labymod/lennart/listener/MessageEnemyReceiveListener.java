package de.labymod.lennart.listener;

import net.labymod.api.events.MessageReceiveEvent;

public class MessageEnemyReceiveListener implements MessageReceiveEvent {

    public static String enemy = null;

    @Override
    public boolean onReceive(String s, String strippedMessage) {
        if (!strippedMessage.contains("1vs1") && !strippedMessage.contains("»")) {
            return false;
        }

        if (strippedMessage.contains("Kampf") && strippedMessage.contains("beginnt")) {

            String enemyname = s.split("§6")[1].split("§7")[0];
            enemyname = enemyname.substring(0, enemyname.length()-2);
            System.out.println(enemyname);

            enemy = enemyname;


        }

        else if (strippedMessage.contains("Du") && strippedMessage.contains("hast den Kampf gegen")) {

            enemy = null;

        }

        return false;
    }
}