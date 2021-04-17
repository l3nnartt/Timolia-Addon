package de.labymod.lennart.stats;

import java.util.ArrayList;
import java.util.List;

public class EnemyStats1vs1 {

    private final List<EnemyStat1vs1> stats = new ArrayList<>();

    public EnemyStats1vs1(List<String> stringList){
        List<String> filteredList = new ArrayList<>();
        for (String strings : stringList) {
            if (strings.contains("✦")) {
                filteredList.add(strings);
            }
        }

        for (String stringsFiltered : filteredList) {
            String filteredString = stringsFiltered.replace("╠ ✦ ", "");
            String[] finalString = filteredString.split(": ");
            stats.add(new EnemyStat1vs1(finalString[0], finalString[1]));
        }
    }

    public List<EnemyStat1vs1> getStats() {
        return stats;
    }
    public String[] getFullEnemyStats() {
        List<String> strings = new ArrayList<>();
        stats.forEach(statitem -> {
            strings.add(statitem.getFull());
        });
        return strings.toArray(new String[0]);
    }

    public String[] getEnemyStatsValues() {
        List<String> strings = new ArrayList<>();
        stats.forEach(statitem -> {
            strings.add(statitem.getValue());
        });
        return strings.toArray(new String[0]);
    }

    public String[] getEnemyStatsName() {
        List<String> strings = new ArrayList<>();
        stats.forEach(statitem -> {
            strings.add(statitem.getName());
        });
        return strings.toArray(new String[0]);
    }
}